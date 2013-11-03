package vn.gmostore.basic.dto;

import java.util.Date;

import vn.gmostore.basic.model.Product;

public class ProductPropertiesDto {
    private String someString;
    private Integer someNumber;
    private Date someDate;
    private Product product;

    public ProductPropertiesDto() {
        this.someString = "";
        this.someNumber = 0;
        this.someDate = new Date();
    }

    public ProductPropertiesDto(String someString, Integer someNumber, Date someDate) {
        this.someString = someString;
        this.someNumber = someNumber;
        this.someDate = someDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public Integer getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(Integer someNumber) {
        this.someNumber = someNumber;
    }

    public Date getSomeDate() {
        return someDate;
    }

    public void setSomeDate(Date someDate) {
        this.someDate = someDate;
    }
}
