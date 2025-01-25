package id.my.hendisantika.customerservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.customerservice.client.AccountClient;
import id.my.hendisantika.customerservice.model.Account;
import id.my.hendisantika.customerservice.model.Customer;
import id.my.hendisantika.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    private final CustomerRepository customerRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/")
    public Customer add(@RequestBody Customer customer) {
        return customerRepository.add(customer);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return customerRepository.update(customer);
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") Long id) {
        return customerRepository.findById(id);
    }

    @GetMapping("/withAccounts/{id}")
    public Customer findByIdWithAccounts(@PathVariable("id") Long id) throws JsonProcessingException {
        List<Account> accounts = accountClient.findByCustomer(id);
        log.info("Accounts found: {}", mapper.writeValueAsString(accounts));
        Customer c = customerRepository.findById(id);
        c.setAccounts(accounts);
        return c;
    }

    @PostMapping("/ids")
    public List<Customer> find(@RequestBody List<Long> ids) {
        return customerRepository.find(ids);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        customerRepository.delete(id);
    }
}
