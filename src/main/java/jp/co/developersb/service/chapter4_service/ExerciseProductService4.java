package jp.co.developersb.service.chapter4_service;

import jakarta.validation.Valid;
import jp.co.developersb.dto.chapter4_dto.ProductReqest4;
import jp.co.developersb.dto.chapter4_dto.ProductResponse4;
import jp.co.developersb.exception.ProductAlreadyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseProductService4 {
    private final List<ProductResponse4> products = new ArrayList<>();
    private long nextId = 1L;

    public ProductResponse4 createProduct(@Valid ProductReqest4 request){
        //TODO：1.同じ商品名がすでに登録されているか確認してください
        boolean exists = products.stream()
                .anyMatch(product -> product.name().equals(request.name()));

        //TODO：2.同じ商品名が存在する場合はProductAlreadyExceptionを投げてください
        if (exists) {
            throw new ProductAlreadyException("同じ商品名の商品は登録できません");
        }

        //TODO：3.新しく作成する商品のインスタンスを生成してください。
        ProductResponse4 product = new ProductResponse4(
                nextId,
                request.name(),
                request.price(),
                request.stock()
        );

        //TODO：4.productsに新しく作成した商品を追加してください
        products.add(product);

        //TODO：5.nextIdを1つ増やしてください
        nextId++;

        //TODO：6.新しく作成した商品をreturnしてください
        return product;
    }

    public List<ProductResponse4> findAllProducts() {
        //TODO:8.productsをreturnしてください
        return products;
    }
}
