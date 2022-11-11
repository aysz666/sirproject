package com.yue.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * &#064;ClassName  : MSG
 * 类名  &#064;Description  :
 * 描述  &#064;Author  : Supreme //作者
 * &#064;Date:  2022/11/8  15:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Msg {
    int code;   //错误码
    String message; //消息提示
    Map<String,Object> data= new HashMap<>();   //数据

    //无权访问
    public static Msg denyAccess(String message){
        Msg result=new Msg();
        result.setCode(300);
        result.setMessage(message);
        return result;
    }

    //操作成功
    public static Msg success(String message){
        Msg result=new Msg();
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    //客户端操作失败
    public static Msg fail(String message){
        Msg result=new Msg();
        result.setCode(400);
        result.setMessage(message);
        return result;
    }

    public Msg add(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}