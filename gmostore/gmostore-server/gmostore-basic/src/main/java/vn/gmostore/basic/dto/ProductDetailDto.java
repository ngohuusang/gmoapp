package vn.gmostore.basic.dto;

import java.util.HashSet;
import java.util.Set;

import vn.gmostore.basic.model.Comment;
import vn.gmostore.basic.model.Product;
import vn.gmostore.basic.model.ScreenShot;

public class ProductDetailDto extends ProductDto implements Dto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String promotionText;
    private String promotionImageUrl;
    private String description;
    private String recentChange;
    private String status;
    private String videoUrl;
    private Boolean recommand;
    private Set<ScreenShotDto> screenShots = new HashSet<ScreenShotDto>(0);
    private Set<CommentDto> comments = new HashSet<CommentDto>(0);

    public ProductDetailDto() {
    }

    public ProductDetailDto(Product product) {
        super(product);
        this.promotionText = product.getPromotionText();
        this.promotionImageUrl = product.getPromotionImageUrl();
        this.description = product.getDescription();
        this.recentChange = product.getRecentChange();
        this.status = product.getStatus();
        this.videoUrl = product.getVideoUrl();
        this.recommand = product.getRecommend();
        this.screenShots = getScreenShots(product);
        this.comments = getComments(product);
    }

    private Set<ScreenShotDto> getScreenShots(Product product) {
        Set<ScreenShotDto> results = new HashSet<ScreenShotDto>();
        if (product.getScreenShots() != null) {
            for (ScreenShot screenShot : product.getScreenShots()) {
                results.add(new ScreenShotDto(screenShot));
            }
        }
        return results;
    }

    private Set<CommentDto> getComments(Product product) {
        Set<CommentDto> results = new HashSet<CommentDto>();
        if (product.getComments() != null) {
            for (Comment comment : product.getComments()) {
                results.add(new CommentDto(comment));
            }
        }
        return results;
    }

    public String getPromotionText() {
        return promotionText;
    }

    public void setPromotionText(String promotionText) {
        this.promotionText = promotionText;
    }

    public String getPromotionImageUrl() {
        return promotionImageUrl;
    }

    public void setPromotionImageUrl(String promotionImageUrl) {
        this.promotionImageUrl = promotionImageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecentChange() {
        return recentChange;
    }

    public void setRecentChange(String recentChange) {
        this.recentChange = recentChange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Boolean getRecommand() {
        return recommand;
    }

    public void setRecommand(Boolean recommand) {
        this.recommand = recommand;
    }

    public Set<ScreenShotDto> getScreenShots() {
        return screenShots;
    }

    public void setScreenShots(Set<ScreenShotDto> screenShots) {
        this.screenShots = screenShots;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }
}
