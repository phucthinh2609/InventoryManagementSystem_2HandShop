package vn.mvpthinh.model;

import sun.util.resources.bg.LocaleNames_bg;

import java.time.Instant;

public class Product {
    private Long id;
    private String title;

    private Instant createdAt;
    private Instant updatedAt;
    private String content;

    public Product() {
    }

    public Product(Long id, String title, Instant createdAt, Instant updatedAt, String content) {
        this.id = id;
        this.title = title;

        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.content = content;
    }

    public Product(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static Product parse(String record) {
//        record = "1652955029,Quần đùi,null,null,2022-05-19T10:10:33.937Z,null,Nhiều size,\n";
        String[] field = record.split(",");
        long id = Long.parseLong(field[0]);
        String title = field[1];
        Instant createdAt = Instant.parse(field[2]);
        String temp = field[3];
        String content = field[4];
        Instant updatedAt = null;
        if (temp != null && !temp.equals("null"))
            updatedAt = Instant.parse(temp);

        return new Product(id, title, createdAt, updatedAt, content);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return String.format("%s,%s,%s,%s,%s",
                id,
                title,
                createdAt,
                updatedAt,
                content
        );
    }
}

