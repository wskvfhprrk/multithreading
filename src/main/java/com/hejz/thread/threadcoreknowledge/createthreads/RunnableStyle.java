package com.hejz.thread.threadcoreknowledge.createthreads;

/**
 * @author: hejz
 * @Description: 使用runnabl方式创建线程
 * @Date: 2020/1/17 16:38
 */
public class RunnableStyle implements Runnable{

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }
    @Override
    public void run() {
        System.out.println("用runnable方法建立线程");
    }
}
