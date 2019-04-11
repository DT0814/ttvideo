package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.UserByCourseDao;
import com.xawl.ttvideo.pojo.UserByCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserByCourseService {
    @Autowired
    private UserByCourseDao dao;

    public void by(Integer uid, Integer cid) {
        UserByCourse userByCourse = new UserByCourse();
        userByCourse.setUid(uid);
        userByCourse.setCid(cid);
        dao.saveAndFlush(userByCourse);
    }

    public List<UserByCourse> getByUid(Integer uid) {
        UserByCourse userByCourse = new UserByCourse();
        userByCourse.setUid(uid);
        Example<UserByCourse> example = Example.of(userByCourse);
        return dao.findAll(example);
    }

    public boolean isBy(Integer uid, Integer cid) {
        UserByCourse userByCourse = new UserByCourse();
        userByCourse.setUid(uid);
        userByCourse.setCid(cid);
        Example<UserByCourse> example = Example.of(userByCourse);
        Optional<UserByCourse> one = dao.findOne(example);
        return one.isPresent();
    }
}
