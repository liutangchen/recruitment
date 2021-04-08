package com.wangfu.thread;

/**
 * 两个线程交替打印 A1B2C3D4..
 *
 * @author Wang Guolong
 * @version 1.0
 * @date 2021/2/19 2:31 下午
 */
public class AlternatePrint {
    static class SynchronizedWork implements Runnable {
        private Object lock;
        private String str;

        public SynchronizedWork(Object lock, String str) {
            this.lock = lock;
            this.str = str;
        }

        @Override
        public void run() {
            char[] chars = str.toCharArray();
            for (char ch : chars) {
                synchronized (lock) {
                    System.out.println(ch);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        SynchronizedWork characterWork = new SynchronizedWork(lock, "ABCDEFGHI");
        SynchronizedWork numberWork = new SynchronizedWork(lock, "123456789");
        Thread characterThread = new Thread(characterWork);
        Thread numberThread = new Thread(numberWork);
        characterThread.start();
        numberThread.start();
        Thread.sleep(5000);
    }
}
