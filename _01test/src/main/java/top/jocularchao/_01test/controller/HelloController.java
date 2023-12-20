package top.jocularchao._01test.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jocularchao
 * @date 2023-12-20 18:29
 * @description
 */
@Controller
public class HelloController {

    //处理首页或是登录界面跳转
    @RequestMapping("/")
    public String home(HttpSession session){
        if (session.getAttribute("login")!=null){
            return "index";
        }else {
            return "login";
        }
    }

}
