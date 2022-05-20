package vn.mvpthinh.model;

import sun.util.resources.bg.LocaleNames_bg;

import java.time.Instant;

public class Product {
    private Long id;
    private String title;
    private String summary;
    private String type;
    private Instant createdAt;
    private Instant updatedAt;
    private String content;

    public Product() {
    }

    public Product(Long id, String title, String summary, String type, Instant createdAt, Instant updatedAt, String content) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.type = type;
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
        String summary = field[2];
        String type = null;
        Instant createdAt = Instant.parse(field[4]);
        Instant updatedAt = null;
        String content = field[6];
        String temp = field[5];
        if (temp != null && !temp.equals("null"))
            updatedAt = Instant.parse(temp);

        return new Product(id, title, summary, type, createdAt, updatedAt, content);
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        return String.format("%s,%s,%s,%s,%s,%s,%s,",
                id,
                title,
                summary,
                type,
                createdAt,
                updatedAt,
                content
        );
    }
}

