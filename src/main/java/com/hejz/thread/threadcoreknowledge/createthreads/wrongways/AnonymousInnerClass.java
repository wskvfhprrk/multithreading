package com.hejz.thread.threadcoreknowledge.createthreads.wrongways;

/**
 * @author: hejz
 * @Description: 匿名内部类创建线程
 * @Date: 2020/1/17 17:35
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
