package com.xawl.ttvideo.dao;

import com.xawl.ttvideo.pojo.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherDao extends JpaRepository<Teacher, Integer> {
}
