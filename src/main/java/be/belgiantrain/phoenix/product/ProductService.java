package be.belgiantrain.phoenix.product;

import be.belgiantrain.phoenix.product.db.ProductRepository;
import be.belgiantrain.phoenix.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
@RestController
@SuppressWarnings("WeakerAccess")
public class ProductService {

    static final String PRODUCTS = "/products";
    static final String PRODUCT_BY_CODE = "/product-by-code";
    static final String PRODUCT_BY_NAME = "/product-by-name";

    @Autowired
    private ProductRepository productRepository;

    @ResponseBody
    @RequestMapping(value = PRODUCTS, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Product> getProducts() {
        return productRepository.getProducts();
    }

    @ResponseBody
    @RequestMapping(value = PRODUCT_BY_CODE, method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductByCode(@RequestBody final String code) {
        return productRepository.getProductByCode(code);
    }

    @ResponseBody
    @RequestMapping(value = PRODUCT_BY_NAME, method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductByName(@RequestBody final String name) {
        return productRepository.getProductByName(name);
    }

}