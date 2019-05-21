package be.belgiantrain.phoenix.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"be.belgiantrain.phoenix.product"})
@SuppressWarnings("SpringFacetCodeInspection")
public class ProductApp {

    public static void main(final String[] args) {
        SpringApplication.run(ProductApp.class, args);
    }

}