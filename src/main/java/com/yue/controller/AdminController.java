package com.yue.controller;

import com.yue.config.UploadFile;
import com.yue.domain.Project;
import com.yue.service.serviceimpl.AdminSeviceimpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//管理员业务

@RestController
@RequestMapping("/admins")
@CrossOrigin
@Api("管理员相关")
public class AdminController {
    @Autowired
    private AdminSeviceimpl adminSeviceimpl;

//    获取进行中的记录
    @GetMapping("/all")
    @ApiOperation("获取所有进行中的项目")
    public List<Project> all_project(){
        return adminSeviceimpl.get_all_project();
    }


//    获取所有通过的记录
    @GetMapping("/success")
    @ApiOperation("获取审核通过的项目")
    public List<Project> success_project(){
        return adminSeviceimpl.get_success_project();
    }

//    搜索名称或者编号
    @PostMapping("search_project")
    @ApiOperation("通过名称或者编号搜索项目")
    public List<Project> search_project(@RequestBody Map<String,Object> maps){
        String projectName = String.valueOf(maps.get("projectName"));
        return adminSeviceimpl.search_project(projectName);
    }

    @PostMapping("/do_project")
    @ApiOperation("审核项目")
    public Map<String,Object> handle_project(@RequestBody Map<String,Object> map){
        String id = String.valueOf(map.get("id"));
        String suggestion = String.valueOf(map.get("suggestion"));
        String state= String.valueOf(map.get("state"));
        Map<String,Object> maps = new HashMap<>();
        if (adminSeviceimpl.dosu_project(id,suggestion,state)){
            maps.put("msg","处理成功！");
        }else {
            maps.put("msg","处理失败！");
        }
        return maps;
    }

    @GetMapping("/get_date")
    @ApiOperation("下载附件，需要传文件名称")
    public Map<String,Object> get_date(String documentName, HttpServletResponse response){

        Boolean aBoolean = UploadFile.fileDownLoad(response, documentName);

        Map<String,Object> map = new HashMap<>();
        if (aBoolean){
            map.put("msg","文件请求下载成功！");
        }else{
            map.put("msg","下载请求失败！");
        }
        return map;
    }
}
