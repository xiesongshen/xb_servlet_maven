package com.scheduled;

import com.service.MeetingService;

import java.util.TimerTask;

/**
 * @auth admin
 * @date 2020/3/23 15:45
 * @Description
 */
public class MeetingTask extends TimerTask {

    private boolean isRunning = false;

    private MeetingService meetingService = new MeetingService();

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            //开始执行
            meetingService.updateStatusTask();
            //执行结束
            isRunning = false;
        }
    }

}
