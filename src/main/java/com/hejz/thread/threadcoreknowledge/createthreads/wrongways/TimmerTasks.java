package com.hejz.thread.threadcoreknowledge.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: hejz
 * @Description: 定时器创建线程
 * @Date: 2020/1/17 17:30
 */
public class TimmerTasks {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },1000,1000);

    }
}
