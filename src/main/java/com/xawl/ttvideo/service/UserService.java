package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.UserDao;
import com.xawl.ttvideo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao dao;

    public User findByAccount(String account) {
        User user = new User();
        user.setAccount(account);
        Example example = Example.of(user);
        Optional one = dao.findOne(example);
        if (one.isPresent()) {
            return (User) one.get();
        } else {
            return null;
        }
    }

    public User add(User user) {
        dao.saveAndFlush(user);
        return null;
    }
}
