package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.pojo.Teacher;
import com.xawl.ttvideo.service.TeacherService;
import com.xawl.ttvideo.utils.PageInfo;
import com.xawl.ttvideo.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService service;


    @RequestMapping("findByTid")
    public Result findByAid(Integer tid) {
        Teacher teacher = service.findByTid(tid);
        if (null == teacher) {
            return Result.err(401, "教师不存在");
        } else {
            return Result.success(200, "", teacher);
        }
    }

    @GetMapping("findAll")
    public Result findAll(Boolean selectAll, Integer page, Teacher teacher) {
        if (selectAll != null) {
            return Result.success(service.findAll());
        } else {
            System.out.println(teacher);
            Page<Teacher> all = service.findAll(--page, teacher);
            PageInfo<Teacher> pageInfo = new PageUtils<Teacher>().getPageInfo(all);
            return Result.success(pageInfo);
        }

    }

    @RequestMapping("add")
    public Result add(Teacher teacher) {
        System.out.println(teacher);
        Teacher obj = service.add(teacher);
        return Result.success(200, "", obj);
    }

    @RequestMapping("update")
    public Result update(Teacher teacher) {
        System.out.println(teacher);
        teacher = service.update(teacher);
        return Result.success(200, "ok", teacher);
    }

    @RequestMapping("delete")
    private Result delete(Integer tid) {
        service.delete(tid);
        return Result.success(200, "ok");
    }
}
