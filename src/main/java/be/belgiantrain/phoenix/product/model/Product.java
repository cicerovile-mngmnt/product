package be.belgiantrain.phoenix.product.model;

import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Locale;
import java.util.Map;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class Product {

    /**
     * Cosmos identifier.
     */
    @Id
    private String id;
    /**
     * UIC code.
     */
    private String code;
    /**
     * Descriptive name.
     */
    private String name;

    /**
     * Extended descriptions.
     */
    private Map<Locale, String> description;

}