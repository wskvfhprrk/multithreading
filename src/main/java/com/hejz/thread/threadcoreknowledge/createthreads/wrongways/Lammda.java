package com.hejz.thread.threadcoreknowledge.createthreads.wrongways;

/**
 * @author: hejz
 * @Description: 使用lammda表达式建立线程
 * @Date: 2020/1/17 17:40
 */
public class Lammda {
    public static void main(String[] args) {
        new Thread(()-> System.out.println(Thread.currentThread().getName())).start();
    }
}
