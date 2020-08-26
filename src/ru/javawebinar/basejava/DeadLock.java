package ru.javawebinar.basejava;

public class DeadLock {
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);

    }

    static void deadLock(Object lock1, Object lock2) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (lock1) {
                        try {
                            System.out.println("take lock1");
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (lock2) {
                            System.out.println("take lock2");
                        }
                    }
                }
            }
        }).start();
    }
}
