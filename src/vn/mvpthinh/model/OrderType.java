package vn.mvpthinh.model;

public enum OrderType {
    IN ("IN"),
    OUT ("OUT");

    private String value;

    private OrderType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static OrderType parseOrderType(String value) {
        OrderType[] values = values();
        for (OrderType role : values) {
            if (role.value.equals(value))
                return role;
        }
        throw new IllegalArgumentException("invalid");
    }
}
