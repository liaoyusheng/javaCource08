package com.lys.part1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * signal/signalAll
 */
public class Method5 {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private volatile static int result = -1;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {

            try {
                Thread.sleep(1000);
                lock.lock();
                System.out.println("子线程执行》》》》");
                result = sum();
                condition.signal();
//           condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        });
        thread.start();

        lock.lock();
        condition.await();
        lock.unlock();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");


    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
