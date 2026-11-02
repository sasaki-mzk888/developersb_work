package jp.co.developersb.repository.chapter6_repository;

import jp.co.developersb.entity.chapter6_entity.AppUser6;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser6, Long> {
    Optional<AppUser6> findByUsername(String username); // ログイン認証で使う想定
}