package vn.mvpthinh.model;

public enum OrderStatus {
    NEW ("NEW"),
    CHECKOUT ("CHECKOUT"),
    PAID ("PAID"),
    FAILED ("FAILED"),
    SHIPPED ("SHIPPED"),
    DELIVERED ("DELIVERED"),
    RETURNED ("RETURNED"),
    COMPLETE ("COMPLETE");


    private String value;

    private OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static OrderStatus parseOrderStatus(String value) {
        OrderStatus[] values = values();
        for (OrderStatus role : values) {
            if (role.value.equals(value))
                return role;
        }
        throw new IllegalArgumentException("invalid");
    }
}
