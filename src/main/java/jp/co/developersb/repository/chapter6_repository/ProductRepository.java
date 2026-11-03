package jp.co.developersb.repository.chapter6_repository;

import jp.co.developersb.entity.chapter6_entity.ProductInfo6;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductInfo6, Long> {
    List<ProductInfo6> findByPublishedTrueOrderByIdDesc();
}