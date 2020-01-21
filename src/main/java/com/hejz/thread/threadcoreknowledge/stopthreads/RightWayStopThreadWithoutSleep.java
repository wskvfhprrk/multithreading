package com.hejz.thread.threadcoreknowledge.stopthreads;

/**
 * @author: hejz
 * @Description: run方法中没有sleep或wait停止线程
 * @Date: 2020/1/19 14:28
 */
public class RightWayStopThreadWithoutSleep implements Runnable {
    @Override
    public void run() {
        int i = 0;
        while (!Thread.currentThread().isInterrupted() && i <= Integer.MAX_VALUE / 2) {
            if (i % 1000 == 0) {
                System.out.println(i + "是10000的倍数");
            }
            i++;
        }
        System.out.println("任务结了");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
