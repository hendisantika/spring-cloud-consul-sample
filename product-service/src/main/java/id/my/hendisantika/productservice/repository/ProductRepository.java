package id.my.hendisantika.productservice.repository;

import id.my.hendisantika.productservice.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/01/25
 * Time: 11.31
 * To change this template use File | Settings | File Templates.
 */
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public Product add(Product product) {
        product.setId((long) (products.size() + 1));
        products.add(product);
        return product;
    }

    public Product update(Product product) {
        products.set(product.getId().intValue() - 1, product);
        return product;
    }

    public Product findById(Long id) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (product.isPresent())
            return product.get();
        else
            return null;
    }

    public void delete(Long id) {
        products.remove(id.intValue());
    }
}
