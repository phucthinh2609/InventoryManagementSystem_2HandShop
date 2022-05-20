package vn.mvpthinh.model;

import com.sun.org.apache.xpath.internal.operations.Or;

import javax.management.relation.Role;
import java.time.Instant;
import java.time.LocalDateTime;

public class Order {
    private Long id;
    private int userId;
    private Type type;
    private String status;
    private int subTotal;
    private int grandTotal;
    private Instant createdAt;
    private Instant updateAt;
    private String content;

    public Order() {
    }

    public Order(Long id, int userId, Type type, String status, int subTotal, int grandTotal, Instant createdAt, Instant updateAt, String content) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.status = status;
        this.subTotal = subTotal;
        this.grandTotal = grandTotal;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.content = content;
    }

    public static Order parse(String record) {
        Order order = new Order();
        String[] field = record.split(",");
        order.id = Long.parseLong(field[0]);
        order.userId = Integer.parseInt(field[1]);
        order.type = Type.parseRole(field[2]);
        order.status = field[3];
        order.subTotal = Integer.parseInt(field[3]);
        order.grandTotal = Integer.parseInt(field[4]);
        order.createdAt = Instant.parse(field[5]);
        order.updateAt = null;
        String temp = field[6];
        order.content = field[7];

        if (temp != null && !temp.equals("null"))
            order.updateAt = Instant.parse(temp);

        return order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(int subTotal) {
        this.subTotal = subTotal;
    }

    public int getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(int grandTotal) {
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

    @Override
    public String toString() {
        return id + "," + userId + "," + type + "," + status + "," + subTotal + "," + grandTotal + "," + createdAt + "," + updateAt + "," + content;
    }
}
