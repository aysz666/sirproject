package com.yue.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yue.config.config.JWT.UserDetail;
import com.yue.dao.ProjectDao;
import com.yue.dao.ProjectFuDao;
import com.yue.dao.UserDao;
import com.yue.domain.Project;
import com.yue.domain.ProjectFu;
import com.yue.domain.User;
import com.yue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private UserDao userDao;

    @Autowired
    private ProjectFuDao projectFuDao;
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

//        shiro
//        与用户关联
//        Subject subject = SecurityUtils.getSubject();
//        User user = (User) subject.getPrincipal();
//        String user_name = user.getUsername();

//        用账号查处用户的信息
//        带优化
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String user_name = String.valueOf(authentication.getPrincipal());
//        User user = do_login(user_name);
//        int user_id = user.getId();

//        获取用户信息
        UsernamePasswordAuthenticationToken principal = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) principal.getPrincipal();
        User user = userDetail.getUser();
        String user_name = user.getUsername();
        int user_id = user.getId();


//        关联用户信息
        project.setUserId(user_id);
        project.setUserUsername(user_name);

        try{
//            如果状态为-1，则改为0表审核中，若状态为-2或1，需要插入操作
            if(project.getState()==-1){
                project.setState(0);//设置项目状态为进行中
                projectDao.updateById(project);
            }else {
                project.setState(0);//设置项目状态为进行中
                projectDao.insert(project);
            }

        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }
    @Override
    public List<ProjectFu> getFailing(int id){
        QueryWrapper<ProjectFu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ProjectFu::getProjectId,id);
        return projectFuDao.selectList(wrapper);
    }

}
