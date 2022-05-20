package vn.mvpthinh.model;

import java.time.Instant;

public class OrderItem {
    private Long id;
    private String productId;
    private int itemId;
    private int orderId;
    private double price;
    private int quantity;
    private Instant createdAt;
    private Instant updatedAt;
    private String content;

    public OrderItem() {
    }

    public OrderItem(Long id, String productId, int itemId, int orderId, double price, int quantity, Instant createdAt, Instant updatedAt, String content) {
        this.id = id;
        this.productId = productId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public static OrderItem parse(String record) {
        String[] filed = record.split(",");
        OrderItem orderItem = new OrderItem();

        orderItem.id = Long.parseLong(filed[0]);
        orderItem.productId = filed[1];
        orderItem.itemId = Integer.parseInt(filed[2]);
        orderItem.orderId = Integer.parseInt(filed[3]);
        orderItem.price = Double.parseDouble(filed[4]);
        orderItem.quantity = Integer.parseInt(filed[5]);
        orderItem.createdAt = Instant.parse(filed[6]);
        orderItem.updatedAt = null;
        String temp = filed[7];
        orderItem.content = filed[8];

        if (temp != null && !temp.equals("null"))
            orderItem.updatedAt = Instant.parse(temp);

        return orderItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return  id + "," + productId + "," + itemId + "," + orderId + "," + price + "," + quantity + "," + createdAt + "," + updatedAt + "," + content;
    }
}
