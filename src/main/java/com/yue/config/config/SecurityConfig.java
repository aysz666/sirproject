package com.yue.config.config;

import com.yue.config.config.JWT.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //JWT异常处理
    @Autowired
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // 其他异常处理
    @Autowired
    private JWTAccessDeniedHandler jwtAccessDeniedHandler;

    //自定义JWT识别
    @Bean
    JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JWTAuthenticationFilter(authenticationManager());
    }

//    自定义成功处理
    @Autowired
JWTAuthenticationSuccessHandler authenticationSuccessHandler;
//    失败处理
    @Autowired
JWTAuthenticationFailureHandler authenticationFailureHandler;
//    自定义认证
    @Autowired
    SelfAuthenticationProvider authenticationProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.cors();//开启跨域
        http.exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                添加拦截
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                更改UsernamePasswordAuthenticationFilter拦截
                .addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
//                路径权限配置
                .antMatchers("/login").permitAll()
                .antMatchers("/users/**").hasRole("user")
                .antMatchers("/admins/**").hasRole("admin")
                .anyRequest().authenticated();
        http.formLogin()
//                参数名称
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .sessionManagement().maximumSessions(2);//也许上线一个用户两个设备

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        密码加密方式
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
        auth.userDetailsService(userDetailsService());
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

//    加载SelfUsernamePasswordAuthenticationFilter并配置
    SelfUsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        SelfUsernamePasswordAuthenticationFilter filter = new SelfUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(super.authenticationManagerBean());
        filter.setFilterProcessesUrl("/login");
        //处理登录成功
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        //处理登录失败
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        return filter;
    }
}
