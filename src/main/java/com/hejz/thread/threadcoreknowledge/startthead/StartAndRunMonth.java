package com.hejz.thread.threadcoreknowledge.startthead;

/**
 * @author: hejz
 * @Description: 使用start和run同时启动线程
 * @Date: 2020/1/19 11:00
 */
public class StartAndRunMonth {
    public static void main(String[] args) {
        Runnable runnable =()->{
            System.out.println(Thread.currentThread().getName());
        };
        System.out.println("使用run启动线程");
        runnable.run();
        System.out.println("使用start启动线程");
        new Thread(runnable).start();
    }
}
