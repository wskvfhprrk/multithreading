package com.hejz.thread.threadcoreknowledge.stopthreads;

/**
 * @author: hejz
 * @Description: run抛出checked Exception,只能用try/catch
 * @Date: 2020/1/19 17:14
 */
public class RunThrowException {

    public void aVoid() throws Exception {
        throw new Exception();
    }
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
