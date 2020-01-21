package com.hejz.thread.threadcoreknowledge.stopthreads;

/**
 * @author: hejz
 * @Description: 循环内部,sleep抓取了InterruptedException异常的话，无法中止线程的。
 * @Date: 2020/1/19 14:42
 */
public class CantInturrupte {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int i = 0;

            while (i <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (i % 100 == 0) {
                    System.out.println(i + "是100位数");
                }
                i++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(5000);
        thread.interrupt();
    }
}
