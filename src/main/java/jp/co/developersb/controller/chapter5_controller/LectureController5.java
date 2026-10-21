package jp.co.developersb.controller.chapter5_controller;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter5_dto.ProductReqest5;
import jp.co.developersb.dto.chapter5_dto.ProductResponse5;
import jp.co.developersb.service.chapter5_service.LectureProductService5;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter5-lecture")
public class LectureController5 {
    private final LectureProductService5 lectureProductService;

    public LectureController5(LectureProductService5 lectureProductService) {
        this.lectureProductService = lectureProductService;
    }

    @PostMapping("/product")
    public ProductResponse5 createProduct(@Valid @RequestBody ProductReqest5 reqest){
        return lectureProductService.createProduct(reqest);
    }

    @GetMapping("/product")
    public List<ProductResponse5> findAllProducts(){
        return lectureProductService.findAllProducts();
    }

}
