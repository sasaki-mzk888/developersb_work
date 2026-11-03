package jp.co.developersb.controller.chapter7_controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController7 {

    @GetMapping("/api/auth/status")
    public Map<String, Object> status(Authentication authentication) {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return Map.of("authenticated", false, "username", "", "isAdmin", false);
        }

        boolean isAdmin = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);

        return Map.of(
                "authenticated", true,
                "username", authentication.getName(),
                "isAdmin", isAdmin
        );
    }
}