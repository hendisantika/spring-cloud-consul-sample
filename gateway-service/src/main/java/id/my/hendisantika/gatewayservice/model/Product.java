package id.my.hendisantika.gatewayservice.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 26/01/25
 * Time: 08.34
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private int price;
}
