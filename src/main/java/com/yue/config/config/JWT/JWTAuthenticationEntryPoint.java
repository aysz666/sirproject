package com.yue.config.config.JWT;

import com.alibaba.fastjson.JSON;
import com.yue.config.config.JSON.JsonResult;
import com.yue.config.config.JSON.ResultCode;
import com.yue.config.config.JSON.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT识别异常处理
 * @author Tu_Yooo
 * @Date 2021/5/26 9:53
 */
@Slf4j
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

//    没有登陆的处理
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.error("认证失败！未登录！");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401

        JsonResult result = ResultTool.fail(ResultCode.USER_NOT_LOGIN);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));


    }
}
