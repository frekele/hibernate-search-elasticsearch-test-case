package org.hibernate.search.bugs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;
import org.apache.lucene.search.Query;
import org.elasticsearch.client.Response;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.elasticsearch.ElasticsearchQueries;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.QueryDescriptor;
import org.hibernate.search.testsupport.TestForIssue;
import org.jboss.logging.Logger;
import org.junit.After;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class FieldMappingTypeIT extends SearchTestBase {

    private Logger logger = Logger.getLogger(FieldMappingTypeIT.class.getName());

    private JsonNode responseJsonNode;

    @Override
    public Class<?>[] getAnnotatedClasses() {
        return new Class<?>[]{MyTestEntity.class};
    }

    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    public void setUp() {
        super.setUp();
        this.createMapping();
        this.loadResponseJsonNode();
    }

    @After
    public void deleteTestData() {
        try (Session s = getSessionFactory().openSession()) {
            FullTextSession session = Search.getFullTextSession(s);
            Transaction tx = s.beginTransaction();

            QueryDescriptor query = ElasticsearchQueries.fromJson("{ 'query': { 'match_all' : {} } }");
            List<?> result = session.createFullTextQuery(query, MyTestEntity.class).list();

            for (Object entity : result) {
                session.delete(entity);
            }

            tx.commit();
        }
    }

    @SuppressWarnings("unchecked")
    private void createMapping() {
        try (Session s = getSessionFactory().openSession()) {
            MyTestEntity yourEntity1 = new MyTestEntity(1L);

            Transaction tx = s.beginTransaction();
            s.persist(yourEntity1);
            tx.commit();

            FullTextSession session = Search.getFullTextSession(s);
            QueryBuilder qb = session.getSearchFactory().buildQueryBuilder().forEntity(MyTestEntity.class).get();
            Query query = qb.all().createQuery();
            List<MyTestEntity> result = (List<MyTestEntity>) session.createFullTextQuery(query).list();
            assertEquals(1, result.size());
            assertEquals((long) 1, (long) result.get(0).getId());
        }
    }

    private void loadResponseJsonNode() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("pretty", "true");
            Response response = getElasticsearchRestClient().performRequest("GET", "/my-index/" + MyTestEntity.class.getCanonicalName() + "/_mapping", params);
            String responseBody = EntityUtils.toString(response.getEntity());
            this.getLogger().info(responseBody);

            ObjectMapper mapper = new ObjectMapper();
            this.responseJsonNode = mapper.readTree(responseBody);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2908")
    @SuppressWarnings("unchecked")
    public void testStringField() throws Exception {
        JsonNode jsonProperties = responseJsonNode.get("my-index").get("mappings")
                .get(MyTestEntity.class.getCanonicalName()).get("properties");
        assertEquals("text", jsonProperties.get("stringWithAnalyze").get("type").textValue());
        assertEquals("keyword", jsonProperties.get("stringWithoutAnalyze").get("type").textValue());
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2908")
    @SuppressWarnings("unchecked")
    public void testBooleanField() throws Exception {
        JsonNode jsonProperties = responseJsonNode.get("my-index").get("mappings")
                .get(MyTestEntity.class.getCanonicalName()).get("properties");
        assertEquals("boolean", jsonProperties.get("booleanWrapWithAnalyze").get("type").textValue());
        assertEquals("boolean", jsonProperties.get("booleanWrapWithoutAnalyze").get("type").textValue());
        assertEquals("boolean", jsonProperties.get("booleanPrimitiveWithAnalyze").get("type").textValue());
        assertEquals("boolean", jsonProperties.get("booleanPrimitiveWithoutAnalyze").get("type").textValue());
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2908")
    @SuppressWarnings("unchecked")
    public void testByteField() throws Exception {
        JsonNode jsonProperties = responseJsonNode.get("my-index").get("mappings")
                .get(MyTestEntity.class.getCanonicalName()).get("properties");
        assertEquals("byte", jsonProperties.get("byteWrapWithAnalyze").get("type").textValue());
        assertEquals("byte", jsonProperties.get("byteWrapWithoutAnalyze").get("type").textValue());
        assertEquals("byte", jsonProperties.get("bytePrimitiveWithAnalyze").get("type").textValue());
        assertEquals("byte", jsonProperties.get("bytePrimitiveWithoutAnalyze").get("type").textValue());
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2908")
    @SuppressWarnings("unchecked")
    public void testIntegerField() throws Exception {
        JsonNode jsonProperties = responseJsonNode.get("my-index").get("mappings")
                .get(MyTestEntity.class.getCanonicalName()).get("properties");
        assertEquals("integer", jsonProperties.get("integerWrapWithAnalyze").get("type").textValue());
        assertEquals("integer", jsonProperties.get("integerWrapWithoutAnalyze").get("type").textValue());
        assertEquals("integer", jsonProperties.get("integerPrimitiveWithAnalyze").get("type").textValue());
        assertEquals("integer", jsonProperties.get("integerPrimitiveWithoutAnalyze").get("type").textValue());
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2908")
    @SuppressWarnings("unchecked")
    public void testLongField() throws Exception {
        JsonNode jsonProperties = responseJsonNode.get("my-index").get("mappings")
                .get(MyTestEntity.class.getCanonicalName()).get("properties");
        assertEquals("long", jsonProperties.get("longWrapWithAnalyze").get("type").textValue());
        assertEquals("long", jsonProperties.get("longWrapWithoutAnalyze").get("type").textValue());
        assertEquals("long", jsonProperties.get("longPrimitiveWithAnalyze").get("type").textValue());
        assertEquals("long", jsonProperties.get("longPrimitiveWithoutAnalyze").get("type").textValue());
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2908")
    @SuppressWarnings("unchecked")
    public void testFloatField() throws Exception {
        JsonNode jsonProperties = responseJsonNode.get("my-index").get("mappings")
                .get(MyTestEntity.class.getCanonicalName()).get("properties");
        assertEquals("float", jsonProperties.get("floatWrapWithAnalyze").get("type").textValue());
        assertEquals("float", jsonProperties.get("floatWrapWithoutAnalyze").get("type").textValue());
        assertEquals("float", jsonProperties.get("floatPrimitiveWithAnalyze").get("type").textValue());
        assertEquals("float", jsonProperties.get("floatPrimitiveWithoutAnalyze").get("type").textValue());
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2908")
    @SuppressWarnings("unchecked")
    public void testDoubleField() throws Exception {
        JsonNode jsonProperties = responseJsonNode.get("my-index").get("mappings")
                .get(MyTestEntity.class.getCanonicalName()).get("properties");
        assertEquals("double", jsonProperties.get("doubleWrapWithAnalyze").get("type").textValue());
        assertEquals("double", jsonProperties.get("doubleWrapWithoutAnalyze").get("type").textValue());
        assertEquals("double", jsonProperties.get("doublePrimitiveWithAnalyze").get("type").textValue());
        assertEquals("double", jsonProperties.get("doublePrimitiveWithoutAnalyze").get("type").textValue());
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2908")
    @SuppressWarnings("unchecked")
    public void testShortField() throws Exception {
        JsonNode jsonProperties = responseJsonNode.get("my-index").get("mappings")
                .get(MyTestEntity.class.getCanonicalName()).get("properties");
        assertEquals("short", jsonProperties.get("shortWrapWithAnalyze").get("type").textValue());
        assertEquals("short", jsonProperties.get("shortWrapWithoutAnalyze").get("type").textValue());
        assertEquals("short", jsonProperties.get("shortPrimitiveWithAnalyze").get("type").textValue());
        assertEquals("short", jsonProperties.get("shortPrimitiveWithoutAnalyze").get("type").textValue());
    }
}
