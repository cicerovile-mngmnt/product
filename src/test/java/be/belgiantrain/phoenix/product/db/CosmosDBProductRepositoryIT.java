package be.belgiantrain.phoenix.product.db;

import be.belgiantrain.phoenix.product.ProductApp;
import be.belgiantrain.phoenix.product.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CosmosDBProductRepositoryIT {

    @Autowired
    @SuppressWarnings("unused")
    private CosmosDBProductRepository cosmosDBProductRepository;

    private Product product = new Product("000", "000", "standard-ticket");

    @Before
    public void before() {
        cosmosDBProductRepository.save(product);
    }

    @After
    public void after() {
        cosmosDBProductRepository.delete(product);
    }

    @Test
    public void findAll() {
        Iterable<Product> products = cosmosDBProductRepository.findAll();
        Assert.assertTrue(products.iterator().hasNext());
    }

    @Test
    public void find() {
        Optional<Product> dbProduct = cosmosDBProductRepository.findById(product.getId());
        Assert.assertTrue(dbProduct.isPresent());
    }

    @Test
    public void findByCode() {
        // cosmosDBProductRepository.findByCode("000");
    }

}