package org.hibernate.search.bugs;

import org.apache.lucene.search.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.elasticsearch.ElasticsearchQueries;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.QueryDescriptor;
import org.hibernate.search.testsupport.TestForIssue;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class MyBigDecimalFieldBridgeIT extends SearchTestBase {

    @Override
    public Class<?>[] getAnnotatedClasses() {
        return new Class<?>[]{ProductEntity.class};
    }

    @Test
    @TestForIssue(jiraKey = "HSEARCH-2906") // Please fill in the JIRA key of your issue
    @SuppressWarnings("unchecked")
    public void testHSEARCH2906Fixed() {
        try (Session s = getSessionFactory().openSession()) {
            ProductEntity yourEntity1 = new ProductEntity(1L, "table", BigDecimal.valueOf(344.89));
            ProductEntity yourEntity2 = new ProductEntity(2L, "chair", BigDecimal.valueOf(132.54));

            Transaction tx = s.beginTransaction();
            s.persist(yourEntity1);
            s.persist(yourEntity2);
            tx.commit();

            FullTextSession session = Search.getFullTextSession(s);
            QueryBuilder qb = session.getSearchFactory().buildQueryBuilder().forEntity(ProductEntity.class).get();

            Query query = qb.keyword().onField("name").matching("table").createQuery();
            List<ProductEntity> result = (List<ProductEntity>) session.createFullTextQuery(query).list();
            assertEquals(1, result.size());
            assertEquals((long) 1, (long) result.get(0).getId());
            assertEquals(BigDecimal.valueOf(344.89), result.get(0).getPrice());

            query = qb.keyword().onField("name").matching("chair").createQuery();
            result = (List<ProductEntity>) session.createFullTextQuery(query).list();
            assertEquals(1, result.size());
            assertEquals((long) 2, (long) result.get(0).getId());
            assertEquals(BigDecimal.valueOf(132.54), result.get(0).getPrice());
        }
    }

    @After
    public void deleteTestData() {
        try (Session s = getSessionFactory().openSession()) {
            FullTextSession session = Search.getFullTextSession(s);
            Transaction tx = s.beginTransaction();

            QueryDescriptor query = ElasticsearchQueries.fromJson("{ 'query': { 'match_all' : {} } }");
            List<?> result = session.createFullTextQuery(query, ProductEntity.class).list();

            for (Object entity : result) {
                session.delete(entity);
            }

            tx.commit();
        }
    }
}
