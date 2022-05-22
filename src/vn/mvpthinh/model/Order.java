package vn.mvpthinh.model;

import java.time.Instant;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private OrderType orderType;
    private OrderStatus status;
    private double grandTotal;
    private Instant createdAt;
    private Instant updateAt;
    private String content;
    List<OrderItem> orderItems;

    public Order() {
    }


    public Order(Long id, OrderType orderType) {
        this.id = id;
        this.orderType = orderType;
    }

    public Order(Long id, Long userId, OrderType type, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.orderType = type;
        this.status = status;
    }

    public static Order parse(String record) {
        Order order = new Order();
        String[] field = record.split(",");
        order.id = Long.parseLong(field[0]);
        order.userId = Long.parseLong(field[1]);
        order.orderType = OrderType.parseOrderType(field[2]);
        order.status = OrderStatus.parseOrderStatus(field[3]);
        order.grandTotal = Double.parseDouble(field[4]);
        order.createdAt = Instant.parse(field[5]);
        order.updateAt = null;
        String temp = field[6];
        order.content = field[7];

        if (temp != null && !temp.equals("null")) order.updateAt = Instant.parse(temp);

        return order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderType getType() {
        return orderType;
    }

    public void setType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                id,
                userId,
                orderType,
                status,
                grandTotal,
                createdAt,
                updateAt,
                content
        );
    }
}
