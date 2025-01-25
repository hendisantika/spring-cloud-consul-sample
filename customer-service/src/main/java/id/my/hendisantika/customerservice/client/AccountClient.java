package id.my.hendisantika.customerservice.client;

import id.my.hendisantika.customerservice.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/01/25
 * Time: 11.00
 * To change this template use File | Settings | File Templates.
 */
@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping("/customers/{customerId}")
    List<Account> findByCustomer(@PathVariable("customerId") Long customerId);
}
