package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.UserGiveLikeCourseDao;
import com.xawl.ttvideo.pojo.Course;
import com.xawl.ttvideo.pojo.UserGiveLikeCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserGiveLikeCourseService {
    @Autowired
    private UserGiveLikeCourseDao dao;

    public void giveLike(Integer uid, Integer cid) {
        UserGiveLikeCourse userGiveLikeCourse = new UserGiveLikeCourse();
        userGiveLikeCourse.setCid(cid);
        userGiveLikeCourse.setUid(uid);
        Example<UserGiveLikeCourse> example = Example.of(userGiveLikeCourse);
        Optional<UserGiveLikeCourse> one = dao.findOne(example);
        if (!one.isPresent()) {
            userGiveLikeCourse.setIntime(new Date());
            dao.saveAndFlush(userGiveLikeCourse);
        }
    }

    public List<UserGiveLikeCourse> getByUid(Integer uid) {
        UserGiveLikeCourse userGiveLikeCourse = new UserGiveLikeCourse();
        userGiveLikeCourse.setUid(uid);
        Example<UserGiveLikeCourse> example = Example.of(userGiveLikeCourse);
        List<UserGiveLikeCourse> all = dao.findAll(example);
        return all;
    }
}
