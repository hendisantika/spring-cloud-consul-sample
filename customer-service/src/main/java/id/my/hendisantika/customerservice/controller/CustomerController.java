package id.my.hendisantika.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.customerservice.client.AccountClient;
import id.my.hendisantika.customerservice.model.Customer;
import id.my.hendisantika.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/01/25
 * Time: 11.01
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final AccountClient accountClient;
    private final CustomerRepository repository;
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/")
    public Customer add(@RequestBody Customer customer) {
        return repository.add(customer);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return repository.update(customer);
    }
}
