# 多线程开发
### 1实线多线程的方法有几种？

oracle官网里讲有两种：

1. 一种是继承thread的类；

2. 另一种是实现runnable接口；

   使用第二种方法更好，一般都是使用方法二，不考虑方法一：

   1. 建一个类实现接口可以多个实现，但继承只能继承一个类（java不能双继承）；
   2. 使用实现runnable接口的方法不用去new和销毁类，节约资源；
   3. 使用方法类与我们thread类应该解耦的，不应该与thread混为一谈。

   两种方法的区别：

   实现了runnable接口并传入thread类和继承thread类然后重写run方法在实现多线程上的本质是一样的，没有区别，都是最终调用stat()方法来新建线程。这两个方法最终区别是run()方法的来源上不一样：

   ```java
   @Override
   public void run() {
       if (target != null) {
           target.run();
       }
   }
   ```

   方法二最终是target.run();

   方法一是run()被重写。

`定时器、线程池还有lammda表达式不属于多线程实现方式,但可以实现多线程。`

### 2怎么才能启动多线程？

启动线程正确和错误方式：

正确方式为：`start()`

错误方式为：`run()`

start是通知jvm虚拟机启动新的线程，但通知虚拟机并不意味着立刻启动线程，虚拟机启动线程可能立即启动，也可能稍后启动，也可能等很久执行，所以执行线程序的顺序不是依据通知线程顺序决定的（start）。是而run则是一个方法，不能够启动新线程，源码同上，run()方法跟我们自己写的方法一样。

不能够重复执行start()方法：

```linux
java.lang.IllegalThreadStateException
```

1. 从源码上看，start第一次执行后状态就变了，不可能在原来状态，启动start时首先要检查 其状态，原码中

   ```java
   if (threadStatus != 0)
               throw new IllegalThreadStateException();
   ```

2. 然后加入线程组；

3. 最后使用start0启动线程。而start0方法不是java方法，它使用`native`修饰，使用c++实现，无法看源码的（一般不需要看）。

注意:

1. start方法被`synchronized`修饰，保证了线程的安全；
2. 凡jvm创建的main线程组和system线程组，并不会通过start启动

### 3如何正确停止线程？（难点）

## 原理

使用`interrupt`来通知，而不是强制。

### 需要停止线程几种情况：

1. 用户主动停止；
2. 服务突然要快速关闭；
3. 运行越时或者出错也会要主动停止线程。

`interrupt`不能直接中断停止线程，只能够通知线程，而最终断定中断停止线程只有线程本身，但这只是一个规范，`interrupt`没有中断和停止线程的权利。

## 如何正确停止

线程怎么会停止：

- run方法执行完毕后自动停止；
- 方法内部出现错误和异常，但错误没有被捕获；

这两种情况都会被停止，被停止的线程会进行资源回收。

### 线程在没有组塞情况下停线程的方法

1. 使用interrupt方法通知；
2. 同时run方法需要有检测`Thread.currentThread().isInterrupted()`。

只有通知，线程里没有检查不能中途进行停止。

 ### 在有线程阻塞方法有sleep时程序报错：

```linux
java.lang.InterruptedException: sleep interrupted
```

**果循环内部sleep抓取了InterruptedException异常的话，无法中止线程的**。

#### 开发过程中最佳实践

##### 优先选择：传递中断

在处理sleep阻塞时不能使用try/catch抓取处理异常，应该让它throw抛出去

##### 不想或者无法传递中断时：恢复中断

如果try/catch了中断，应该在catch中使用`Thread.currentThread().interrupt()`恢复中断，以便顶层可以感知到中断信号。

##### 不应该屏蔽中断——一定不能屏蔽中断

#### 响应中断的方法

- Object.wait()/Objectwait(Long)/Object(Long,int)
- Thread.sleep()/Thread.sleep(Long)/THread.sleep(Long,int)
- Thread.jion()/Thread.jion(long)/Thread.jion(Long,int)
- java.util.coucurrent.BlockingQueue.take()/put(E)
- java.util.coucurrent.locks.Lock.lockInterrupibly()

- java.util.coucurrent.CountDownLatch.await()
- java.util.coucurrent.CyclicBarrier.await()
- java.util.coucurrent.Exchaner.exechange(V)
- java.nio.channIs.InterruptibleChannel相关方法
- java.nio.channIs.Selector的相关方法

## 停止线程的错误方法

1. stop(),suspend()和resume()三种方法都是过时的方法，stop()会直接中断停止线程，但线程中的逻辑不能完全执行，后面两个不能去除moniter锁
2. volatile设置的booblean标记位，看似可行，但有些情况不能停止，由于线程长时间阻塞（这种比较长见，像生产者和消费者模式存在这样的情况，生产者速度高于消费者，长时间阻塞线程），这样没有办法及时唤醒，或者启远无法唤醒线程，而interrupt设计之初已经考虑到wait等长期阻塞作为一种特殊情况考虑在内，我们应该使用interupt方法停止线程了。

## 重要函数解析

## 常见面试问题

### 4线程的一生（6个状态）

new\runnable(可运行的)\blocked\waiting\timed waiting\Terminated

### 5thread和object类的重要方法详解

### 6线程的各个属性（四种）

### 7未捕获异常如何处理？

### 8多线程会导致的问题？

### 常见的面视问题



