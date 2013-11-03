package vn.gmostore.basic.dto;

import vn.gmostore.basic.model.Product;

public class ProductDetailDto extends ProductDto implements Dto {

    public ProductDetailDto(Product product) {
        super(product);
    }

    public ProductDetailDto() {
    }

    //    @JsonIgnore
    //    private Platform platform;
    //    @JsonIgnore
    //    private Publisher publisher;
    //    @JsonIgnore
    //    private String fullName;
    //    private String packageName;
    //    private String currentVersion;
    //    private String promotionText;
    //    private String promotionImageUrl;
    //    private String description;
    //    private String recentChange;
    //    private String icon;
    //    private String status;
    //    private String videoUrl;
    //    private Boolean recommand;
    //    private int viewCount;
    //    private int sharedCount;
    //    private long createDate;
    //    private Long updateDate;
    //    private Long deleteDate;
    //    @JsonIgnore
    //    private Set<ScreenShot> screenShots = new HashSet<ScreenShot>(0);
    //    @JsonIgnore
    //    private Set<WishList> wishLists = new HashSet<WishList>(0);
    //    @JsonIgnore
    //    private Set<Rating> ratings = new HashSet<Rating>(0);
    //    @JsonIgnore
    //    private Set<Price> prices = new HashSet<Price>(0);
    //    @JsonIgnore
    //    private Set<HistoryTransaction> historyTransactions = new HashSet<HistoryTransaction>(0);
    //    @JsonIgnore
    //    private Set<HistoryAction> historyActions = new HashSet<HistoryAction>(0);
    //    @JsonIgnore
    //    private Set<Promotion> promotions = new HashSet<Promotion>(0);
    //    @JsonIgnore
    //    private Set<Version> versions = new HashSet<Version>(0);
    //    @JsonIgnore
    //    private Set<Comment> comments = new HashSet<Comment>(0);
}
