package jp.co.developersb.config.chapter7_config;   // 推測

import jp.co.developersb.entity.chapter7_entity.AppUser7;          // 推測
import jp.co.developersb.entity.chapter7_entity.ProductInfo7;      // 推測
import jp.co.developersb.entity.chapter7_entity.Role7;             // 推測
import jp.co.developersb.repository.chapter7_repository.AppUserRepository7;   // 推測
import jp.co.developersb.repository.chapter7_repository.ProductRepository7;   // 推測
import org.springframework.boot.CommandLineRunner;                 // 推測
import org.springframework.context.annotation.Bean;                // 推測
import org.springframework.context.annotation.Configuration;       // 推測
import org.springframework.security.crypto.password.PasswordEncoder;   // 推測

@Configuration   // 推測
public class DataInitializer7 {

    @Bean   // 推測
    CommandLineRunner initChapter7Data(
            AppUserRepository7 appUserRepository,
            ProductRepository7 productRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (appUserRepository.count() == 0) {
                appUserRepository.save(new AppUser7("user", passwordEncoder.encode("password"), Role7.ROLE_USER));
                appUserRepository.save(new AppUser7("admin", passwordEncoder.encode("password"), Role7.ROLE_ADMIN));
            }

            if (productRepository.count() == 0) {
                productRepository.save(new ProductInfo7("いちごタピオカミルクティ", 420, 16, true));
                productRepository.save(new ProductInfo7("ほうじ茶タピオカミルクティ", 580, 22, true));
                productRepository.save(new ProductInfo7("ハニーバターワッフル", 760, 9, true));
                productRepository.save(new ProductInfo7("ハニーキャラメルザッハトルテ", 1280, 4, true));
            }
        };
    }
}