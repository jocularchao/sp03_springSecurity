package top.jocularchao._03template.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Create with IntelliJ IDEA.
 *
 * @author JocularChao
 * @date 2023/11/11 19:31
 * @Description
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    //不用重写任何内容
    //这里实际上会自动注册一个Filter，SpringSecurity底层就是依靠N个过滤器实现的，我们之后再探讨
}
