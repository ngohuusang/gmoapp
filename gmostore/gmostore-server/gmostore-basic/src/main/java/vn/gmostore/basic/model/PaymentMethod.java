package vn.gmostore.basic.model;

// default package
// Generated Oct 27, 2013 8:20:49 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * PaymentMethod generated by hbm2java
 */
@Entity
@Table(name = "payment_method", catalog = "gmostore_db", uniqueConstraints = @UniqueConstraint(columnNames = "type"))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PaymentMethod extends AbstractDomain<Integer> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String type;
    private String name;
    private long createDate;
    private Long updateDate;
    private Long deleteDate;
    private Set<HistoryRecharge> historyRecharges = new HashSet<HistoryRecharge>(0);

    public PaymentMethod() {
    }

    public PaymentMethod(String type, String name, long createDate) {
        this.type = type;
        this.name = name;
        this.createDate = createDate;
    }

    public PaymentMethod(String type, String name, long createDate, Long updateDate, Long deleteDate, Set<HistoryRecharge> historyRecharges) {
        this.type = type;
        this.name = name;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
        this.historyRecharges = historyRecharges;
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

    @Column(name = "type", unique = true, nullable = false, length = 20)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentMethod")
    public Set<HistoryRecharge> getHistoryRecharges() {
        return this.historyRecharges;
    }

    public void setHistoryRecharges(Set<HistoryRecharge> historyRecharges) {
        this.historyRecharges = historyRecharges;
    }

}
