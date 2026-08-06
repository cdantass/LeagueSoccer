package com.cdantas.league.User.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/entrar")
    public String carregaPaginaLogin() {
        return "authentication/login"; // templates/authentication/login.html
    }
}