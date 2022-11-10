package com.yue.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class User implements Serializable {
    /*
    老师
    * */
    private int id;
    private String username;
    private String name;
    private String email;
    private String password;

    private String position;

}
