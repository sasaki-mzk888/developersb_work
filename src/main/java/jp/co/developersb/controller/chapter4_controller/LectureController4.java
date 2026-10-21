package jp.co.developersb.controller.chapter4_controller;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter4_dto.ProductReqest4;
import jp.co.developersb.dto.chapter4_dto.ProductResponse4;
import jp.co.developersb.service.chapter4_service.LectureProductService4;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chapter4-lecture")
public class LectureController4 {

    private final LectureProductService4 lectureProductService;

    public LectureController4(LectureProductService4 lectureProductService) {
        this.lectureProductService = lectureProductService;
    }

    @PostMapping("/product")
    public ProductResponse4 createProduct(@Valid @RequestBody ProductReqest4 request) {
        return lectureProductService.createProduct(request);
    }

    @GetMapping("/product")
    public List<ProductResponse4> findAllProducts() {
        return lectureProductService.findAllProducts();
    }
}