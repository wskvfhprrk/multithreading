package com.hejz.thread.threadcoreknowledge.createthreads;

/**
 * @author: hejz
 * @Description: 用thread方式创建线程
 * @Date: 2020/1/17 16:42
 */
public class ThreadStyle extends Thread{

    @Override
    public void run() {
        System.out.println("用thread方式创建线程");
    }

    public static void main(String[] args) {
        ThreadStyle threadStyle = new ThreadStyle();
        threadStyle.start();
    }
}
