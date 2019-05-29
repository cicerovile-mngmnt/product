package be.belgiantrain.phoenix.product.db;

import be.belgiantrain.phoenix.product.model.Product;

import java.util.Set;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
public interface ProductRepository {

    Set<Product> getProducts();

    Product getProductByCode(final String code);

    Product getProductByName(final String name);

}