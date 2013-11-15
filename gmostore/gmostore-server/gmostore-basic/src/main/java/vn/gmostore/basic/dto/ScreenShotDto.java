package vn.gmostore.basic.dto;

import vn.gmostore.basic.model.ScreenShot;

public class ScreenShotDto implements Dto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String imageUrl;
    private String deviceType;
    private Boolean status;
    private long createDate;

    public ScreenShotDto(ScreenShot screenShot) {
        if (screenShot == null)
            return;
        this.id = screenShot.getId();
        this.imageUrl = screenShot.getImageUrl();
        this.deviceType = screenShot.getDeviceType();
        this.status = screenShot.getStatus();
        this.createDate = screenShot.getCreateDate();
    }

    public ScreenShotDto(Integer id, String imageUrl, String deviceType, Boolean status, long createDate) {
        super();
        this.id = id;
        this.imageUrl = imageUrl;
        this.deviceType = deviceType;
        this.status = status;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
}
