package com.lys.part1;

/**
 * 通过join，等待子线程执行完毕
 */
public class Method3 {
    private volatile static int result = -1;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            System.out.println("子线程执行》》》》");
            result = sum();
        });
        thread.start();
        thread.join();

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
