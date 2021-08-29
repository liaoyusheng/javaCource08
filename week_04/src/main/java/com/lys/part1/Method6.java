package com.lys.part1;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch
 */
public class Method6 {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private volatile static int result = -1;
    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            System.out.println("子线程执行》》》》");
            result = sum();
            countDownLatch.countDown();
        });
        thread.start();
        countDownLatch.await();
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
