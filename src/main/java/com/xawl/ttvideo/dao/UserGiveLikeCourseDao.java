package com.xawl.ttvideo.dao;

import com.xawl.ttvideo.pojo.UserGiveLikeCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGiveLikeCourseDao extends JpaRepository<UserGiveLikeCourse,Integer> {
}
