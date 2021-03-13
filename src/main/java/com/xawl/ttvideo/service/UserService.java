package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.UserDao;
import com.xawl.ttvideo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    public User findByUid(Integer uid) {
        Optional<User> byId = dao.findById(uid);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return null;
        }
    }

    public User add(User user) {
        dao.saveAndFlush(user);
        return null;
    }

    public boolean bySvip(Integer uid) {
        Optional<User> byId = dao.findById(uid);
        if (byId.isPresent()) {
            User user = byId.get();
            user.setIsvip(true);
            dao.saveAndFlush(user);
            return true;
        } else {
            return false;
        }
    }

    public Page<User> findAll(Integer pageNumber, User user) {
        Sort sort = Sort.by(Sort.Direction.DESC, "uid");
        Pageable pageable = PageRequest.of(pageNumber, 8, sort);
        Example<User> example = Example.of(user, ExampleMatcher.matching());
        Page<User> all = dao.findAll(example, pageable);
        return all;

    }

    public List<User> findAll() {
        return dao.findAll();
    }

    public void deleteById(User user) {
        dao.deleteById(user.getUid());
    }

    public void rePass(User user) {
        User byUid = findByUid(user.getUid());
        byUid.setPass(byUid.getAccount());
        dao.saveAndFlush(byUid);
    }

    public void update(User user) {
        User byUid = findByUid(user.getUid());
        if (user.getAge() != null) {
            byUid.setAge(user.getAge());
        }
        if (user.getName() != null) {
            byUid.setName(user.getName());
        }
        if (user.getPhone() != null) {
            byUid.setPhone(user.getPhone());
        }
        dao.saveAndFlush(byUid);
    }
}
