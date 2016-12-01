package com.example;

/**
 * 使用继承java.lang.Thread类的方式创建一个线程
 */
public class MyThreadExt extends Thread {
    /**
     * 重写（Override）run()方法 JVM会自动调用该方法
     */
    @Override
    public void run() {
        super.run();
        System.out.println("I'm running!------extends Thread");
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int b=(int) (Math.random()*100+1);
            System.out.println("zhuxiancheng="+b);
        }
    }
}
