package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Course;
import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.pojo.UserByCourse;
import com.xawl.ttvideo.service.CourseService;
import com.xawl.ttvideo.service.UserByCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("userByCourse")
public class UserByCourseController {
    @Autowired
    private UserByCourseService service;
    @Autowired
    private CourseService courseService;

    @PostMapping("by")
    private Result by(Integer uid, Integer cid) {
        service.by(uid, cid);
        return Result.success(null);
    }

    @GetMapping("getByUid")
    private Result getByUid(Integer uid) {
        List<UserByCourse> list = service.getByUid(uid);
        if (list != null && list.size() > 0) {
            Integer arr[] = new Integer[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i).getCid();
            }
            List<Course> res = courseService.findByCids(arr);
            return Result.success(res);
        } else {
            return Result.success(null);
        }
    }

    @GetMapping("isBy")
    private Result isBy(Integer uid, Integer cid) {
        boolean b = service.isBy(uid, cid);
        return Result.success(b);
    }
}
