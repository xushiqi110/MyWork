package com.example;

/**
 * 通过实现Runnable接口创建一个线程
 */
public class MyRunnableImp implements Runnable {
    public void run() {
        System.out.println("I'm running!---------implements Runnable");
    }
}
