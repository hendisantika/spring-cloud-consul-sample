package id.my.hendisantika.orderservice.repository;

import id.my.hendisantika.orderservice.model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/01/25
 * Time: 11.18
 * To change this template use File | Settings | File Templates.
 */
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public Order add(Order order) {
        order.setId((long) (orders.size() + 1));
        orders.add(order);
        return order;
    }

    public Order update(Order order) {
        orders.set(order.getId().intValue() - 1, order);
        return order;
    }

    public Order findById(Long id) {
        return orders.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow();
    }

    public void delete(Long id) {
        orders.remove(id.intValue());
    }
}
