package vn.mvpthinh.model;

import java.time.Instant;

public class Item {
    private Long id;
    private String productId;
    private int orderId;
    private String createdBy;
    private String updatedBy;
    private double price;
    private int quantity;
    private double sold;
    private int available;
    private Instant createdAt;
    private Instant updatedAt;

    public Item() {
    }

    public Item(Long id, String productId, int orderId, String createdBy, String updatedBy, double price, int quantity, double sold, int available, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.price = price;
        this.quantity = quantity;
        this.sold = sold;
        this.available = available;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
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

    @Override
    public String toString() {
        return  id + "," + productId + "," + orderId + "," + createdBy + "," + updatedBy + "," + price + "," + quantity + "," + sold + "," + available + "," + createdAt + "," + updatedAt;
    }
}
