package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Course;
import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.pojo.UserGiveLikeCourse;
import com.xawl.ttvideo.service.CourseService;
import com.xawl.ttvideo.service.UserGiveLikeCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("userGiveLikeCourse")
public class UserGiveLikeCourseController {
    @Autowired
    private UserGiveLikeCourseService service;
    @Autowired
    private CourseService courseService;

    @GetMapping("getByUid")
    private Result getByUid(Integer uid) {
        List<UserGiveLikeCourse> list = service.getByUid(uid);
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
}
