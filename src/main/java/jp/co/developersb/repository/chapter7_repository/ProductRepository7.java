package jp.co.developersb.repository.chapter7_repository;

import jp.co.developersb.entity.chapter7_entity.ProductInfo7;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository7 extends JpaRepository<ProductInfo7, Long> {
    List<ProductInfo7> findByPublishedTrueOrderByIdDesc();
}
