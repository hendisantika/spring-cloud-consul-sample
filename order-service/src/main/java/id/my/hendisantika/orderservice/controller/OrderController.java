package id.my.hendisantika.orderservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.orderservice.client.AccountClient;
import id.my.hendisantika.orderservice.client.CustomerClient;
import id.my.hendisantika.orderservice.client.ProductClient;
import id.my.hendisantika.orderservice.model.Account;
import id.my.hendisantika.orderservice.model.Customer;
import id.my.hendisantika.orderservice.model.Order;
import id.my.hendisantika.orderservice.model.OrderStatus;
import id.my.hendisantika.orderservice.model.Product;
import id.my.hendisantika.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/01/25
 * Time: 11.24
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository repository;
    private final AccountClient accountClient;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/")
    public Order prepare(@RequestBody Order order) throws JsonProcessingException {
        int price = 0;
        List<Product> products = productClient.findByIds(order.getProductIds());
        log.info("Products found: {}", mapper.writeValueAsString(products));
        Customer customer = customerClient.findByIdWithAccounts(order.getCustomerId());
        log.info("Customer found: {}", mapper.writeValueAsString(customer));
        for (Product product : products)
            price += product.getPrice();
        final int priceDiscounted = priceDiscount(price, customer);
        log.info("Discounted price: {}", mapper.writeValueAsString(Collections.singletonMap("price", priceDiscounted)));
        Optional<Account> account = customer.getAccounts().stream().filter(a -> (a.getBalance() > priceDiscounted)).findFirst();
        if (account.isPresent()) {
            order.setAccountId(account.get().getId());
            order.setStatus(OrderStatus.ACCEPTED);
            order.setPrice(priceDiscounted);
            log.info("Account found: {}", mapper.writeValueAsString(account.get()));
        } else {
            order.setStatus(OrderStatus.REJECTED);
            log.info("Account not found: {}", mapper.writeValueAsString(customer.getAccounts()));
        }
        Map<String, String> m = MDC.getCopyOfContextMap();
        return repository.add(order);
    }

    @PutMapping("/{id}")
    public Order accept(@PathVariable Long id) throws JsonProcessingException {
        final Order order = repository.findById(id);
        log.info("Order found: {}", mapper.writeValueAsString(order));
        accountClient.withdraw(order.getAccountId(), order.getPrice());
        HashMap<String, Object> log1 = new HashMap<>();
        log1.put("accountId", order.getAccountId());
        log1.put("price", order.getPrice());
        log.info("Account modified: {}", mapper.writeValueAsString(log));
        order.setStatus(OrderStatus.DONE);
        log.info("Order status changed: {}", mapper.writeValueAsString(Collections.singletonMap("status", order.getStatus())));
        repository.update(order);
        return order;
    }
}
