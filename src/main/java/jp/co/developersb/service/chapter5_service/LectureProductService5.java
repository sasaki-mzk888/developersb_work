package jp.co.developersb.service.chapter5_service;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter5_dto.ProductReqest5;
import jp.co.developersb.dto.chapter5_dto.ProductResponse5;
import jp.co.developersb.entity.chapter5_entity.ProductEntity5;
import jp.co.developersb.exception.ProductAlreadyException;
import jp.co.developersb.repository.chapter5_repository.ProductRepository5;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureProductService5 {
    private final ProductRepository5 productRepository;

    public LectureProductService5(ProductRepository5 productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse5 createProduct(@Valid ProductReqest5 request) {
        if (productRepository.existsByName(request.name())) {
            throw new ProductAlreadyException("同じ商品名の商品は登録できません");
        }

        ProductEntity5 product = new ProductEntity5(
                request.name(),
                request.price(),
                request.stock()
        );

        ProductEntity5 savedProduct = productRepository.save(product);

        return toResponse(savedProduct);
    }

    public List<ProductResponse5> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProductResponse5 toResponse(ProductEntity5 product) {
        return new ProductResponse5(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }
}