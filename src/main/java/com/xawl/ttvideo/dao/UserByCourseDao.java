package com.xawl.ttvideo.dao;

import com.xawl.ttvideo.pojo.UserByCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserByCourseDao extends JpaRepository<UserByCourse, Integer> {
}
