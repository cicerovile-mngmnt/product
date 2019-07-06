package be.belgiantrain.phoenix.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Michael Couck
 * @version 1.0
 * @since 28-05-2019
 */
// @RefreshScope
@SpringBootApplication
// @EnableDiscoveryClient
@ComponentScan(basePackages = {"be.belgiantrain.phoenix.product"})
public class ProductApp {

    /*private static String keyStoreCertificate = "/etc/ssl/certs/belgiantrain.jks";
    private static String keyStorePassword = "password";
    private static String keyStoreType = "JKS";

    private static String trustStoreCertificate = "/etc/ssl/certs/belgiantrain.jks";
    private static String trustStorePassword = "password";
    private static String trustStoreType = "JKS";*/

    public static void main(final String[] args) {
        /*System.setProperty("javax.net.ssl.keyStore", keyStoreCertificate);
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
        System.setProperty("javax.net.ssl.keyStoreType", keyStoreType);
        System.setProperty("javax.net.ssl.trustStore", trustStoreCertificate);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
        System.setProperty("javax.net.ssl.trustStoreType", trustStoreType);*/
        SpringApplication.run(ProductApp.class, args);
    }

}