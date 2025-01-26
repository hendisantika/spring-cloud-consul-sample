package id.my.hendisantika.gatewayservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.testcontainers.consul.ConsulContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class GatewayServiceApplicationTests {

	@Container
	static ConsulContainer consulContainer = new ConsulContainer("consul:1.14")
			.withConsulCommand("kv put config/customer-service test=abc");
	@Autowired
	TestRestTemplate template;

	@BeforeAll
	static void init() {
		System.setProperty("spring.cloud.consul.port", consulContainer.getFirstMappedPort().toString());
		System.setProperty("spring.config.import", "optional:consul:localhost:" + consulContainer.getFirstMappedPort());
	}

	@Test
	public void testOrder() throws InterruptedException {
		for (int i = 0; i < 5; i++) {
			sendAndAcceptOrder();
		}
	}

	private void sendAndAcceptOrder() {
		try {
			Random r = new Random();
			Order order = new Order();
			order.setCustomerId((long) r.nextInt(3) + 1);
			order.setProductIds(List.of(new Long[]{(long) r.nextInt(10) + 1, (long) r.nextInt(10) + 1}));
			order = template.postForObject("/api/order", order, Order.class);
			if (order.getStatus() != OrderStatus.REJECTED) {
				template.put("/api/order/{id}", null, order.getId());
			}
		} catch (Exception e) {

		}
	}

}
