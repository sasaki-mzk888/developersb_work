package jp.co.developersb.dto.chapter7_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductForm7 {
    @NotBlank(message = "商品名を入力してください")
    private String name;

    @NotNull(message = "価格を入力してください")
    @Min(value = 0, message = "価格は0以上で入力してください")
    private Integer price;

    @NotNull(message = "在庫数を入力してください")
    @Min(value = 0, message = "在庫数は0以上で入力してください")
    private Integer stock;

    private boolean published;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public boolean isPublished() { return published; }
    public void setPublished(boolean published) { this.published = published; }
}
