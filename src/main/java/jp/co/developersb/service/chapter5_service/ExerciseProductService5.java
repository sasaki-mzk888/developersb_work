package jp.co.developersb.service.chapter5_service;

import jp.co.developersb.dto.chapter5_dto.ProductReqest5;
import jp.co.developersb.dto.chapter5_dto.ProductResponse5;
import jp.co.developersb.entity.chapter5_entity.ProductEntity5;
import jp.co.developersb.exception.ProductAlreadyException;
import jp.co.developersb.repository.chapter5_repository.ProductRepository5;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseProductService5 {
    private final ProductRepository5 productRepository;

    private ExerciseProductService5(ProductRepository5 productRepository){
        this.productRepository = productRepository;
    }

    public ProductResponse5 createProduct(ProductReqest5 request){
        //TODO:1.同じ商品名が存在するかを確認し、存在する場合はProductAlreadyExceptionを投げてください
        if (productRepository.existsByName(request.name())) {
            throw new ProductAlreadyException("同じ商品名の商品は登録できません");
        }
        //TODO:2.ProductEntity5クラスをEntityして定義してください
        ProductEntity5 product = new ProductEntity5(
                request.name(),
                request.price(),
                request.stock()
        );

        //TODO:3.新しく作成した商品をDBに保存してください
        ProductEntity5 savedProduct = productRepository.save(product);

        //TODO：4.新しく作成した商品をProductResponse5に変換してreturnしてください
        return toResponse(savedProduct);
    }

    public List<ProductResponse5>findAllProducts(){
        //TODO:6.DBから商品一覧を取得し、画面に返すProductRespons5のリストに変換してください
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
