package com.yue.controller;

import com.yue.config.UploadFile;
import com.yue.domain.Project;
import com.yue.service.serviceimpl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyRole('user')")
public class UserController {
    @Autowired
    private UserServiceimpl userServiceimpl;

    @GetMapping(value = "/my_project")
    public List<Project> person_one(){
        UsernamePasswordAuthenticationToken principal = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String user_name = String.valueOf(principal.getPrincipal());

        return userServiceimpl.get_my_project(user_name);
    }
    @GetMapping(value = "/my_all_project")
    @ResponseBody
    public List<Project> person_all(){
//        获取用户信息
        UsernamePasswordAuthenticationToken principal = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String user_name = String.valueOf(principal.getPrincipal());

        return userServiceimpl.get_my_all_project(user_name);
    }

    @PostMapping(value = "/submit")
    public Map<String,Object> submit_project(Project project, @RequestParam("file") MultipartFile[] file){

        Map<String,Object> model = new HashMap<>();
        ArrayList<String> list  = new ArrayList<>();
//        传入文件到资源目录下
        if (file.length !=0){
            for (MultipartFile aFile : file){
                if (!aFile.isEmpty()){
                    list.add(project.getUsername()+aFile.getOriginalFilename());
                    Boolean aBoolean = UploadFile.httpUpload(project.getUsername(),aFile);
                    if (aBoolean){
                        model.put("file_msg","文件上传成功！");
                    }else{
                        model.put("file_msg","文件上传失败！");
                    }
                }
            }
        }
        if (!list.isEmpty()){
            String dateFile = String.join("*",list);//用*号分割所有文件命
            project.setDate(dateFile);
        }

        Boolean flag = userServiceimpl.submit_projict(project);//插入数据库
//        提示信息
        if(flag){
            model.put("msg","提交成功！");
        }
        else{
            model.put("msg","提交失败");
        }
        return model;
    }
}
