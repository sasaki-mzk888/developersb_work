package jp.co.developersb.config.chapter6_config;

import jp.co.developersb.entity.chapter6_entity.AppUser6;
import jp.co.developersb.entity.chapter6_entity.ProductInfo6;
import jp.co.developersb.repository.chapter6_repository.AppUserRepository;
import jp.co.developersb.repository.chapter6_repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jp.co.developersb.entity.chapter6_entity.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            AppUserRepository appUserRepository,
            ProductRepository productRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (appUserRepository.count() == 0) {
                appUserRepository.save(new AppUser6("user", passwordEncoder.encode("password"), Role.ROLE_USER));
                appUserRepository.save(new AppUser6("admin", passwordEncoder.encode("password"), Role.ROLE_ADMIN));
            }
            if (productRepository.count() == 0) {
                productRepository.save(new ProductInfo6("濃厚チョコブラウニー", 480, 18, true));
                productRepository.save(new ProductInfo6("はちみつミルクラテ", 620, 24, true));
                productRepository.save(new ProductInfo6("プロテインナッツバー", 350, 30, true));
                productRepository.save(new ProductInfo6("季節限定ベリーパフェ", 980, 5, false));
            }
        };
    }
}
