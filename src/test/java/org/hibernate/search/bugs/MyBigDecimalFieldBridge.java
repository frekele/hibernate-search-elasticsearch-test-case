package org.hibernate.search.bugs;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.MetadataProvidingFieldBridge;
import org.hibernate.search.bridge.TwoWayFieldBridge;
import org.hibernate.search.bridge.spi.FieldMetadataBuilder;
import org.hibernate.search.bridge.spi.FieldType;
import org.hibernate.search.util.StringHelper;

import java.math.BigDecimal;

public class MyBigDecimalFieldBridge implements MetadataProvidingFieldBridge, TwoWayFieldBridge {

    @Override
    public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
        if (value != null) {
            BigDecimal bigDecimal = (BigDecimal) value;
            Double indexedValue = bigDecimal.doubleValue();
            luceneOptions.addNumericFieldToDocument(name, indexedValue, document);
        }
    }

    @Override
    public Object get(String name, Document document) {
        String fromLucene = document.get(name);
        return StringHelper.isEmpty(fromLucene) ? null : new BigDecimal(fromLucene);
    }

    @Override
    public String objectToString(Object object) {
        return object == null ? null : object.toString();
    }

    @Override
    public void configureFieldMetadata(String name, FieldMetadataBuilder builder) {
        builder.field(name, FieldType.DOUBLE);
    }
}