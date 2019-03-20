package com.xawl.ttvideo.dao;

import com.xawl.ttvideo.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseDao extends JpaRepository<Course, Integer> {
    @Query(value = "select c from Course c where c.cname like %:name%")
    List<Course> findByCnameLike(@Param("name") String name);
}
