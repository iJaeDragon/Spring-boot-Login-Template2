package com.billyAndMillie.game.main.Controller;

import com.billyAndMillie.game.common.domain.ClientMsg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/main"}, produces = "text/html; charset=UTF-8")
    public ModelAndView main(@RequestParam Map<String, Object> requestParam) {
        ModelAndView mav = new ModelAndView("pages/main/main");

        if(requestParam.get("expired") != null) {
            ClientMsg clientMsg = new ClientMsg();
            clientMsg.setMsg("다른 환경에서 로그인되어 로그아웃 됐습니다.");
            clientMsg.setType("expired");

            mav.addObject("clientMsg", clientMsg);
        } else if(requestParam.get("logout") != null) {
            ClientMsg clientMsg = new ClientMsg();
            clientMsg.setMsg("로그아웃 됐습니다.");
            clientMsg.setType("logout");

            mav.addObject("clientMsg", clientMsg);
        }

        return mav;
    }

}
