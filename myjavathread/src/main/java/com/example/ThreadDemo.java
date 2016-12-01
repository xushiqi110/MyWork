package com.example;


public class ThreadDemo implements Runnable {

    int ticket = 100;

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            method();

        }
    }
   // synchronized线程中要同时执行完的任务
    private synchronized void method() {
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "ZhengZaiShouDi==="
                    + (ticket) + "===ZhangPiao!");
            ticket--;

        }
    }

}
