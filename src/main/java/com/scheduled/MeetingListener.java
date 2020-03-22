package com.scheduled;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Date;
import java.util.Timer;

/**
 * @auth admin
 * @date 2020/3/22 15:29
 * @Description
 */
public class MeetingListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Timer timer = new Timer(true);
        //每隔多长时间执行一次
        timer.schedule(new MeetingTask(), new Date(), 1 * 1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
