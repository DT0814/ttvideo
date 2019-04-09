package com.xawl.ttvideo.listener;

import com.xawl.ttvideo.controller.UserController;
import com.xawl.ttvideo.pojo.UserRecommend;
import com.xawl.ttvideo.service.UserRecommendService;
import com.xawl.ttvideo.utils.UserBased;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebListener
public class myServletContextListener implements ServletContextListener {
    @Autowired
    UserRecommendService service;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext())
                .getAutowireCapableBeanFactory().autowireBean(this);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    List<UserRecommend> calculation = UserBased.calculation();
                    calculation.stream().forEach(userRecommend -> {
                        service.add(userRecommend);
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TasteException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000 * 60);
    }
}