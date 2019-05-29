package be.belgiantrain.phoenix.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Michael Couck
 * @since 28-05-2019
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"be.belgiantrain.phoenix.product"})
@SuppressWarnings("SpringFacetCodeInspection")
public class ProductApp {

    public static void main(final String[] args) {
        SpringApplication.run(ProductApp.class, args);
    }

}