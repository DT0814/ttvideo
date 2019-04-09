package com.xawl.ttvideo.dao;

import com.xawl.ttvideo.pojo.UserRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecommendDao extends JpaRepository<UserRecommend, Integer> {
}
