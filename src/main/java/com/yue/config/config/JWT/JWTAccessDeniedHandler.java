package com.yue.config.config.JWT;

import com.alibaba.fastjson.JSON;
import com.yue.config.config.JSON.JsonResult;
import com.yue.config.config.JSON.ResultCode;
import com.yue.config.config.JSON.ResultTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 其他异常处理
 * @author Tu_Yooo
 * @Date 2021/5/26 10:01
 */
@Slf4j//简化语句
@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {
//    没有权限时的处理
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        log.info("权限不够！！");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);//403

        JsonResult result = ResultTool.fail(ResultCode.NO_PERMISSION);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
