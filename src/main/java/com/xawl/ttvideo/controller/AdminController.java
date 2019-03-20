package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Admin;
import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.service.AdminService;
import com.xawl.ttvideo.utils.PageInfo;
import com.xawl.ttvideo.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService service;

    @RequestMapping("login")
    public Result login(Admin admin) {
        Admin obj = service.findByAccount(admin.getAccount());
        if (obj != null && obj.getPass().equals(admin.getPass())) {
            return Result.success(200, "", obj);
        } else {
            return Result.err(401, "账号或密码错误!");
        }
    }

    @RequestMapping("findByAid")
    public Result findByAid(Integer aid) {
        Admin admin = service.findByAid(aid);
        if (null == admin) {
            return Result.err(401, "管理员不存在");
        } else {
            return Result.success(200, "", admin);
        }
    }

    @GetMapping("findAll")
    public Result findAll(Integer page, Admin admin) {
        System.out.println(admin);
        Page<Admin> all = service.findAll(--page, admin);
        PageInfo<Admin> pageInfo = new PageUtils<Admin>().getPageInfo(all);
        return Result.success(pageInfo);
    }

    @RequestMapping("add")
    public Result add(Admin admin) {
        admin.setIntime(new Date());
        admin.setType(2);
        System.out.println(admin);
        Admin obj = service.add(admin);
        return Result.success(200, "", obj);
    }

    @RequestMapping("update")
    public Result update(Admin admin) {
        System.out.println(admin);
        admin = service.update(admin);
        return Result.success(200, "ok", admin);
    }

    @RequestMapping("delete")
    private Result delete(Integer aid) {
        service.delete(aid);
        return Result.success(200, "ok");
    }

    @RequestMapping("rePass")
    private Result rePass(Integer aid) {
        service.rePass(aid);
        return Result.success(200, "ok");
    }
}
