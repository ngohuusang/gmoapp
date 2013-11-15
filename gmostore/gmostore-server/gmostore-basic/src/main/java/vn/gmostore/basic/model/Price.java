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
 * Price generated by hbm2java
 */
@Entity
@Table(name = "price", catalog = "gmostore_db")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Price extends AbstractDomain<Integer> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Product product;
    private int gold;
    private int diamond;
    private long createDate;
    private Long startDate;
    private Long endDate;
    private Long updateDate;
    private Long deleteDate;

    public Price() {
    }

    public Price(Product product, int gold, int diamond, long createDate) {
        this.product = product;
        this.gold = gold;
        this.diamond = diamond;
        this.createDate = createDate;
    }

    public Price(Product product, int gold, int diamond, long createDate, Long startDate, Long endDate, Long updateDate, Long deleteDate) {
        this.product = product;
        this.gold = gold;
        this.diamond = diamond;
        this.createDate = createDate;
        this.startDate = startDate;
        this.endDate = endDate;
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
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "gold", nullable = false)
    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Column(name = "diamond", nullable = false)
    public int getDiamond() {
        return this.diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    @Column(name = "create_date", nullable = false)
    public long getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    @Column(name = "start_date")
    public Long getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public Long getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
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
