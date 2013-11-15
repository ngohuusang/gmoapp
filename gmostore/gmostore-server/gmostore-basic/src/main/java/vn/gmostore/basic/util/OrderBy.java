package vn.gmostore.basic.util;

public class OrderBy {
    public enum OrderType {
        ASC, DESC
    }

    private String column;
    private OrderType orderType = OrderType.DESC;

    public OrderBy() {
    }

    public OrderBy(String column, String type) {
        this.column = column;
        this.orderType = valueOf(type);
    }

    public OrderBy(String column, OrderType orderType) {
        this.column = column;
        this.orderType = orderType;
    }

    private OrderType valueOf(String sType) {
        for (OrderType type : OrderType.values()) {
            if (type.toString().equalsIgnoreCase(sType)) {
                return type;
            }
        }
        return OrderType.DESC;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

}
