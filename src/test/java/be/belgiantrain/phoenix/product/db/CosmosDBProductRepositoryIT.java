package be.belgiantrain.phoenix.product.db;

import be.belgiantrain.phoenix.product.ProductApp;
import be.belgiantrain.phoenix.product.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CosmosDBProductRepositoryIT {

    // @Autowired
    @SuppressWarnings("unused")
    private CosmosDBProductRepository cosmosDBProductRepository;

    @SuppressWarnings("unchecked")
    private Product product = new Product("000", "000", "standard-ticket", Collections.EMPTY_MAP);

    @Before
    public void before() {
        if (cosmosDBProductRepository != null) {
            cosmosDBProductRepository.save(product);
        }
    }

    @After
    public void after() {
        if (cosmosDBProductRepository != null) {
            cosmosDBProductRepository.delete(product);
        }
    }

    @Test
    @Ignore
    public void findAll() {
        Iterable<Product> products = cosmosDBProductRepository.findAll();
        Assert.assertTrue(products.iterator().hasNext());
    }

    @Test
    @Ignore
    public void find() {
        Optional<Product> dbProduct = cosmosDBProductRepository.findById(product.getId());
        Assert.assertTrue(dbProduct.isPresent());
    }

    @Test
    @Ignore
    public void findByCode() {
        // cosmosDBProductRepository.findByCode("000");
    }

}