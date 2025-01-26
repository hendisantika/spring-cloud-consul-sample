package id.my.hendisantika.orderservice;

import io.specto.hoverfly.junit5.HoverflyExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.consul.ConsulContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ExtendWith(HoverflyExtension.class)
class OrderServiceApplicationTests {

	@Container
	static ConsulContainer consulContainer = new ConsulContainer("consul:1.14")
			.withConsulCommand("kv put config/order-service test=abc");
	@Autowired
	TestRestTemplate template;

	@BeforeAll
	static void init() {
		System.setProperty("spring.cloud.consul.port", consulContainer.getFirstMappedPort().toString());
		System.setProperty("spring.config.import", "optional:consul:localhost:" + consulContainer.getFirstMappedPort());
	}

}
