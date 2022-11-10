package com.yue.config.config.JWT;

import com.yue.config.config.JSON.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义一个过滤器用来进行识别jwt。
 * 实现自动登录
 * @author Tu_Yooo
 * &#064;Date  2021/5/25 17:14
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


//    jwt拦截
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("token");//获取token
        if(StringUtils.isBlank(header)){
            chain.doFilter(request,response);
            return;
        }
        //工具类解析ＪＷＴ
        Claims token = jwtUtils.getClaimsByToken(header);
        if (token == null){
            throw new JwtException("token异常");
        }

        if (jwtUtils.isTokenExpired(token)){
            log.info("jwt过期");
            throw new JwtException("token过期");
        }

        String username = token.getSubject();
        //查询数据库
        UserDetail userDetail = userDetailsService.loadUserByUsername(username);
        //获取用户权限等信息

        System.out.println(userDetail.getAuthorities());
        UsernamePasswordAuthenticationToken authentication =
                // 参数: 用户名 密码 权限信息
                new UsernamePasswordAuthenticationToken(username,null,userDetail.getAuthorities());

        //后续security就能获取到当前登录的用户信息了，也就完成了用户认证。
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request,response);
    }
}