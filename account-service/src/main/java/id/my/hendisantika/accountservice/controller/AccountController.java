package id.my.hendisantika.accountservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.accountservice.exception.BalanceNotEnoughException;
import id.my.hendisantika.accountservice.model.Account;
import id.my.hendisantika.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/01/25
 * Time: 10.49
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final ObjectMapper mapper = new ObjectMapper();

    private final AccountRepository accountRepository;

    @PostMapping("/")
    public Account add(@RequestBody Account account) {
        return accountRepository.add(account);
    }

    @PutMapping
    public Account update(@RequestBody Account account) {
        return accountRepository.update(account);
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public Account withdraw(@PathVariable("id") Long id, @PathVariable("amount") int amount) throws JsonProcessingException {
        Account account = accountRepository.findById(id);
        if (amount > account.getBalance())
            throw new BalanceNotEnoughException("Not enough funds: id=" + id + ", amount=" + amount);
        log.info("Account found: {}", mapper.writeValueAsString(account));
        account.setBalance(account.getBalance() - amount);
        log.info("Current balance: {}", mapper.writeValueAsString(Collections.singletonMap("balance", account.getBalance())));
        return accountRepository.update(account);
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable("id") Long id) {
        return accountRepository.findById(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> findByCustomerId(@PathVariable("customerId") Long customerId) {
        return accountRepository.findByCustomer(customerId);
    }
}
