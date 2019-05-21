package be.belgiantrain.phoenix.product;

import be.belgiantrain.phoenix.product.model.Product;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("WeakerAccess")
public class ProductService {

    static final String PRODUCT = "/product";

    @RequestMapping(PRODUCT)
    @ResponseBody
    public Product getService(@RequestBody final Product product) {
        // TODO: Get the data from the database
        return product;
    }

}