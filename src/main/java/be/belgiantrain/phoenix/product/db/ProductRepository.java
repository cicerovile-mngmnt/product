package be.belgiantrain.phoenix.product.db;

import be.belgiantrain.phoenix.product.model.Product;

import java.util.Collection;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
public interface ProductRepository {

    Collection<Product> getProducts();

    Product getProductByCode(final String code);

    Product getProductByName(final String name);

}