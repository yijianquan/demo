package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName() + Arrays.toString(authentication.getAuthorities().toArray());
    }

//    @RequestMapping("/login")
//    public String login(@RequestParam String username, @RequestParam String password) {
//        if (userService.login(username, password)) {
//            return "登录成功";
//        }
//        return "登录失败!";
//    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @GetMapping(value = "get")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/getcurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

}
