package com.billyAndMillie.game.login.Controller;

import com.billyAndMillie.game.login.Service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "/loginView")
    public String loginView(Model model) {

        return "login/loginView";
    }

    @GetMapping(value = "/registerView")
    public String registerView(Model model) {

        return "login/registerView";
    }

    @GetMapping(value = "/myPageView")
    public String myPageView(Model model) {

        return "login/myPageView";
    }
}
