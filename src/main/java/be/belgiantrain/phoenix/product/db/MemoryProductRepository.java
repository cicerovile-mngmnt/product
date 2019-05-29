package be.belgiantrain.phoenix.product.db;

import be.belgiantrain.phoenix.product.model.Product;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
@Slf4j
@Service
@SuppressWarnings("WeakerAccess")
public class MemoryProductRepository implements ProductRepository {

    private Set<Product> products = new TreeSet<>((o1, o2) -> o1.getCode().compareTo(o2.getCode()));

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public Product getProductByCode(final String code) {
        for (final Product product : products) {
            if (product.getCode().equals(code)) {
                return product;
            }
        }
        return null;
    }

    public Product getProductByName(final String name) {
        for (final Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    @PostConstruct
    public void postConstruct() throws IOException {
        Gson gson = new Gson();
        InputStream inputStream = MemoryProductRepository.class.getClassLoader().getResourceAsStream("product/");
        List<String> files = IOUtils.readLines(inputStream, StandardCharsets.UTF_8);
        // For some reason this is empty on Azure
        log.error("Files for data : {}", files);
        if (files == null || files.isEmpty()) {
            Map<String, Object> conditions = new HashMap<>();
            conditions.put("app", "true");
            conditions.put("internet", "true");
            conditions.put("cashier", "true");
            conditions.put("ticket-vending-machine", "true");
            Product product = new Product("000", "standard-ticket", conditions);
            products.add(product);
        } else {
            for (final String file : files) {
                inputStream = MemoryProductRepository.class.getClassLoader().getResourceAsStream("product/" + file);
                String json = new String(IOUtils.toByteArray(inputStream));
                Product product = gson.fromJson(json, Product.class);
                log.error("File  : {}, product : {}", file, product);
                products.add(product);
            }
        }
    }

}