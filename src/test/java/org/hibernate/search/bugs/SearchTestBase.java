package org.hibernate.search.bugs;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.spi.ServiceRegistryImplementor;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.net.InetAddress;

public abstract class SearchTestBase {

    private SessionFactory sessionFactory;

    private RestClient elasticsearchRestClient;

    @Before
    public void setUp() {
        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        ServiceRegistryImplementor serviceRegistry = (ServiceRegistryImplementor) registryBuilder.build();

        MetadataSources ms = new MetadataSources(serviceRegistry);
        Class<?>[] annotatedClasses = getAnnotatedClasses();
        if (annotatedClasses != null) {
            for (Class<?> entity : annotatedClasses) {
                ms.addAnnotatedClass(entity);
            }
        }

        Metadata metadata = ms.buildMetadata();

        final SessionFactoryBuilder sfb = metadata.getSessionFactoryBuilder();
        this.sessionFactory = sfb.build();
        this.buildElasticsearchRestClient();
    }

    @After
    public void tearDown() {
        this.sessionFactory.close();
        this.closeElasticsearchRestClient();
    }

    private void buildElasticsearchRestClient() {
        String protocol = "http";
        String url = InetAddress.getLoopbackAddress().getHostAddress();
        String port = "9200";
        this.getLogger().info("RestClient: " + protocol + "://" + url + ":" + port);
        RestClientBuilder builder = RestClient.builder(new HttpHost(url, Integer.valueOf(port), protocol));
        this.elasticsearchRestClient = builder.build();
    }

    private void closeElasticsearchRestClient() {
        try {
            elasticsearchRestClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract Class<?>[] getAnnotatedClasses();

    protected abstract Logger getLogger();

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public RestClient getElasticsearchRestClient() {
        return elasticsearchRestClient;
    }
}
