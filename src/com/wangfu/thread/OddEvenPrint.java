package com.wangfu.thread;

/**
 * 两个线程交替打印奇偶数，0-100
 *
 * @Author: liutangchen
 * @Date: 2021/4/8 22:32
 */
public class OddEvenPrint {
    private static int count = 0;

    public static void main(String[] args) {
        Object lock = new Object();
        Work evenWork = new Work(lock);
        Work oddWork = new Work(lock);
        Thread evenThread = new Thread(evenWork);
        Thread oddThread = new Thread(oddWork);
        evenThread.start();
        oddThread.start();
        System.out.println("main");
    }

    static class Work implements Runnable {

        private Object lock;

        public Work(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (lock) {
                    System.out.println(count++);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    // 此处判断，是为了打印完了100个数字后，程序能够正常结束，否则程序将一直等待下去，耗费系统资源。
                    if (count <= 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
