package top.jocularchao._04authentication.config;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

/**
 * Create with IntelliJ IDEA.
 *
 * @author JocularChao
 * @date 2023/11/11 20:27
 * @Description
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    //1 基于内存认证
    //UserDetailsService就是获取用户信息的服务
    /*@Bean
    public UserDetailsService userDetailsService() {
        //每一个UserDetails就代表一个用户信息，其中包含用户的用户名和密码以及角色
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")  //角色目前我们不需要关心
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN","USER")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }*/

    //1 使用官方提供的BCrypt加密工具更好的加密
    //这里将BCryptPasswordEncoder直接注册为Bean，Security会自动进行选
    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User
                .withUsername("user")
                .password(encoder.encode("password"))   //这里将密码进行加密后存储
                .roles("USER")
                .build();
        System.out.println(encoder.encode("password"));  //一会观察一下加密出来之后的密码长啥样
        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("password"))   //这里将密码进行加密后存储
                .roles("ADMIN", "USER")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/


    //2 基于数据库验证
    //密码加密
    /*@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //数据库连接
    @Bean
    public DataSource dataSource() {
        //配置数据源
        return new PooledDataSource("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/jdbc_test","root"
        ,"123456");
    }

    //交互
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource, PasswordEncoder encoder) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        //创建一个用户，并保存在数据库中
        manager.createUser(User
                .withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build()
        );

        System.out.println(encoder.encode("password"));
        return manager;

    }*/


    //2 密码重置
    //密码加密
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //数据库连接
    @Bean
    public DataSource dataSource() {
        //配置数据源
        return new PooledDataSource("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/jdbc_test","root"
        ,"123456");
    }

    //手动创建一个AuthenticationManager用于处理密码校验
    private AuthenticationManager authenticationManager(UserDetailsManager manager,
                                                        PasswordEncoder encoder){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(manager);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    //交互
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource, PasswordEncoder encoder) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setAuthenticationManager(authenticationManager(manager, encoder));

        return manager;

    }

}