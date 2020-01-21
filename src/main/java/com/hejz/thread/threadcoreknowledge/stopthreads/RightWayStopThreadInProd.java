package com.hejz.thread.threadcoreknowledge.stopthreads;

/**
 * @author: hejz
 * @Description: 最佳实距，catch了InterruptedException之后，优先选择：在方法签名中抛出异常，那么run()
 * 就会强制try/catch
 * @Date: 2020/1/19 17:19
 */
public class RightWayStopThreadInProd implements Runnable {
    @Override
    public void run() {
        try {
            while (true && !Thread.currentThread().isInterrupted()) {
                System.out.println("go.........");
                throwInMothed();
            }
        } catch (InterruptedException e) {
            System.out.println("处理业务逻辑");
            e.printStackTrace();
        }
    }

    private void throwInMothed() throws InterruptedException {
//        try {
        Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        try {
            thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
