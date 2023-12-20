package top.jocularchao._03template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create with IntelliJ IDEA.
 *
 * @author JocularChao
 * @date 2023/11/11 18:17
 * @Description
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
