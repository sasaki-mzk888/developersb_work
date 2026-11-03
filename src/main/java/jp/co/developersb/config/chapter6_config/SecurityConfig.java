package jp.co.developersb.config.chapter6_config;
import jp.co.developersb.service.chapter6_service.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration   // chapter7では無効化(SecurityConfig7と競合するため)
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                        "/chapter4-lectuer/product",
                        "/chapter4-exercise/product",
                        "/chapter5-lectuer/product",
                        "/chapter5-exercise/product",
                        "/chapter6-product"
                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/login",
                                "/css/**",
                                "/chapter4-static/**",
                                "/chapter5-static/**",
                                "/product-list",
                                "/chapter6-static/**"
                        ).permitAll()
                        .requestMatchers(
                                "/chapter3-lectuer/**",
                                "/chapter3-exercise/**",
                                "/chapter4-lectuer/**",
                                "/chapter4-exercise/**",
                                "/chapter5-lectuer/**",
                                "/chapter5-exercise/**"
                        ).permitAll()
                        .requestMatchers("/product-management/**").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/product-registration/**").hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                                .loginPage("/login")
                                .defaultSuccessUrl("/product-list",true)
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    DaoAuthenticationProvider authenticationProvider(
            AppUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ){
        var provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
