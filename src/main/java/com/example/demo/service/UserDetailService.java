package com.example.demo.service;

import com.example.demo.repository.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户登录验证。。。");
        return userMapper.findUserByUsername(username);
    }

}

