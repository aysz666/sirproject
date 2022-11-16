package com.yue.controller;

import com.yue.config.UploadFile;
import com.yue.config.config.JWT.UserDetail;
import com.yue.domain.Project;
import com.yue.domain.ProjectFu;
import com.yue.domain.User;
import com.yue.service.serviceimpl.UserServiceimpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("用户相关的接口")
public class UserController {
    @Autowired
    private UserServiceimpl userServiceimpl;

    @ApiOperation("传入某个项目的id，返回被退回的历史记录")
    @GetMapping("get/failing/{id}")
    public List<ProjectFu> getFailing(@PathVariable("id") int id){
        System.out.println(id);
        return userServiceimpl.getFailing(id);
    }


    @GetMapping(value = "/my_project")
    @ApiOperation("获取当前项目")
    public List<Project> person_one(){
//        获取用户信息
        UsernamePasswordAuthenticationToken principal = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) principal.getPrincipal();
        User user = userDetail.getUser();
        String user_name = user.getUsername();

        return userServiceimpl.get_my_project(user_name);
    }
    @GetMapping(value = "/my_all_project")
    @ApiOperation("获取当前用户所有的项目")
    public List<Project> person_all(){
//        获取用户信息
        UsernamePasswordAuthenticationToken principal = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) principal.getPrincipal();
        User user = userDetail.getUser();
        String user_name = user.getUsername();

        return userServiceimpl.get_my_all_project(user_name);
    }


    @PostMapping("/submit/file")
    public Map<String,Object> submit_file(@RequestParam("id") int id, @RequestParam("file") MultipartFile[] file){
        ArrayList<String> list  = new ArrayList<>();
        Map<String,Object> model = new HashMap<>();
        System.out.println(id);
//        获取用户信息
        UsernamePasswordAuthenticationToken principal = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) principal.getPrincipal();
        User user = userDetail.getUser();
        String user_name = user.getUsername();

//        传入文件到资源目录下
        if (file.length !=0){
            for (MultipartFile aFile : file){
                if (!aFile.isEmpty()){
                    list.add(user_name+aFile.getOriginalFilename());
                    Boolean aBoolean = UploadFile.httpUpload(user_name,aFile);
                    if (aBoolean){
                        model.put("file_msg","文件上传成功！");
                    }else{
                        model.put("file_msg","文件上传失败！");
                    }
                }
            }
        }
//        int id  = Integer.parseInt((String) map.get("id"));
        if (!list.isEmpty()){
            String dateFile = String.join("*",list);//用*号分割所有文件命
            Boolean flag = userServiceimpl.submit_file(id,dateFile);
            if (flag){
                model.put("file_msg","文件上传成功！");
            }else{
                model.put("file_msg","文件上传失败！");
            }
        }
        return model;
    }
    @PostMapping(value = "/submit")
    @ApiOperation("添加或修改项目,添加项目是不可以带有项目id")
    public Map<String,Object> submit_project(@RequestBody Project project){
        Map<String,Object> model = new HashMap<>();
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
