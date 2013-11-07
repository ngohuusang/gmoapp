package vn.gmostore.basic.dto;

import vn.gmostore.basic.model.Product;

public class ProductDto implements Dto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    protected Integer id;
    protected String fullName;
    protected String publisher;
    protected String packageName;
    protected String currentVersion;
    protected String icon;
    protected long currentGold;
    protected long currentDiamon;
    protected String downloadLink;
    protected int commentCount;
    protected int viewCount;
    protected int sharedCount;
    protected int ratedPoint;
    protected int fileSize;
    protected long createDate;
    protected Long updateDate;

    public ProductDto(Product product) {
        if (product == null)
            return;
        this.id = product.getId();
        this.fullName = product.getFullName();
        this.publisher = product.getPublisher().getName();
        this.packageName = product.getPackageName();
        this.currentVersion = product.getCurrentVersion();
        this.icon = product.getIcon();
        this.commentCount = product.getComments().size();
        this.viewCount = product.getViewCount();
        this.sharedCount = product.getSharedCount();
    }

    public ProductDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getSharedCount() {
        return sharedCount;
    }

    public void setSharedCount(int sharedCount) {
        this.sharedCount = sharedCount;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getRatedPoint() {
        return ratedPoint;
    }

    public void setRatedPoint(int ratedPoint) {
        this.ratedPoint = ratedPoint;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public long getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(long currentGold) {
        this.currentGold = currentGold;
    }

    public long getCurrentDiamon() {
        return currentDiamon;
    }

    public void setCurrentDiamon(long currentDiamon) {
        this.currentDiamon = currentDiamon;
    }
    
}
