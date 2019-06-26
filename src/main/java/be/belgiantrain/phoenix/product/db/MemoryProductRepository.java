package be.belgiantrain.phoenix.product.db;

import be.belgiantrain.phoenix.product.model.Product;
import be.belgiantrain.phoenix.product.tool.FILE;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
@Slf4j
@Service
@Repository
@SuppressWarnings("WeakerAccess")
public class MemoryProductRepository implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    public Collection<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Product getProductByCode(final String code) {
        return products.get(Collections.binarySearch(products, code));
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
    @SuppressWarnings("ConstantConditions")
    public void postConstruct() throws IOException {
        InputStream inputStream = new FileInputStream(FILE.findFileRecursively(new File("."), "standard-ticket.json"));
        String json = new String(IOUtils.toByteArray(inputStream));
        Type listType = new TypeToken<ArrayList<Product>>() {
        }.getType();
        products = new Gson().fromJson(json, listType);
        Collections.sort(products, (o1, o2) -> o1.getCode().compareTo(o2.getCode()));
    }

    @SuppressWarnings("unused")
    private Product getProduct(final String id, final String code, final String name) {
        // products.add(getProduct("0", "000", "standard-ticket"));
        Map<Locale, String> descriptions = new HashMap<>();
        return new Product(id, code, name, descriptions);
    }

}