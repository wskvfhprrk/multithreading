package com.hejz.thread.threadcoreknowledge.startthead;

/**
 * @author: hejz
 * @Description: 两次执行start()方法错误
 * @Date: 2020/1/19 11:11
 */
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread=new Thread();
        thread.start();
        thread.start();
    }
}
