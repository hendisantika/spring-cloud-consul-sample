package id.my.hendisantika.customerservice.repository;

import id.my.hendisantika.customerservice.model.Customer;

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
 * Time: 10.59
 * To change this template use File | Settings | File Templates.
 */
public class CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();

    public Customer add(Customer customer) {
        customer.setId((long) (customers.size() + 1));
        customers.add(customer);
        return customer;
    }

    public Customer update(Customer customer) {
        customers.set(customer.getId().intValue() - 1, customer);
        return customer;
    }

    public Customer findById(Long id) {
        return customers.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    public void delete(Long id) {
        customers.remove(id.intValue());
    }
}
