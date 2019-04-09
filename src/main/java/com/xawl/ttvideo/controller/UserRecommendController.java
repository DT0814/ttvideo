package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.service.UserRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("userRecommend")
public class UserRecommendController {
    @Autowired
    private UserRecommendService service;
}
