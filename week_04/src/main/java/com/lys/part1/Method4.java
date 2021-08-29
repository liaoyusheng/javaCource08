package com.lys.part1;

/**
 * wait()、notify()/notifyAll()
 */
public class Method4 {
    private static final Object obj = new Object();
    private volatile static int result = -1;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);//确保主线程先执行wait方法
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程执行》》》》");
            result = sum();
            synchronized (obj) {
                obj.notify();
//                obj.notifyAll();
            }

        });
        thread.start();

        synchronized (obj) {
            obj.wait();
        }
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
