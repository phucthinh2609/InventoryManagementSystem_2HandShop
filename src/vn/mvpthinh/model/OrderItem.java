package vn.mvpthinh.model;

import java.time.Instant;

public class OrderItem {
    private Long id;
    private Long productId;
    private Long itemId;
    private Long orderId;
    private double price;
    private int quantity;
    private Instant createdAt;
    private Instant updatedAt;
    private String content;
    private Product product;
    private Item item;
    private Order order;


    public OrderItem(Long id, Long productId, Long itemId, Long orderId, double price, int quantity, String content) {
        this.id = id;
        this.productId = productId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.content = content;
    }

    public OrderItem() {

    }

    public static OrderItem parse(String record) {
        String[] filed = record.split(",");
        OrderItem orderItem = new OrderItem();

        orderItem.id = Long.parseLong(filed[0]);
        orderItem.productId = Long.parseLong(filed[1]);
        orderItem.itemId = Long.parseLong(filed[2]);
        orderItem.orderId = Long.parseLong(filed[3]);
        orderItem.price = Double.parseDouble(filed[4]);
        orderItem.quantity = Integer.parseInt(filed[5]);
        orderItem.content = filed[6];
        orderItem.createdAt = Instant.parse(filed[7]);
        String temp = filed[8];
        if (temp != null && !temp.equals("null")) {
            orderItem.updatedAt = Instant.parse(temp);
        }
        return orderItem;
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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,",
                id,
                productId,
                itemId,
                orderId,
                price,
                quantity,
                content,
                createdAt,
                updatedAt
                );

    }
}
