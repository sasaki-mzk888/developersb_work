package jp.co.developersb.repository.chapter5_repository;

import jp.co.developersb.entity.chapter5_entity.ProductEntity5;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository5 extends JpaRepository<ProductEntity5, Long> {
    boolean existsByName(String name);
}
