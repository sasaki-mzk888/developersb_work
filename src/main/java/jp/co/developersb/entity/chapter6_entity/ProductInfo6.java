package jp.co.developersb.entity.chapter6_entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_info6")
public class ProductInfo6 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private boolean published; // 公開フラグ

    protected ProductInfo6() {} // JPA用(必須)

    public ProductInfo6(String name, int price, int stock, boolean published) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.published = published;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }
    public boolean isPublished() { return published; }

    public void setPublished(boolean published) { this.published = published; }   // 追加
}