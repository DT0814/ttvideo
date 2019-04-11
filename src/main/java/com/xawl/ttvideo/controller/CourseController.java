package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.pojo.Course;
import com.xawl.ttvideo.pojo.Result;
import com.xawl.ttvideo.service.CatalogService;
import com.xawl.ttvideo.service.CourseService;
import com.xawl.ttvideo.service.TeacherService;
import com.xawl.ttvideo.service.UserGiveLikeCourseService;
import com.xawl.ttvideo.utils.PageInfo;
import com.xawl.ttvideo.utils.PageUtils;
import com.xawl.ttvideo.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseService service;
    @Autowired
    private UserGiveLikeCourseService userGiveLikeCourseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CatalogService catalogService;
    private final static String uploadPath = "images/";

    @RequestMapping("add")
    public Result add(MultipartFile file, Course course) {
        try {
            if (null != file && file.getSize() != 0) {
                try {
                    course.setImg(ResourceUtils.upload(file, uploadPath));
                    course.setIntime(new Date());
                    course.setTname(teacherService.findTnameBuyTid(course.getTid()));
                    course.setCaname(catalogService.findCanameBycaid(course.getCaid()));
                    course.setClickNum(0);
                    course.setDel(0);
                    course = service.add(course);
                    return Result.success(course);
                } catch (IOException e) {
                    e.printStackTrace();
                    return Result.err(401, "上传课程图片出错");
                }
            } else {
                return Result.err(401, "请上传课程图片");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(401, "添加课程失败");
        }
    }

    @RequestMapping("delete")
    public Result delete(Integer cid, String img) {
        try {
            service.delete(cid);
            ResourceUtils.deleteResource(img);
            return Result.success(200, "");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.success(401, "删除失败");
        }

    }

    @RequestMapping("update")
    public Result update(Course course, MultipartFile file) {
        if (null != file && file.getSize() != 0) {
            try {
                ResourceUtils.deleteResource(course.getImg());
                course.setImg(ResourceUtils.upload(file, uploadPath));
            } catch (IOException e) {
                e.printStackTrace();
                return Result.err(301, "上传课程图片出错");
            }
        }
        course.setTname(teacherService.findTnameBuyTid(course.getTid()));
        course.setCaname(catalogService.findCanameBycaid(course.getCaid()));
        course = service.update(course);
        return Result.success(200, "", course);
    }


    @GetMapping("findAll")
    public Result findAll(Integer page, Course course) {
        Page<Course> all = service.findAll(--page, course);
        PageInfo<Course> pageInfo = new PageUtils<Course>().getPageInfo(all);
        return Result.success(pageInfo);
    }

    @GetMapping("findExcellentCourse")
    public Result ExcellentCourse() {
        List<Course> res = service.findExcellentCourse();
        return Result.success(200, "", res);
    }

    @GetMapping("newCourse")
    public Result newCourse() {
        List<Course> res = service.newCourse();
        return Result.success(200, "", res);
    }

    @GetMapping("findByCaid")
    public Result findByCaid(Integer caid) {
        List<Course> res = service.findBycaid(caid);
        return Result.success(res);
    }

    @GetMapping("find")
    public Result find(Integer caid, Integer num) {
        List<Course> res = service.find(caid, num);
        return Result.success(res);
    }

    @GetMapping("findCourseByCid")
    public Result findCourseByCid(Integer cid) {
        Course res = service.findCourseByCid(cid);
        return Result.success(res);
    }

    @GetMapping("findByCname")
    public Result findByCname(String cname) {
        List<Course> res = service.findByCname(cname);
        return Result.success(res);
    }

    @GetMapping("recommend")
    public Result recommend(Integer uid) {
        List<Course> res = service.findRecommend(uid);
        return Result.success(res);
    }

    @PostMapping("giveLike")
    public Result giveLike(Integer uid, Integer cid) {
        service.giveLike(cid);
        userGiveLikeCourseService.giveLike(uid,cid);
        return Result.success(null);
    }
}
