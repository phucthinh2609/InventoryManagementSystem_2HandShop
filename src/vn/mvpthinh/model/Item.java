package vn.mvpthinh.model;

import java.time.Instant;

public class Item {
    private Long id;
    private Long productId;
    private Long orderId;
    private String sku;
    private double price;
    private int quantity;
    private int sold;
    private int available;
    private Instant createdAt;
    private Instant updatedAt;
    private Long createdBy;
    private Long updatedBy;

    private Order order;
    private Product product;

    public Item() {
    }

    public static Item parse(String record) {
        Item item = new Item();
        String[] field = record.split(",");
        item.id = Long.parseLong(field[0]);
        item.productId = Long.parseLong(field[1]);
        item.orderId = Long.parseLong(field[2]);
        item.sku = field[3];
        item.price = Double.parseDouble(field[4]);
        item.quantity = Integer.parseInt(field[5]);
        item.sold = Integer.parseInt(field[6]);
        item.available = Integer.parseInt(field[7]);
        item.createdBy = Long.parseLong(field[8]);
        String temp = field[9];
        if (temp != null && !temp.equals("null"))
            item.updatedBy = Long.parseLong(temp);
        item.createdAt = Instant.parse(field[10]);
        item.updatedAt = null;
        temp = field[11];
        if (temp != null && !temp.equals("null"))
            item.updatedAt = Instant.parse(temp);

        return item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                id,
                productId,
                orderId,
                sku,
                price,
                quantity,
                sold,
                available,
                createdBy,
                updatedBy,
                createdAt,
                updatedAt
        );
    }
}
