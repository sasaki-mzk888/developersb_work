package jp.co.developersb.controller.chapter4_controller;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter4_dto.ProductReqest4;
import jp.co.developersb.dto.chapter4_dto.ProductResponse4;
import jp.co.developersb.service.chapter4_service.ExerciseProductService4;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter4-exercise")
public class ExerciseController4 {
    private final ExerciseProductService4 exerciseProductService;

    public ExerciseController4(ExerciseProductService4 exerciseProductService) {
        this.exerciseProductService = exerciseProductService;
    }

    @PostMapping("/product")
    public ProductResponse4 createProduct(@Valid @RequestBody ProductReqest4 request) {
        //TODO:7.exerciseProductServiceのcreateProductメソッドを呼び出してください
        return exerciseProductService.createProduct(request);
    }

    @GetMapping("/product")
    public List<ProductResponse4> findAllProducts() {
        //TODO:7.exerciseProductServiceのfindAllProductsメソッドを呼び出してください
        return exerciseProductService.findAllProducts();
    }
}

