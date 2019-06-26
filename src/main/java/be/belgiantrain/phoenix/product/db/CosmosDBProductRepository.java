package be.belgiantrain.phoenix.product.db;

import be.belgiantrain.phoenix.product.model.Product;
import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
// @Repository
public interface CosmosDBProductRepository extends DocumentDbRepository<Product, String> {

    // Product findByCode(final String code);

}