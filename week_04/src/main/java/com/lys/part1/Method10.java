package com.lys.part1;

import java.util.concurrent.Semaphore;

/**
 *semaphore 控制子线程先执行
 */
public class Method10 {
    private static Semaphore semaphore = new Semaphore(1);
    private volatile static int result = -1;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            System.out.println("子线程执行》》》》");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result = sum();
            semaphore.release();
        });
        thread.start();
        Thread.sleep(1000);
        semaphore.acquire();
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        semaphore.release();
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
