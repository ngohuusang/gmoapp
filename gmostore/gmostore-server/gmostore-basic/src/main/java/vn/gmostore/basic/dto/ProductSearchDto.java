package vn.gmostore.basic.dto;

import vn.gmostore.basic.util.OrderBy;
import vn.gmostore.basic.util.OrderBy.OrderType;

public class ProductSearchDto implements Dto {

    /**
     * 
     */
    public enum ProductColumnSupported {
        fullName, viewCount, sharedCount, createDate, updateDate
    }

    private static final long serialVersionUID = 1L;

    private Integer platformId;
    private Integer categoryId;
    private int offset;
    private int limit;
    private OrderBy[] orderBies;

    public ProductSearchDto() {
    }

    public ProductSearchDto(Integer platformId, Integer categoryId, int offset, int limit, OrderBy[] orderBies) {
        this.platformId = platformId;
        this.categoryId = categoryId;
        this.offset = offset;
        this.limit = limit;
        this.orderBies = orderBies;
    }

    public static OrderBy[] getLatestOrders() {
        return new OrderBy[] { new OrderBy(ProductColumnSupported.updateDate.toString(), OrderType.DESC),
                new OrderBy(ProductColumnSupported.createDate.toString(), OrderType.DESC),
                new OrderBy(ProductColumnSupported.fullName.toString(), OrderType.ASC) };
    }

    public static OrderBy[] getFullNameOrders() {
        return new OrderBy[] { new OrderBy(ProductColumnSupported.fullName.toString(), OrderType.ASC) };
    }

    public static OrderBy[] getTopViewsOrders() {
        return new OrderBy[] { new OrderBy(ProductColumnSupported.viewCount.toString(), OrderType.DESC),
                new OrderBy(ProductColumnSupported.fullName.toString(), OrderType.ASC) };
    }

    public static OrderBy[] getTopSharedsOrders() {
        return new OrderBy[] { new OrderBy(ProductColumnSupported.sharedCount.toString(), OrderType.DESC),
                new OrderBy(ProductColumnSupported.fullName.toString(), OrderType.ASC) };
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public OrderBy[] getOrderBies() {
        return orderBies;
    }

    public void setOrderBies(OrderBy[] orderBies) {
        this.orderBies = orderBies;
    }
}
