package com.yue.controller;

import com.yue.config.Msg;
import com.yue.domain.User;
import com.yue.service.serviceimpl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private UserServiceimpl serviceimpl;

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @PostMapping("/login")
    public Msg login(@RequestBody Map<String,Object> map){

        String username  = String.valueOf(map.get("username"));

        String password = String.valueOf(map.get("password"));
        User user = serviceimpl.do_login(username);
        Msg msg;
        if (user == null) {
            msg = Msg.fail("账号错误");
        } else if (!password.equals(user.getPassword())) {
            msg = Msg.fail("密码错误");
        } else {
            //通过UUID生成token字符串,并将其以string类型的数据保存在redis缓存中，key为token，value为username
            String token= UUID.randomUUID().toString().replaceAll("-","");
            redisTemplate.opsForValue().set(token,username,7200*10,TimeUnit.MINUTES);
            if(Objects.equals(user.getPosition(), "admin")){
                msg=Msg.success("登录成功").add("token",token).add("name",user.getName()).add("role",9999);
            }else{
                msg=Msg.success("登录成功").add("token",token).add("name",user.getName()).add("role",1111);
            }
        }
        return msg;
    }

    //注销接口
    @PostMapping("/logout")
    public Msg logout(@RequestHeader("token")String token){
        //删除redis缓存中的token
        redisTemplate.delete(token);
        return Msg.success("注销成功");
    }
}
