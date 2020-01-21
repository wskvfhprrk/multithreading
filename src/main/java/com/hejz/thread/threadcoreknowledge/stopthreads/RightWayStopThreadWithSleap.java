package com.hejz.thread.threadcoreknowledge.stopthreads;

/**
 * @author: hejz
 * @Description: 带有sleap中断线程的写法
 * @Date: 2020/1/19 14:42
 */
public class RightWayStopThreadWithSleap {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int i = 0;
            try {
                while (!Thread.currentThread().isInterrupted() && i <= 300) {
                    if (i % 100 == 0) {
                        System.out.println(i + "是100位数");
                    }
                    i++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(500);
        thread.interrupt();
    }
}
