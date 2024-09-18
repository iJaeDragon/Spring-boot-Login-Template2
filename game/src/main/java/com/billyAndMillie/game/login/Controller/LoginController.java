package com.billyAndMillie.game.login.Controller;

import com.billyAndMillie.game.common.domain.ClientMsg;
import com.billyAndMillie.game.common.domain.ResponseResult;
import com.billyAndMillie.game.login.Service.LoginService;
import com.billyAndMillie.game.login.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = "/loginView")
    public ModelAndView loginView(@RequestParam Map<String, Object> requestParam) {
        ModelAndView mav = new ModelAndView("pages/login/loginView");

        if(requestParam.get("fail") != null) {
            ClientMsg clientMsg = new ClientMsg();
            clientMsg.setMsg("로그인 실패했습니다.");
            clientMsg.setType("fail");

            mav.addObject("clientMsg", clientMsg);
        }

        return mav;
    }

    @GetMapping(value = "/registerView")
    public String registerView(Model model) {

        return "pages/login/registerView";
    }

    @GetMapping(value = "/myPageView")
    public String myPageView(Model model) {

        return "pages/login/myPageView";
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseResult register(@RequestBody User user) {
        ResponseResult result = new ResponseResult();

        try {
            boolean isSuccess = loginService.userRegister(user);

            if(!isSuccess) {
                throw new RuntimeException("회원가입중 에러가 발생했습니다.");
            }

            result.setSuccess(true);

        } catch (RuntimeException e) {
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }

        return result;
    }
}
