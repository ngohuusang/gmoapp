package vn.gmostore.basic.model;

// default package
// Generated Oct 27, 2013 8:20:49 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ListTypeHasProductId generated by hbm2java
 */
@Embeddable
public class ListTypeHasProductId implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int listTypeId;
    private int productId;

    public ListTypeHasProductId() {
    }

    public ListTypeHasProductId(int listTypeId, int productId) {
        this.listTypeId = listTypeId;
        this.productId = productId;
    }

    @Column(name = "list_type_id", nullable = false)
    public int getListTypeId() {
        return this.listTypeId;
    }

    public void setListTypeId(int listTypeId) {
        this.listTypeId = listTypeId;
    }

    @Column(name = "product_id", nullable = false)
    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ListTypeHasProductId))
            return false;
        ListTypeHasProductId castOther = (ListTypeHasProductId) other;

        return (this.getListTypeId() == castOther.getListTypeId()) && (this.getProductId() == castOther.getProductId());
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 37 * result + this.getListTypeId();
        result = 37 * result + this.getProductId();
        return result;
    }

}