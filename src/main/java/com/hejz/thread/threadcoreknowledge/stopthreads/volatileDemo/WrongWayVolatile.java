package com.hejz.thread.threadcoreknowledge.stopthreads.volatileDemo;

/**
 * @author: hejz
 * @Description: 演示volatile的局限：看似可行
 * @Date: 2020/1/20 16:54
 */
public class WrongWayVolatile implements Runnable {
    private static volatile Boolean canceled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                Thread.sleep(1);
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new WrongWayVolatile());
        thread.start();
        try {
            thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        canceled = true;
    }
}
