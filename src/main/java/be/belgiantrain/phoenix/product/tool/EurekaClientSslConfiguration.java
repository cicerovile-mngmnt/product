package be.belgiantrain.phoenix.product.tool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class intercepts the construction of the {@link DiscoveryClient} and adds arguments for ssl/tls/https. The parameters
 * for the location of the certificate and the password must be defined by the service that includes this library. Typically the
 * parameters will be in the yml configuration file, application.yml, bootstrap.yml or something similar.
 * <p>
 * Additionally the clients that use this library need to scan the library of this class first, to load the certificate
 * and initialize the client before anything else happens. This is achieved by the following annotation:<br>
 * {@link org.springframework.context.annotation.ComponentScan}
 * <p>
 * With as parameters:<br>
 * <pre>
 *    {"be.belgiantrain"}
 * </pre>
 *
 * @author MCOUCK
 * @version 1.00
 * @since 18-12-2018
 */
@Configuration
@SuppressWarnings({"SpringFacetCodeInspection", "JavadocReference"})
public class EurekaClientSslConfiguration {

    /**
     * These must be installed on the file system, /etc/ssl/certs/...jks, and the path and value for the certificate
     * and the password must be defined in a bootstrap.yml or application.yml file, spring needs to inject these into the
     * values, without which the application will fail to start.
     */
    @Value("${server.ssl.key-store:/etc/ssl/certs/belgiantrain.jks}")
    private String keyStoreCertificate;
    @Value("${server.ssl.key-store-password:password}")
    private String keyStorePassword;
    @Value("${server.ssl.key-store-type:JKS}")
    private String keyStoreType;

    @Value("${server.ssl.trust-store:/etc/ssl/certs/belgiantrain.jks}")
    private String trustStoreCertificate;
    @Value("${server.ssl.trust-store-password:password}")
    private String trustStorePassword;
    @Value("${server.ssl.trust-store-type:JKS}")
    private String trustStoreType;

    /**
     * This method just inserts the location and the password for the ssl certificates into the {@link com.netflix.discovery.EurekaClient}
     * that the client can connect to the secured EurekaServer.
     *
     * @return the additional arguments
     */
    /*@Bean
    public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientSslArgs() {
        // TODO: Potentially look for certificates in the default folders
        String message =
                "Eureka client ssl properties, key store password : {}, " +
                        "key store certificate : {}, " +
                        "trust store password : {}, " +
                        "trust store certificate : {}";
        System.out.println(message + keyStorePassword + ":" + keyStoreCertificate + ":" + trustStorePassword + ":" + trustStoreCertificate);

        // -Djavax.net.ssl.keyStore=/etc/ssl/certs/belgiantrain.jks -Djavax.net.ssl.keyStorePassword=password -Djavax.net.ssl.trustStore=/etc/ssl/certs/belgiantrain.jks -Djavax.net.ssl.trustStorePassword=passwordX
        System.setProperty("javax.net.ssl.keyStore", keyStoreCertificate);
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
        System.setProperty("javax.net.ssl.keyStoreType", keyStoreType);
        System.setProperty("javax.net.ssl.trustStore", trustStoreCertificate);
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
        System.setProperty("javax.net.ssl.trustStoreType", trustStoreType);

        EurekaJerseyClientImpl.EurekaJerseyClientBuilder builder = new EurekaJerseyClientImpl.EurekaJerseyClientBuilder();
        builder.withClientName("eureka-ssl-client");
        builder.withSystemSSLConfiguration();
        builder.withMaxTotalConnections(3);
        builder.withMaxConnectionsPerHost(3);

        DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs = new DiscoveryClient.DiscoveryClientOptionalArgs();
        discoveryClientOptionalArgs.setEurekaJerseyClient(builder.build());

        return discoveryClientOptionalArgs;
    }*/

}