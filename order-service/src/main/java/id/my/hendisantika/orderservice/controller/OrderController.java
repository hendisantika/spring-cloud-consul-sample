package id.my.hendisantika.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.orderservice.client.AccountClient;
import id.my.hendisantika.orderservice.client.CustomerClient;
import id.my.hendisantika.orderservice.client.ProductClient;
import id.my.hendisantika.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

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
}
