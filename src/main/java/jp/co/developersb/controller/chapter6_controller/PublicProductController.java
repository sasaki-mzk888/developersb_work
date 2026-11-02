package jp.co.developersb.controller.chapter6_controller;

import jp.co.developersb.repository.chapter6_repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicProductController {

    private final ProductRepository productRepository;

    public PublicProductController(ProductRepository productRepository) { this.productRepository = productRepository; }

    @GetMapping({"/", "/product-list"})
    public String index(Model model){
        model.addAttribute("products",productRepository.findByPublishedTrueOrderByIdDesc());
        return "chapter6_templates/public/product-list";
    }

}
