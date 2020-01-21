package com.hejz.thread.threadcoreknowledge.stopthreads;

/**
 * @author: hejz
 * @Description: 执行过程中每次循环都会带有sleap中断线程的写法，同样会抛异常：java.lang.InterruptedException: sleep interrupted
 * @Date: 2020/1/19 14:42
 */
public class RightWayStopThreadWithSleapEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int i = 0;
            try {
                //这里判断Thread.currentThread().isInterrupted()与否不重要了——因为在中断过程中检测。
                while (i <= 10000) {
                    if (i % 100 == 0) {
                        System.out.println(i + "是100位数");
                    }
                    i++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        thread.sleep(5000);
        thread.interrupt();
    }
}
