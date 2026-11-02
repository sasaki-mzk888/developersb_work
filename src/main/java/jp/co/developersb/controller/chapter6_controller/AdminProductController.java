package jp.co.developersb.controller.chapter6_controller;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter6_dto.ProductForm6;          // 推測: 置き場所は実際に合わせる
import jp.co.developersb.entity.chapter6_entity.ProductInfo6;
import jp.co.developersb.repository.chapter6_repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;                              // 修正
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminProductController {

    private final ProductRepository productRepository;

    public AdminProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/product-management")
    public String index(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "chapter6_templates/admin/product-management";
    }

    @GetMapping("/product-registration")
    public String create(Model model) {
        model.addAttribute("product", new ProductForm6());
        return "chapter6_templates/admin/product-registration";
    }

    @PostMapping("/product-registration")
    public String store(@Valid @ModelAttribute("product") ProductForm6 form,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "chapter6_templates/admin/product-registration";
        }

        var product = new ProductInfo6(
                form.getName(),
                form.getPrice(),
                form.getStock(),
                form.isPublished()
        );

        productRepository.save(product);
        return "redirect:/product-management";
    }
    @PostMapping("/product-management/{id}/publish")
    public String publish(@PathVariable Long id) {
        var product = productRepository.findById(id).orElseThrow();
        product.setPublished(true);
        productRepository.save(product);
        return "redirect:/product-management";
    }

    @PostMapping("/product-management/{id}/hide")
    public String hide(@PathVariable Long id) {
        var product = productRepository.findById(id).orElseThrow();
        product.setPublished(false);
        productRepository.save(product);
        return "redirect:/product-management";
    }
}