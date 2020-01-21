package com.hejz.thread.threadcoreknowledge.volatileDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author: hejz
 * @Description: 演示陷入阴塞时，volatile无法停止线程，生产者速度快，消费者消费比较慢，消费者速度慢时
 * 会阻塞生产者队列，
 * @Date: 2020/1/20 17:03
 */
public class WrongWayVolatileCantStop {


    public static void main(String[] args) {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            try {
                System.out.println(consumer.storage.take() + "被消费了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费不需要更多数据了。");
            //不需要应该停下来了
            producer.canceled = true;
        }
        System.out.println("canceled:"+producer.canceled);
    }

}


class Producer implements Runnable {

    public volatile boolean canceled = false;

    BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }


    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 100000 && !canceled) {
                if (num % 100 == 0) {
                    storage.put(num);
                    System.out.println(num + "是100的倍数,被放到仓库中了。");
                }
                num++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者结束运行");
        }
    }
}

class Consumer {

    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNums() {
        if (Math.random() > 0.95) {
            return false;
        }
        return true;
    }
}