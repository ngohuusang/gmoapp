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
import org.hibernate.annotations.NaturalId;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "gmostore_db", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class User extends AbstractDomain<Integer> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String name;
    private Byte sex;
    private Long birthday;
    private String status;
    private int gold;
    private int diamonds;
    private long createDate;
    private Long updateDate;
    private Long activeDate;
    private Long deleteDate;
    private Set<UserDevice> userDevices = new HashSet<UserDevice>(0);
    private Set<Comment> comments = new HashSet<Comment>(0);
    private Set<WishList> wishLists = new HashSet<WishList>(0);
    private Set<Sns> snses = new HashSet<Sns>(0);
    private Set<HistoryRecharge> historyRecharges = new HashSet<HistoryRecharge>(0);
    private Set<Rating> ratings = new HashSet<Rating>(0);
    private Set<HistoryTransaction> historyTransactions = new HashSet<HistoryTransaction>(0);
    private Set<HistoryAction> historyActions = new HashSet<HistoryAction>(0);
    private Set<StatusHistory> statusHistories = new HashSet<StatusHistory>(0);

    public User() {
    }

    public User(String username, String status) {
        this.username = username;
        this.status = status;
    }

    public User(String username, String name, Byte sex, Long birthday, String status, int gold, int diamonds, long createDate, Long updateDate,
            Long activeDate, Long deleteDate, Set<UserDevice> userDevices, Set<Comment> comments, Set<WishList> wishLists, Set<Sns> snses,
            Set<HistoryRecharge> historyRecharges, Set<Rating> ratings, Set<HistoryTransaction> historyTransactions, Set<HistoryAction> historyActions,
            Set<StatusHistory> statusHistories) {
        this.username = username;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.status = status;
        this.gold = gold;
        this.diamonds = diamonds;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.activeDate = activeDate;
        this.deleteDate = deleteDate;
        this.userDevices = userDevices;
        this.comments = comments;
        this.wishLists = wishLists;
        this.snses = snses;
        this.historyRecharges = historyRecharges;
        this.ratings = ratings;
        this.historyTransactions = historyTransactions;
        this.historyActions = historyActions;
        this.statusHistories = statusHistories;
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

    @NaturalId(mutable = false)
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
    @Column(name = "username", unique = true, nullable = false, length = 100)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "name", length = 100)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "sex")
    public Byte getSex() {
        return this.sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    @Column(name = "birthday")
    public Long getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    @Column(name = "status", nullable = false, length = 9)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "gold")
    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Column(name = "diamonds")
    public int getDiamonds() {
        return this.diamonds;
    }

    public void setDiamonds(int diamonds) {
        this.diamonds = diamonds;
    }

    @Column(name = "create_date")
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

    @Column(name = "active_date")
    public Long getActiveDate() {
        return this.activeDate;
    }

    public void setActiveDate(Long activeDate) {
        this.activeDate = activeDate;
    }

    @Column(name = "delete_date")
    public Long getDeleteDate() {
        return this.deleteDate;
    }

    public void setDeleteDate(Long deleteDate) {
        this.deleteDate = deleteDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<UserDevice> getUserDevices() {
        return this.userDevices;
    }

    public void setUserDevices(Set<UserDevice> userDevices) {
        this.userDevices = userDevices;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<WishList> getWishLists() {
        return this.wishLists;
    }

    public void setWishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Sns> getSnses() {
        return this.snses;
    }

    public void setSnses(Set<Sns> snses) {
        this.snses = snses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<HistoryRecharge> getHistoryRecharges() {
        return this.historyRecharges;
    }

    public void setHistoryRecharges(Set<HistoryRecharge> historyRecharges) {
        this.historyRecharges = historyRecharges;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Rating> getRatings() {
        return this.ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<HistoryTransaction> getHistoryTransactions() {
        return this.historyTransactions;
    }

    public void setHistoryTransactions(Set<HistoryTransaction> historyTransactions) {
        this.historyTransactions = historyTransactions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<HistoryAction> getHistoryActions() {
        return this.historyActions;
    }

    public void setHistoryActions(Set<HistoryAction> historyActions) {
        this.historyActions = historyActions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<StatusHistory> getStatusHistories() {
        return this.statusHistories;
    }

    public void setStatusHistories(Set<StatusHistory> statusHistories) {
        this.statusHistories = statusHistories;
    }

}
