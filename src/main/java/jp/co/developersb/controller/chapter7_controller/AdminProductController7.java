package jp.co.developersb.controller.chapter7_controller;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter7_dto.ProductForm7;          // 推測: 置き場所は実際に合わせる
import jp.co.developersb.entity.chapter7_entity.ProductInfo7;
import jp.co.developersb.service.chapter7_service.ProductService7;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   // 推測(画面外)
public class AdminProductController7 {

    private final ProductService7 productService;   // 推測(18〜21行目)

    public AdminProductController7(ProductService7 productService) {   // 推測
        this.productService = productService;
    }

    @GetMapping("/api/product-management")
    public List<ProductInfo7> index() {
        return productService.findAllProducts();
    }

    @PostMapping("/api/product-registration")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductInfo7 store(@Valid @RequestBody ProductForm7 form) {
        return productService.create(form);
    }

    @PostMapping("/api/product-management/{id}/publish")
    public ProductInfo7 publish(@PathVariable Long id) {
        return productService.publish(id);
    }

    @PostMapping("/api/product-management/{id}/hide")
    public ProductInfo7 hide(@PathVariable Long id) {
        return productService.hide(id);
    }
}