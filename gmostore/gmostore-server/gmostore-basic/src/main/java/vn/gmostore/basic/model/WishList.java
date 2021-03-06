package vn.gmostore.basic.model;

// default package
// Generated Oct 27, 2013 8:20:49 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * WishList generated by hbm2java
 */
@Entity
@Table(name = "wish_list", catalog = "gmostore_db")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WishList extends AbstractDomain<Integer> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private User user;
    private Product product;
    private long createDate;
    private Long updateDate;
    private Long deleteDate;

    public WishList() {
    }

    public WishList(User user, Product product, long createDate) {
        this.user = user;
        this.product = product;
        this.createDate = createDate;
    }

    public WishList(User user, Product product, long createDate, Long updateDate, Long deleteDate) {
        this.user = user;
        this.product = product;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }

    @Override
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "create_date", nullable = false)
    public long getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Column(name = "update_date")
    public Long getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "delete_date")
    public Long getDeleteDate() {
        return this.deleteDate;
    }

    public void setDeleteDate(Long deleteDate) {
        this.deleteDate = deleteDate;
    }

}
