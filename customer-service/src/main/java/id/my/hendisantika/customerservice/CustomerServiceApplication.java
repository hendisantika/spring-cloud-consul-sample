package id.my.hendisantika.customerservice;

import id.my.hendisantika.customerservice.model.Customer;
import id.my.hendisantika.customerservice.model.CustomerType;
import id.my.hendisantika.customerservice.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableFeignClients
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setMaxPayloadLength(1000);
        loggingFilter.setAfterMessagePrefix("REQ:");
        return loggingFilter;
    }

    @Bean
    public CustomerRepository customerRepository() {
        CustomerRepository repository = new CustomerRepository();
        repository.add(new Customer("Itadori Yuji", CustomerType.NEW));
        repository.add(new Customer("Satoru Gojo", CustomerType.REGULAR));
        repository.add(new Customer("Suguru Geto", CustomerType.VIP));
        return repository;
    }
}
