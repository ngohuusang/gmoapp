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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Indexed;

import com.gwtplatform.dispatch.shared.Result;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name = "product", catalog = "gmostore_db")
@Indexed(index = "Products")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product extends AbstractDomain<Integer> implements Result {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonIgnore
    private Platform platform;
    @JsonIgnore
    private Publisher publisher;
    @JsonIgnore
    private Category category;
    private String fullName;
    private String packageName;
    private String currentVersion;
    private String promotionText;
    private String promotionImageUrl;
    private String description;
    private String recentChange;
    private String icon;
    private String status;
    private String videoUrl;
    private Boolean recommand;
    private int viewCount;
    private int sharedCount;
    private long createDate;
    private Long updateDate;
    private Long deleteDate;
    @JsonIgnore
    private Set<ScreenShot> screenShots = new HashSet<ScreenShot>(0);
    @JsonIgnore
    private Set<WishList> wishLists = new HashSet<WishList>(0);
    @JsonIgnore
    private Set<Rating> ratings = new HashSet<Rating>(0);
    @JsonIgnore
    private Set<Price> prices = new HashSet<Price>(0);
    @JsonIgnore
    private Set<HistoryTransaction> historyTransactions = new HashSet<HistoryTransaction>(0);
    @JsonIgnore
    private Set<HistoryAction> historyActions = new HashSet<HistoryAction>(0);
    @JsonIgnore
    private Set<Promotion> promotions = new HashSet<Promotion>(0);
    @JsonIgnore
    private Set<Version> versions = new HashSet<Version>(0);
    @JsonIgnore
    private Set<Comment> comments = new HashSet<Comment>(0);
    @JsonIgnore
    private Set<ListTypeHasProduct> listTypeHasProducts = new HashSet<ListTypeHasProduct>(0);

    public Product() {
    }

    public Product(Platform platform, Publisher publisher, Category category, String fullName, String packageName, String description, String icon,
            int viewCount, int sharedCount, long createDate) {
        this.platform = platform;
        this.publisher = publisher;
        this.category = category;
        this.fullName = fullName;
        this.packageName = packageName;
        this.description = description;
        this.icon = icon;
        this.viewCount = viewCount;
        this.sharedCount = sharedCount;
        this.createDate = createDate;
    }

    public Product(Platform platform, Publisher publisher, Category category, String fullName, String packageName, String currentVersion, String promotionText,
            String promotionImageUrl, String description, String recentChange, String icon, String status, String videoUrl, Boolean recommand, int viewCount,
            int sharedCount, long createDate, Long updateDate, Long deleteDate, Set<ScreenShot> screenShots, Set<WishList> wishLists, Set<Rating> ratings,
            Set<Price> prices, Set<HistoryTransaction> historyTransactions, Set<HistoryAction> historyActions, Set<Promotion> promotions,
            Set<Version> versions, Set<Comment> comments, Set<ListTypeHasProduct> listTypeHasProducts) {
        this.platform = platform;
        this.publisher = publisher;
        this.category = category;
        this.fullName = fullName;
        this.packageName = packageName;
        this.currentVersion = currentVersion;
        this.promotionText = promotionText;
        this.promotionImageUrl = promotionImageUrl;
        this.description = description;
        this.recentChange = recentChange;
        this.icon = icon;
        this.status = status;
        this.videoUrl = videoUrl;
        this.recommand = recommand;
        this.viewCount = viewCount;
        this.sharedCount = sharedCount;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
        this.screenShots = screenShots;
        this.wishLists = wishLists;
        this.ratings = ratings;
        this.prices = prices;
        this.historyTransactions = historyTransactions;
        this.historyActions = historyActions;
        this.promotions = promotions;
        this.versions = versions;
        this.comments = comments;
        this.listTypeHasProducts = listTypeHasProducts;
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
    @JoinColumn(name = "platform_id", nullable = false)
    public Platform getPlatform() {
        return this.platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id", nullable = false)
    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "full_name", nullable = false, length = 100)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "package_name", nullable = false, length = 20)
    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Column(name = "current_version", length = 20)
    public String getCurrentVersion() {
        return this.currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Column(name = "promotion_text", length = 65535)
    public String getPromotionText() {
        return this.promotionText;
    }

    public void setPromotionText(String promotionText) {
        this.promotionText = promotionText;
    }

    @Column(name = "promotion_image_url")
    public String getPromotionImageUrl() {
        return this.promotionImageUrl;
    }

    public void setPromotionImageUrl(String promotionImageUrl) {
        this.promotionImageUrl = promotionImageUrl;
    }

    @Column(name = "description", nullable = false, length = 65535)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "recent_change", length = 65535)
    public String getRecentChange() {
        return this.recentChange;
    }

    public void setRecentChange(String recentChange) {
        this.recentChange = recentChange;
    }

    @Column(name = "icon", nullable = false)
    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "status", length = 10)
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "video_url")
    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Column(name = "recommand")
    public Boolean getRecommand() {
        return this.recommand;
    }

    public void setRecommand(Boolean recommand) {
        this.recommand = recommand;
    }

    @Column(name = "view_count", nullable = false)
    public int getViewCount() {
        return this.viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    @Column(name = "shared_count", nullable = false)
    public int getSharedCount() {
        return this.sharedCount;
    }

    public void setSharedCount(int sharedCount) {
        this.sharedCount = sharedCount;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<ScreenShot> getScreenShots() {
        return this.screenShots;
    }

    public void setScreenShots(Set<ScreenShot> screenShots) {
        this.screenShots = screenShots;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<WishList> getWishLists() {
        return this.wishLists;
    }

    public void setWishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<Rating> getRatings() {
        return this.ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<Price> getPrices() {
        return this.prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<HistoryTransaction> getHistoryTransactions() {
        return this.historyTransactions;
    }

    public void setHistoryTransactions(Set<HistoryTransaction> historyTransactions) {
        this.historyTransactions = historyTransactions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<HistoryAction> getHistoryActions() {
        return this.historyActions;
    }

    public void setHistoryActions(Set<HistoryAction> historyActions) {
        this.historyActions = historyActions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<Promotion> getPromotions() {
        return this.promotions;
    }

    public void setPromotions(Set<Promotion> promotions) {
        this.promotions = promotions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<Version> getVersions() {
        return this.versions;
    }

    public void setVersions(Set<Version> versions) {
        this.versions = versions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<ListTypeHasProduct> getListTypeHasProducts() {
        return this.listTypeHasProducts;
    }

    public void setListTypeHasProducts(Set<ListTypeHasProduct> listTypeHasProducts) {
        this.listTypeHasProducts = listTypeHasProducts;
    }

}