package be.belgiantrain.phoenix.product.config;

import com.microsoft.azure.spring.data.cosmosdb.config.AbstractDocumentDbConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.DocumentDBConfig;
import org.springframework.beans.factory.annotation.Value;

// @Configuration
// @EnableDocumentDbRepositories(basePackageClasses = CosmosDBProductRepository.class)
@SuppressWarnings({"SpringFacetCodeInspection", "unused"})
public class AppConfiguration extends AbstractDocumentDbConfiguration {

    @Value("${azure.cosmosdb.uri:https://product.documents.azure.com:443/}")
    private String uri = "https://product.documents.azure.com:443/";

    @Value("${azure.cosmosdb.key:Qd2G0bdLGQg6lWHv9X4A864eQ2cgIvo3XDQv3SLPBC7Egl13WHtoSSEZWCSbPSKCC5IzaqYP8gD65rN5B9phdw==}")
    private String key = "Qd2G0bdLGQg6lWHv9X4A864eQ2cgIvo3XDQv3SLPBC7Egl13WHtoSSEZWCSbPSKCC5IzaqYP8gD65rN5B9phdw==";

    @Value("${azure.cosmosdb.database:product}")
    private String dbName = "product";

    public DocumentDBConfig getConfig() {
        return DocumentDBConfig.builder(uri, key, dbName).build();
    }
}