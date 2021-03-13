package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.pojo.User;
import com.xawl.ttvideo.service.UserService;
import com.xawl.ttvideo.utils.PageInfo;
import com.xawl.ttvideo.utils.PageUtils;
import com.xawl.ttvideo.utils.UserBased;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            service.add(init(user));
            return Result.success(200, "注册成功", user);
        }
    }

    @PostMapping("/add")
    public Result add(User user) {
        User obj = service.findByAccount(user.getAccount());
        if (obj != null) {
            return Result.success(401, "账号已经被注册了!");
        } else {
            service.add(init(user));
            return Result.success(200, "注册成功", user);
        }
    }

    private User init(User user) {
        user.setBalence(0D);
        user.setAge(0);
        user.setIntime(new Date());
        user.setImg("images/touxiang/1.jpg");
        user.setIsvip(false);
        user.setFirstlogin(true);
        user.setGender(1);
        user.setUid(null);
        user.setName("新用戶" + user.getAccount());
        return user;
    }

    @PostMapping("/pingfen")
    public Result pingfen(Integer uid, Integer cid, String score) {
        boolean b = UserBased.writeToUserRecommendLine(uid, cid, score);
        if (b) {
            return Result.success(null);
        } else {
            return Result.err(401, "评分失败");
        }
    }

    @PostMapping("/update")
    public Result update(User user) {
        service.update(user);
        return Result.success(null);
    }

    @PostMapping("/bySvip")
    public Result bySvip(Integer uid) {
        boolean b = service.bySvip(uid);
        if (b) {
            return Result.success(null);
        } else {
            return Result.err(401, "购买失败");
        }
    }

    @PostMapping("/updatePass/{uid}")
    public Result updatePass(@PathVariable Integer uid, String newPass, String pass) {
        User byUid = service.findByUid(uid);
        if (!pass.equals(byUid.getPass())) {
            return Result.err(401, "旧密码错误");
        }
        byUid.setPass(newPass);
        service.update(byUid);
        return Result.success(null);

    }

    @GetMapping("findAll")
    public Result findAll(Boolean selectAll, Integer page, User user) {
        System.out.println(user);
        if (selectAll != null) {
            return Result.success(service.findAll());
        } else {
            Page<User> all = service.findAll(--page, user);
            PageInfo<User> pageInfo = new PageUtils<User>().getPageInfo(all);
            return Result.success(pageInfo);
        }
    }

    @GetMapping("/{user_id}")
    public Result findAll(@PathVariable Integer user_id) {
        System.out.println(user_id);
        return Result.success(service.findByUid(user_id));
    }

    @GetMapping("delete")
    public Result delete(User user) {
        service.deleteById(user);
        return Result.success(null);
    }

    @GetMapping("rePass")
    public Result rePass(User user) {
        service.rePass(user);
        return Result.success(null);
    }
}
