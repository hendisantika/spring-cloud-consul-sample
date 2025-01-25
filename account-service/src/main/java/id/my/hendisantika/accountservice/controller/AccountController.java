package id.my.hendisantika.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RestController
@RequiredArgsConstructor
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final AccountRepository accountRepository;
}
