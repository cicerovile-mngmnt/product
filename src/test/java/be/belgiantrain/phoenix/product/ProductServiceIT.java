package be.belgiantrain.phoenix.product;

import be.belgiantrain.phoenix.product.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceIT {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void getProducts() {
        String url = "http://localhost:" + port + ProductService.PRODUCTS;
        ResponseEntity<Product[]> response = restTemplate.getForEntity(url, Product[].class);
        Product[] products = response.getBody();
        Assert.assertNotNull(products);
        Assert.assertTrue(products.length > 0);
    }

    @Test
    public void getProductByCode() {
        String url = "http://localhost:" + port + ProductService.PRODUCT_BY_CODE;
        ResponseEntity<Product> response = restTemplate.postForEntity(url, "000", Product.class);
        Product product = response.getBody();
        Assert.assertNotNull(product);
    }

    @Test
    public void getProductByName() {
        String url = "http://localhost:" + port + ProductService.PRODUCT_BY_NAME;
        ResponseEntity<Product> response = restTemplate.postForEntity(url, "standard-ticket", Product.class);
        Product product = response.getBody();
        Assert.assertNotNull(product);
    }

}