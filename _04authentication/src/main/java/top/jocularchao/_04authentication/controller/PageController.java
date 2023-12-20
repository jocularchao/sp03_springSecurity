package top.jocularchao._04authentication.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create with IntelliJ IDEA.
 *
 * @author JocularChao
 * @date 2023/11/11 20:20
 * @Description 专用于处理页面响应的控制器
 */
@Controller
public class PageController {

    @RequestMapping("/index")
    public String login(){
        return "index";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }
}