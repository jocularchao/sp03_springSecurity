package top.jocularchao._01test.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author jocularchao
 * @date 2023-12-20 19:23
 * @description
 */
@Controller
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model){
        if (username.equals("test")&& password.equals("123456")){
            session.setAttribute("login",true);
            return "redirect:/";
        }else {
            model.addAttribute("status",true);
            return "login";
        }
    }

}
