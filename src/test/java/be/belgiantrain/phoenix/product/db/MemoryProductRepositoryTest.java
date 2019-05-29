package be.belgiantrain.phoenix.product.db;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class MemoryProductRepositoryTest {

    @Spy
    private MemoryProductRepository memoryProductRepository;

    @Before
    public void before() throws IOException {
        memoryProductRepository.postConstruct();
    }

    @Test
    public void getProducts() {
        Assert.assertTrue(memoryProductRepository.getProducts().size() > 0);
    }

    @Test
    public void getProductByCode() {
        Assert.assertTrue(memoryProductRepository.getProductByCode("000") != null);
    }

    @Test
    public void getProductByName() {
        Assert.assertTrue(memoryProductRepository.getProductByName("standard-ticket") != null);
    }

}