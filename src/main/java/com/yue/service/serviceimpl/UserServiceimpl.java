package com.yue.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yue.dao.ProjectDao;
import com.yue.dao.UserDao;
import com.yue.domain.Project;
import com.yue.domain.User;
import com.yue.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private UserDao userDao;
    @Override
    public List<Project> get_my_project(String name) {
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Project::getUserUsername,name).orderByDesc(Project::getSubmitTime).last("limit 1");
        return projectDao.selectList(wrapper);
    }

    public List<Project> get_my_all_project(String name) {
        LambdaQueryWrapper<Project> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Project::getUserUsername,name).eq(Project::getState,1);
        return projectDao.selectList(wrapper);
    }

    @Override
    public User do_login(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return userDao.selectOne(wrapper);
    }

    @Override
    public Boolean submit_projict(Project project) {
        Timestamp date =  new Timestamp(System.currentTimeMillis());
        project.setSubmitTime(date);//设置提交时间

//        与用户关联
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        String user_name = user.getUsername();
        int user_id = user.getId();
        project.setUserId(user_id);
        project.setUserUsername(user_name);

        try{
            if(project.getState()==-1){
                project.setState(0);//设置项目状态为进行中
                projectDao.updateById(project);
            }else if(project.getState()==1||project.getState()==-2){
                project.setState(0);//设置项目状态为进行中
                projectDao.insert(project);
            }

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

}
