package com.yue.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yue.dao.UserDao;
import com.yue.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RestService {
    @Autowired
    private UserDao userDao;
    public String verification(){
        StringBuilder code = new StringBuilder();
        int [] num = new int[3];
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int number = random.nextInt(10)+48;
            int upper = random.nextInt(26)+65;
            int lower = random.nextInt(26)+97;
            num[0]=number;
            num[1]=upper;
            num[2]=lower;
            code.append((char) num[random.nextInt(3)]);
        }
//        随机生成四位验证码
        return code.toString();
    }
//    判断用户是否存在
    public Object isexist(String number,String email,String position){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("number",number).eq("email",email);
        return userDao.selectOne(wrapper);
    }
}
