package jp.co.developersb.service.chapter7_service;

import jp.co.developersb.dto.chapter7_dto.ProductForm7;
import jp.co.developersb.entity.chapter7_entity.ProductInfo7;
import jp.co.developersb.repository.chapter7_repository.ProductRepository7;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService7 {

    private final ProductRepository7 productRepository;

    public ProductService7(ProductRepository7 productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductInfo7> findPublishedProducts() {
        return productRepository.findByPublishedTrueOrderByIdDesc();
    }

    public List<ProductInfo7> findAllProducts() {
        return productRepository.findAll(Sort.by("id"));
    }

    public ProductInfo7 create(ProductForm7 form) {
        return productRepository.save(new ProductInfo7(
                form.getName(), form.getPrice(), form.getStock(), form.isPublished()));
    }

    public ProductInfo7 publish(Long id) {
        var product = productRepository.findById(id).orElseThrow();
        product.setPublished(true);
        return productRepository.save(product);
    }

    public ProductInfo7 hide(Long id) {
        var product = productRepository.findById(id).orElseThrow();
        product.setPublished(false);
        return productRepository.save(product);
    }
}