package id.my.hendisantika.gatewayservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 26/01/25
 * Time: 08.32
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class Customer {
    private Long id;
    private String name;
    private CustomerType type;
    private List<Account> accounts = new ArrayList<>();
}
