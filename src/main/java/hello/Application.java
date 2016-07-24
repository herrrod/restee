package hello;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Arrays;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public ServletRegistrationBean h2WebConsole() {
        return new ServletRegistrationBean(new WebServlet(), "/h2-console/*");
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        return (args) -> {


            // save a couple of customers
            Customer d3 = new Customer("Dad", "v3");
            Customer d2 = new Customer("Dad", "v2");
            Customer d1 = new Customer("Dad", "v1");

            d3.setChildren(Arrays.asList(d2, d1));
            d2.setParent(d3);
            d3.setParent(d3);
            repository.save(d3);

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");

//            // modify d1 and create a new version.
//            Customer d4 = new Customer("Dad", "v4");
//            d3.setParent()
//            d4.setChildren(Arrays.asList(d3,d2,d1));
//            repository.save(d4);
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Customer customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");
        };
    }
}