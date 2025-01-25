package id.my.hendisantika.productservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.productservice.model.Product;
import id.my.hendisantika.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-cloud-consul-sample
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 25/01/25
 * Time: 11.32
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping
    public Product add(@RequestBody Product product) {
        return repository.add(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return repository.update(product);
    }
}
