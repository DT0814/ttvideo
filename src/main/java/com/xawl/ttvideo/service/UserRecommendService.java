package com.xawl.ttvideo.service;

import com.xawl.ttvideo.dao.UserRecommendDao;
import com.xawl.ttvideo.pojo.UserRecommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRecommendService {
    @Autowired
    private UserRecommendDao dao;

    public void add(UserRecommend userRecommend) {
        dao.saveAndFlush(userRecommend);
    }
}
