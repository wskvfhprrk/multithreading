package com.hejz.thread.threadcoreknowledge.createthreads;

/**
 * @author: hejz
 * @Description: 同时使用两种方法实现多线程
 * @Date: 2020/1/17 17:09
 */
public class BothRunnableThread {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自runnable");
            }
        }){
            @Override
            public void run() {
                System.out.println("我来自thread");
            }
        };
        thread.start();
    }
}
