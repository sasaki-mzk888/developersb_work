package jp.co.developersb.controller.chapter7_controller;

import jp.co.developersb.entity.chapter7_entity.ProductInfo7;
import jp.co.developersb.repository.chapter7_repository.ProductRepository7;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublicProductController7 {

    private final ProductRepository7 productRepository;

    public PublicProductController7(ProductRepository7 productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/api/product-list")
    public List<ProductInfo7> list() {
        return productRepository.findByPublishedTrueOrderByIdDesc();
    }
}