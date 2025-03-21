package id.my.hendisantika.customerservice;

import id.my.hendisantika.customerservice.model.Customer;
import id.my.hendisantika.customerservice.model.CustomerType;
import io.specto.hoverfly.junit.core.Hoverfly;
import io.specto.hoverfly.junit5.HoverflyExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.consul.ConsulContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static io.specto.hoverfly.junit.core.SimulationSource.dsl;
import static io.specto.hoverfly.junit.dsl.HoverflyDsl.service;
import static io.specto.hoverfly.junit.dsl.ResponseCreators.success;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ExtendWith(HoverflyExtension.class)
class CustomerServiceApplicationTests {

    @Container
    static ConsulContainer consulContainer = new ConsulContainer("consul:1.14")
            .withConsulCommand("kv put config/customer-service test=abc");
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
        Customer[] customers = restTemplate.postForObject("/ids", ids, Customer[].class);
        assertEquals(3, customers.length);
    }

    @Test
    void findById() {
        Customer customer = restTemplate.getForObject("/{id}", Customer.class, 1L);
        assertNotNull(customer);
        assertNotNull(customer.getId());
    }

    @Test
    void addCustomerTest() {
        Customer c = new Customer("John Scott", CustomerType.NEW);
        c = restTemplate.postForObject("/", c, Customer.class);
        assertNotNull(c);
        assertNotNull(c.getId());
    }

    @Test
    void findByIdWithAccounts(Hoverfly hoverfly) {
        hoverfly.simulate(
                dsl(service("http://account-service")
                        .get("/customers/1")
                        .willReturn(success().body("[{\"id\":\"1\"}]").header("Content-Type", "application/json")))
        );
        Customer customer = restTemplate.getForObject("/withAccounts/{id}", Customer.class, 1L);
        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals(1, customer.getAccounts().size());
    }
}
