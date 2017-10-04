package org.hibernate.search.bugs;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Indexed(index = "my-index")
public class MyTestEntity {

    @Id
    @DocumentId
    private Long id;

    @Field(analyze = Analyze.YES)
    private String stringWithAnalyze;

    @Field(analyze = Analyze.NO)
    private String stringWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private Boolean booleanWrapWithAnalyze;

    @Field(analyze = Analyze.NO)
    private Boolean booleanWrapWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private boolean booleanPrimitiveWithAnalyze;

    @Field(analyze = Analyze.NO)
    private boolean booleanPrimitiveWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private Byte byteWrapWithAnalyze;

    @Field(analyze = Analyze.NO)
    private Byte byteWrapWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private byte bytePrimitiveWithAnalyze;

    @Field(analyze = Analyze.NO)
    private byte bytePrimitiveWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private Integer integerWrapWithAnalyze;

    @Field(analyze = Analyze.NO)
    private Integer integerWrapWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private int integerPrimitiveWithAnalyze;

    @Field(analyze = Analyze.NO)
    private int integerPrimitiveWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private Long longWrapWithAnalyze;

    @Field(analyze = Analyze.NO)
    private Long longWrapWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private long longPrimitiveWithAnalyze;

    @Field(analyze = Analyze.NO)
    private long longPrimitiveWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private Float floatWrapWithAnalyze;

    @Field(analyze = Analyze.NO)
    private Float floatWrapWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private float floatPrimitiveWithAnalyze;

    @Field(analyze = Analyze.NO)
    private float floatPrimitiveWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private Double doubleWrapWithAnalyze;

    @Field(analyze = Analyze.NO)
    private Double doubleWrapWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private double doublePrimitiveWithAnalyze;

    @Field(analyze = Analyze.NO)
    private double doublePrimitiveWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private Short shortWrapWithAnalyze;

    @Field(analyze = Analyze.NO)
    private Short shortWrapWithoutAnalyze;

    @Field(analyze = Analyze.YES)
    private short shortPrimitiveWithAnalyze;

    @Field(analyze = Analyze.NO)
    private short shortPrimitiveWithoutAnalyze;

    protected MyTestEntity() {
    }

    public MyTestEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringWithAnalyze() {
        return stringWithAnalyze;
    }

    public void setStringWithAnalyze(String stringWithAnalyze) {
        this.stringWithAnalyze = stringWithAnalyze;
    }

    public String getStringWithoutAnalyze() {
        return stringWithoutAnalyze;
    }

    public void setStringWithoutAnalyze(String stringWithoutAnalyze) {
        this.stringWithoutAnalyze = stringWithoutAnalyze;
    }

    public Boolean getBooleanWrapWithAnalyze() {
        return booleanWrapWithAnalyze;
    }

    public void setBooleanWrapWithAnalyze(Boolean booleanWrapWithAnalyze) {
        this.booleanWrapWithAnalyze = booleanWrapWithAnalyze;
    }

    public Boolean getBooleanWrapWithoutAnalyze() {
        return booleanWrapWithoutAnalyze;
    }

    public void setBooleanWrapWithoutAnalyze(Boolean booleanWrapWithoutAnalyze) {
        this.booleanWrapWithoutAnalyze = booleanWrapWithoutAnalyze;
    }

    public boolean isBooleanPrimitiveWithAnalyze() {
        return booleanPrimitiveWithAnalyze;
    }

    public void setBooleanPrimitiveWithAnalyze(boolean booleanPrimitiveWithAnalyze) {
        this.booleanPrimitiveWithAnalyze = booleanPrimitiveWithAnalyze;
    }

    public boolean isBooleanPrimitiveWithoutAnalyze() {
        return booleanPrimitiveWithoutAnalyze;
    }

    public void setBooleanPrimitiveWithoutAnalyze(boolean booleanPrimitiveWithoutAnalyze) {
        this.booleanPrimitiveWithoutAnalyze = booleanPrimitiveWithoutAnalyze;
    }

    public Byte getByteWrapWithAnalyze() {
        return byteWrapWithAnalyze;
    }

    public void setByteWrapWithAnalyze(Byte byteWrapWithAnalyze) {
        this.byteWrapWithAnalyze = byteWrapWithAnalyze;
    }

    public Byte getByteWrapWithoutAnalyze() {
        return byteWrapWithoutAnalyze;
    }

    public void setByteWrapWithoutAnalyze(Byte byteWrapWithoutAnalyze) {
        this.byteWrapWithoutAnalyze = byteWrapWithoutAnalyze;
    }

    public byte getBytePrimitiveWithAnalyze() {
        return bytePrimitiveWithAnalyze;
    }

    public void setBytePrimitiveWithAnalyze(byte bytePrimitiveWithAnalyze) {
        this.bytePrimitiveWithAnalyze = bytePrimitiveWithAnalyze;
    }

    public byte getBytePrimitiveWithoutAnalyze() {
        return bytePrimitiveWithoutAnalyze;
    }

    public void setBytePrimitiveWithoutAnalyze(byte bytePrimitiveWithoutAnalyze) {
        this.bytePrimitiveWithoutAnalyze = bytePrimitiveWithoutAnalyze;
    }

    public Integer getIntegerWrapWithAnalyze() {
        return integerWrapWithAnalyze;
    }

    public void setIntegerWrapWithAnalyze(Integer integerWrapWithAnalyze) {
        this.integerWrapWithAnalyze = integerWrapWithAnalyze;
    }

    public Integer getIntegerWrapWithoutAnalyze() {
        return integerWrapWithoutAnalyze;
    }

    public void setIntegerWrapWithoutAnalyze(Integer integerWrapWithoutAnalyze) {
        this.integerWrapWithoutAnalyze = integerWrapWithoutAnalyze;
    }

    public int getIntegerPrimitiveWithAnalyze() {
        return integerPrimitiveWithAnalyze;
    }

    public void setIntegerPrimitiveWithAnalyze(int integerPrimitiveWithAnalyze) {
        this.integerPrimitiveWithAnalyze = integerPrimitiveWithAnalyze;
    }

    public int getIntegerPrimitiveWithoutAnalyze() {
        return integerPrimitiveWithoutAnalyze;
    }

    public void setIntegerPrimitiveWithoutAnalyze(int integerPrimitiveWithoutAnalyze) {
        this.integerPrimitiveWithoutAnalyze = integerPrimitiveWithoutAnalyze;
    }

    public Long getLongWrapWithAnalyze() {
        return longWrapWithAnalyze;
    }

    public void setLongWrapWithAnalyze(Long longWrapWithAnalyze) {
        this.longWrapWithAnalyze = longWrapWithAnalyze;
    }

    public Long getLongWrapWithoutAnalyze() {
        return longWrapWithoutAnalyze;
    }

    public void setLongWrapWithoutAnalyze(Long longWrapWithoutAnalyze) {
        this.longWrapWithoutAnalyze = longWrapWithoutAnalyze;
    }

    public long getLongPrimitiveWithAnalyze() {
        return longPrimitiveWithAnalyze;
    }

    public void setLongPrimitiveWithAnalyze(long longPrimitiveWithAnalyze) {
        this.longPrimitiveWithAnalyze = longPrimitiveWithAnalyze;
    }

    public long getLongPrimitiveWithoutAnalyze() {
        return longPrimitiveWithoutAnalyze;
    }

    public void setLongPrimitiveWithoutAnalyze(long longPrimitiveWithoutAnalyze) {
        this.longPrimitiveWithoutAnalyze = longPrimitiveWithoutAnalyze;
    }

    public Float getFloatWrapWithAnalyze() {
        return floatWrapWithAnalyze;
    }

    public void setFloatWrapWithAnalyze(Float floatWrapWithAnalyze) {
        this.floatWrapWithAnalyze = floatWrapWithAnalyze;
    }

    public Float getFloatWrapWithoutAnalyze() {
        return floatWrapWithoutAnalyze;
    }

    public void setFloatWrapWithoutAnalyze(Float floatWrapWithoutAnalyze) {
        this.floatWrapWithoutAnalyze = floatWrapWithoutAnalyze;
    }

    public float getFloatPrimitiveWithAnalyze() {
        return floatPrimitiveWithAnalyze;
    }

    public void setFloatPrimitiveWithAnalyze(float floatPrimitiveWithAnalyze) {
        this.floatPrimitiveWithAnalyze = floatPrimitiveWithAnalyze;
    }

    public float getFloatPrimitiveWithoutAnalyze() {
        return floatPrimitiveWithoutAnalyze;
    }

    public void setFloatPrimitiveWithoutAnalyze(float floatPrimitiveWithoutAnalyze) {
        this.floatPrimitiveWithoutAnalyze = floatPrimitiveWithoutAnalyze;
    }

    public Double getDoubleWrapWithAnalyze() {
        return doubleWrapWithAnalyze;
    }

    public void setDoubleWrapWithAnalyze(Double doubleWrapWithAnalyze) {
        this.doubleWrapWithAnalyze = doubleWrapWithAnalyze;
    }

    public Double getDoubleWrapWithoutAnalyze() {
        return doubleWrapWithoutAnalyze;
    }

    public void setDoubleWrapWithoutAnalyze(Double doubleWrapWithoutAnalyze) {
        this.doubleWrapWithoutAnalyze = doubleWrapWithoutAnalyze;
    }

    public double getDoublePrimitiveWithAnalyze() {
        return doublePrimitiveWithAnalyze;
    }

    public void setDoublePrimitiveWithAnalyze(double doublePrimitiveWithAnalyze) {
        this.doublePrimitiveWithAnalyze = doublePrimitiveWithAnalyze;
    }

    public double getDoublePrimitiveWithoutAnalyze() {
        return doublePrimitiveWithoutAnalyze;
    }

    public void setDoublePrimitiveWithoutAnalyze(double doublePrimitiveWithoutAnalyze) {
        this.doublePrimitiveWithoutAnalyze = doublePrimitiveWithoutAnalyze;
    }

    public Short getShortWrapWithAnalyze() {
        return shortWrapWithAnalyze;
    }

    public void setShortWrapWithAnalyze(Short shortWrapWithAnalyze) {
        this.shortWrapWithAnalyze = shortWrapWithAnalyze;
    }

    public Short getShortWrapWithoutAnalyze() {
        return shortWrapWithoutAnalyze;
    }

    public void setShortWrapWithoutAnalyze(Short shortWrapWithoutAnalyze) {
        this.shortWrapWithoutAnalyze = shortWrapWithoutAnalyze;
    }

    public short getShortPrimitiveWithAnalyze() {
        return shortPrimitiveWithAnalyze;
    }

    public void setShortPrimitiveWithAnalyze(short shortPrimitiveWithAnalyze) {
        this.shortPrimitiveWithAnalyze = shortPrimitiveWithAnalyze;
    }

    public short getShortPrimitiveWithoutAnalyze() {
        return shortPrimitiveWithoutAnalyze;
    }

    public void setShortPrimitiveWithoutAnalyze(short shortPrimitiveWithoutAnalyze) {
        this.shortPrimitiveWithoutAnalyze = shortPrimitiveWithoutAnalyze;
    }
}
