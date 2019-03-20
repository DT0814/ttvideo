package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.pojo.User;
import com.xawl.ttvideo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public Result login(User user, String code, HttpSession session) {
        System.out.println(user + "  " + code);
        if (!((String) session.getAttribute("code")).equalsIgnoreCase(code)) {
            return Result.err(401, "验证码错误!");
        }
        User obj = service.findByAccount(user.getAccount());
        if (obj != null && obj.getPass().equals(user.getPass())) {
            return Result.success(200, "", obj);
        } else {
            return Result.err(401, "账号或密码错误!");
        }
    }

    @PostMapping("/register")
    public Result register(User user, String code, HttpSession session) {
        if (!((String) session.getAttribute("code")).equalsIgnoreCase(code)) {
            return Result.err(401, "验证码错误!");
        }
        User obj = service.findByAccount(user.getAccount());
        if (obj != null) {
            return Result.success(401, "账号已经被注册了!");
        } else {
            user.setBalence(0D);
            user.setAge(0);
            user.setIntime(new Date());
            user.setImg("images/touxiang/1.jpg");
            user.setIsvip(false);
            user.setFirstlogin(true);
            user.setGender(1);
            user.setUid(null);
            user.setName("新用戶" + user.getAccount());
            service.add(user);
            return Result.success(200, "注册成功", user);
        }
    }
}
