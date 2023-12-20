package top.jocularchao._04authentication.controller;

import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Create with IntelliJ IDEA.
 *
 * @author JocularChao
 * @date 2023/11/12 18:12
 * @Description
 */
@Controller
public class ResetPasswordController {


    @Resource
    UserDetailsManager manager;

    @Resource
    PasswordEncoder encoder;



    @RequestMapping("/resetPassword")
    public String resetPassword () {
        return "resetPassword";
    }

    @ResponseBody
    @RequestMapping("/resetPassword-success")
    public JSONObject resetPasswordSuccess(@RequestParam String oldPassword ,@RequestParam String newPassword) {
        manager.changePassword(oldPassword, encoder.encode(newPassword));
        JSONObject object = new JSONObject();
        object.put("success", true);
        return object;
    }
}
