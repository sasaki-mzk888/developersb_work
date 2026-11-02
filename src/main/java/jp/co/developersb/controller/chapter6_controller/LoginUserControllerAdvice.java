package jp.co.developersb.controller.chapter6_controller;

import org.springframework.ui.Model;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LoginUserControllerAdvice {

    @ModelAttribute
    public void addLoginUsername(Model model, Authentication authentication) {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return;
        }

        model.addAttribute("loginUsername", authentication.getName());
        model.addAttribute("isAdmin", hasRoleAdmin(authentication));
    }

    private boolean hasRoleAdmin(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("ROLE_ADMIN"::equals);
    }
}