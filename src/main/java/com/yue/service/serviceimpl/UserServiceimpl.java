package com.yue.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
        project.setSubmitTime(date);//??????????????????

//        shiro
//        ???????????????
//        Subject subject = SecurityUtils.getSubject();
//        User user = (User) subject.getPrincipal();
//        String user_name = user.getUsername();

//        ??????????????????????????????
//        ?????????
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String user_name = String.valueOf(authentication.getPrincipal());
//        User user = do_login(user_name);
//        int user_id = user.getId();

//        ??????????????????
        UsernamePasswordAuthenticationToken principal = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) principal.getPrincipal();
        User user = userDetail.getUser();
        String user_name = user.getUsername();
        int user_id = user.getId();


//        ??????????????????
        project.setUserId(user_id);
        project.setUserUsername(user_name);

        try{
//            ???????????????-1????????????0???????????????????????????-2???1?????????????????????
            if(project.getState()==-1){
                project.setState(0);//??????????????????????????????
                projectDao.updateById(project);
            }else {
                project.setState(0);//??????????????????????????????
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

    @Override
    public Boolean submit_file(int id, String filename) {
        UpdateWrapper<Project> wrapper = new UpdateWrapper<>();
        try{
            wrapper.eq("id",id);
            wrapper.set("date",filename);
            projectDao.update(null,wrapper);
            return true;
        }
        catch (Exception e){
            return false;
        }


    }

}
