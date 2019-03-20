package com.xawl.ttvideo.controller;

import com.xawl.ttvideo.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController()
@RequestMapping("/common")
public class CommonController {
    @GetMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //code 验证码session名称
        CaptchaUtil.getCode(request, response);
    }
}
