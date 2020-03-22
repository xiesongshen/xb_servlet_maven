package com.scheduled;

import java.util.TimerTask;

/**
 * @auth admin
 * @date 2020/3/22 15:31
 * @Description
 */
public class MeetingTask extends TimerTask {

    private boolean isRunning = false;

    @Override
    public void run() {
        if (!isRunning) {
            isRunning = true;
            //开始执行
            doSomeThing();
            //执行结束
            isRunning = false;
        }
    }

    public void doSomeThing() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MeetingTask doSomeThing");
    }
}
