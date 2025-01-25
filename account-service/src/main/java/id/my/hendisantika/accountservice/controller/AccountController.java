package id.my.hendisantika.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.accountservice.model.Account;
import id.my.hendisantika.accountservice.repository.AccountRepository;
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
}
