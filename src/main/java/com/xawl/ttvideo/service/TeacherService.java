package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.TeacherDao;
import com.xawl.ttvideo.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherDao dao;


    public Teacher findByTid(Integer tid) {
        Optional<Teacher> byId = dao.findById(tid);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            return null;
        }
    }

    public Page<Teacher> findAll(Integer pageNumber, Teacher teacher) {
        Sort sort = new Sort(Sort.Direction.DESC, "tid");
        Pageable pageable = PageRequest.of(pageNumber, 8, sort);
        Example<Teacher> example = Example.of(teacher, ExampleMatcher.matching());
        Page<Teacher> all = dao.findAll(example, pageable);
        return all;
    }

    public Teacher add(Teacher teacher) {
        return dao.saveAndFlush(teacher);
    }

    public Teacher update(Teacher teacher) {
        Teacher byAid = findByTid(teacher.getTid());
        if (teacher.getTname() != null) {
            byAid.setTname(teacher.getTname());
        }
        if (teacher.getIntroduce() != null) {
            byAid.setIntroduce(teacher.getIntroduce());
        }
        if (teacher.getPhone() != null) {
            byAid.setPhone(teacher.getPhone());
        }
        teacher = dao.saveAndFlush(byAid);
        return teacher;
    }

    public void delete(Integer tid) {
        dao.deleteById(tid);
    }

    public List<Teacher> findAll() {
        return dao.findAll();
    }

    public String findTnameBuyTid(Integer tid) {
        return findByTid(tid).getTname();
    }
}
