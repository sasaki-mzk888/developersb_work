package jp.co.developersb.entity.chapter5_entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "productinfo")
public class ProductEntity5 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    protected ProductEntity5() {
    }

    public ProductEntity5(String name, Integer price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}