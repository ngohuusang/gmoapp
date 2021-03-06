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
 * HistoryTransaction generated by hbm2java
 */
@Entity
@Table(name = "history_transaction", catalog = "gmostore_db")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HistoryTransaction extends AbstractDomain<Integer> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private User user;
    private Promotion promotion;
    private Product product;
    private long purchasedDate;
    private double price;
    private int point;
    private String description;

    public HistoryTransaction() {
    }

    public HistoryTransaction(User user, Promotion promotion, Product product, long purchasedDate, double price, int point, String description) {
        this.user = user;
        this.promotion = promotion;
        this.product = product;
        this.purchasedDate = purchasedDate;
        this.price = price;
        this.point = point;
        this.description = description;
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
    @JoinColumn(name = "promotion_id", nullable = false)
    public Promotion getPromotion() {
        return this.promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "purchased_date", nullable = false)
    public long getPurchasedDate() {
        return this.purchasedDate;
    }

    public void setPurchasedDate(long purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    @Column(name = "price", nullable = false, precision = 22, scale = 0)
    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "point", nullable = false)
    public int getPoint() {
        return this.point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
