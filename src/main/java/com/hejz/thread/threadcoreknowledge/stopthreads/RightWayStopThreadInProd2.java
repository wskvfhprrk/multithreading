package com.hejz.thread.threadcoreknowledge.stopthreads;

/**
 * @author: hejz
 * @Description: 最佳实距，在catch中恢复中断Thread.currentThread().interrupt()，以便能够检查中断
 * @Date: 2020/1/19 17:19
 */
public class RightWayStopThreadInProd2 implements Runnable {
    @Override
    public void run() {

        if (Thread.currentThread().isInterrupted()) {
            System.out.println("当前线程已经被中断了");
            return;
        }

        while (true && !Thread.currentThread().isInterrupted()) {
            System.out.println("go.........");
            try {
                throwInMothed();
            } catch (InterruptedException e) {
                System.out.println("处理业务逻辑");
                e.printStackTrace();
            }
        }
    }

    private void throwInMothed() throws InterruptedException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
