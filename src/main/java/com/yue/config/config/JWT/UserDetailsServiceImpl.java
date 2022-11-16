package com.yue.config.config.JWT;

import com.yue.domain.User;
import com.yue.service.serviceimpl.UserServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserServiceimpl userServiceimpl;
    @Override
    public UserDetail loadUserByUsername(String username)throws UsernameNotFoundException {
            User user = userServiceimpl.do_login(username);
        if (user==null){
                throw  new UsernameNotFoundException("用户不存在");
        }
        return new UserDetail(user,username,user.getPassword(),AuthorityUtils.createAuthorityList("ROLE_"+user.getPosition()));


    }
}
