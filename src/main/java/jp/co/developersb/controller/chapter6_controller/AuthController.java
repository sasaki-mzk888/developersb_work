package jp.co.developersb.controller.chapter6_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping ("/login")
        public String login() { return "chapter6_templates/auth/login"; }
}
