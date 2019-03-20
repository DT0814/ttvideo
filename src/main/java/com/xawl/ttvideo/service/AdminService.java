package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.AdminDao;
import com.xawl.ttvideo.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminDao dao;


    public Admin findByAccount(String account) {
        Admin admin = new Admin();
        admin.setAccount(account);
        Example example = Example.of(admin);
        Optional<Admin> one = dao.findOne(example);
        if (one.isPresent()) {
            return one.get();
        } else {
            return null;
        }
    }

    public Admin findByAid(Integer aid) {
        Optional<Admin> byId = dao.findById(aid);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return null;
        }
    }

    public Page<Admin> findAll(Integer pageNumber, Admin admin) {
        Sort sort = new Sort(Sort.Direction.DESC, "aid");
        Pageable pageable = PageRequest.of(pageNumber, 8, sort);
        Example<Admin> example = Example.of(admin, ExampleMatcher.matching());
        Page<Admin> all = dao.findAll(example, pageable);
        return all;
    }

    public Admin add(Admin admin) {
        return dao.saveAndFlush(admin);
    }

    public Admin update(Admin admin) {
        Admin byAid = findByAid(admin.getAid());
        if (admin.getPass() != null) {
            byAid.setPass(admin.getPass());
        }
        if (admin.getAname() != null) {
            byAid.setAname(admin.getAname());
        }
        if (admin.getPhone() != null) {
            byAid.setPhone(admin.getPhone());
        }
        admin = dao.saveAndFlush(byAid);
        return admin;
    }

    public void delete(Integer aid) {
        dao.deleteById(aid);
    }

    public void rePass(Integer aid) {
        Admin byAid = findByAid(aid);
        byAid.setPass(byAid.getAccount());
        Admin admin = dao.saveAndFlush(byAid);
    }
}
