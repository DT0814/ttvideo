package com.xawl.ttvideo.listener;

import com.xawl.ttvideo.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class myServletContextListener implements ServletContextListener {
    @Autowired
    UserController controller;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory().autowireBean(this);
        System.out.println("服务器启动");
        System.out.println(controller);
    }
}