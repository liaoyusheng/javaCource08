package com.lys.part1;

/**
 * 主线程睡眠一段时间
 */
public class Method1 {
    private volatile static int result = -1;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            System.out.println("子线程执行》》》》");
            result = sum();
        }).start();
        // 确保  拿到result 并输出
        Thread.sleep(1000);//主线程休眠足够长时间
        System.out.println("异步计算结果为：" + result);
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
