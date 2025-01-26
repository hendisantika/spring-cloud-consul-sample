package id.my.hendisantika.accountservice.controller;

import id.my.hendisantika.accountservice.model.Account;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.testcontainers.consul.ConsulContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 26/01/25
 * Time: 07.03
 * To change this template use File | Settings | File Templates.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class AccountControllerTest {
    @Container
    static ConsulContainer consulContainer = new ConsulContainer("consul:1.14")
            .withConsulCommand("kv put config/account-service test=abc");
    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    static void init() {
        System.setProperty("spring.cloud.consul.port", consulContainer.getFirstMappedPort().toString());
        System.setProperty("spring.config.import", "optional:consul:localhost:" + consulContainer.getFirstMappedPort());
    }

    @Test
    void findAll() {
        List<Long> ids = List.of(1L, 2L, 3L);
        Account[] accounts = restTemplate.postForObject("/ids", ids, Account[].class);
        assertEquals(3, accounts.length);
    }

    @Test
    void findByCustomerId() {
        Account[] accounts = restTemplate.getForObject("/customer/{customerId}", Account[].class, 1L);
        assertTrue(accounts.length > 0);
    }

    @Test
    void addAccountTest() {
        Account a = new Account("2234567890", 50000, 10L);
        a = restTemplate.postForObject("/", a, Account.class);
        assertNotNull(a);
        assertNotNull(a.getId());
    }

    @Test
    void withdrawOk() {
        ResponseEntity<Account> r = restTemplate.exchange("/withdraw/1/10000", HttpMethod.PUT, null, Account.class);
        assertEquals(200, r.getStatusCodeValue());
        assertNotNull(r.getBody());
        assertEquals(r.getBody().getBalance(), 40000);
    }

    @Test
    void withdrawFailed() {
        ResponseEntity<Account> r = restTemplate.exchange("/withdraw/2/100000", HttpMethod.PUT, null, Account.class);
        assertEquals(500, r.getStatusCodeValue());
    }
}
