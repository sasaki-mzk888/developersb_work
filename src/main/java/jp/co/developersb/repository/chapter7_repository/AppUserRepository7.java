package jp.co.developersb.repository.chapter7_repository;

import jp.co.developersb.entity.chapter7_entity.AppUser7;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository7 extends JpaRepository<AppUser7, Long> {
    Optional<AppUser7> findByUsername(String username);
}
