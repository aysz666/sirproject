package com.yue.config.config.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password= String.valueOf(authentication.getCredentials());  //获取密码
        UserDetail userDetail= userDetailsService.loadUserByUsername(authentication.getName());

        boolean checkPassword = bCryptPasswordEncoder.matches(password,bCryptPasswordEncoder.encode(userDetail.getPassword()));
        if(!checkPassword){
            throw new BadCredentialsException("密码不正确，请重新登录!");
        }
        return new UsernamePasswordAuthenticationToken(authentication.getName(),password,userDetail.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
