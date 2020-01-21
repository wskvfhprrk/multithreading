package com.hejz.thread.threadcoreknowledge.stopthreads;

/**
 * @author: hejz
 * @Description: 使用stop()停止线程，会导致线程突然停止，造成脏数据——线程内部数据没有办法执行完毕。
 * @Date: 2020/1/20 14:14
 */
public class StopThread implements Runnable {
    //模拟有5个连队每个连队10人，叫到人去领取装备，被叫停后去打仗
    @Override
    public void run() {
            for (int i = 1; i <= 5; i++) {
                System.out.println(i + "连队开始领取武器");
                for (int j = 1; j <= 10; j++) {
                    System.out.println(j);
                    try {
                        Thread.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(i + "连队领取武器完毕");
            }
    }

    public static void main(String[] args)  {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
