package vn.gmostore.basic.dto;

import org.hibernate.validator.constraints.NotEmpty;

import vn.gmostore.basic.model.Platform;

public class PlatformDto implements Dto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String icon;
    private String customeCode;
    private int order;
    private long createDate;
    private Long updateDate;

    public PlatformDto() {
    }

    public PlatformDto(Platform platform) {
        if (platform == null)
            return;
        this.id = platform.getId();
        this.name = platform.getName();
        this.description = platform.getDescription();
        this.icon = platform.getIcon();
        this.customeCode = platform.getCustomeCode();
        this.createDate = platform.getCreateDate();
        this.updateDate = platform.getUpdateDate();
        this.setOrder(platform.getOrder());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCustomeCode() {
        return customeCode;
    }

    public void setCustomeCode(String customeCode) {
        this.customeCode = customeCode;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Platform from() {
        Platform platform = new Platform();
        platform.setId(id);
        platform.setName(name);
        platform.setOrder(order);
        platform.setDescription(description);
        platform.setIcon(icon);
        platform.setCustomeCode(customeCode);
        platform.setCreateDate(createDate);
        platform.setUpdateDate(updateDate);

        return platform;
    }
}
