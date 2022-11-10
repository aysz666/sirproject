package com.yue.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yue.dao.ProjectDao;
import com.yue.domain.Project;
import com.yue.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSeviceimpl implements AdminService {

    @Autowired
    private ProjectDao projectDao;

    public List<Project> get_all_project(){
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("state",0);
        return projectDao.selectList(wrapper);
    }

    @Override
    public List<Project> search_project(String project_name) {
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper
                .like(true,"project_name",project_name)
                .or()
                .eq("approval_number",project_name);
        return projectDao.selectList(wrapper);
    }


    @Override
    public Boolean dosu_project(String id, String suggestion,String state) {
        UpdateWrapper<Project> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",Integer.parseInt(id));
        wrapper.set("state",Integer.parseInt(state));
        wrapper.set("suggestion",suggestion);
        try{
            projectDao.update(null,wrapper);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    public List<Project> get_success_project(){
        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        wrapper.eq("state",1);
        return projectDao.selectList(wrapper);
    }
}
