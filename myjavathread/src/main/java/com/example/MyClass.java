package com.example;

public class MyClass {

    public static void main(String[] args) {
        MyThreadExt m1=new MyThreadExt();
        m1.start();
        MyRunnableImp m2=new MyRunnableImp();
        Thread t = new Thread(m2);
        t.start();
        for(int x=0;x<10;x++)
            System.out.println("main run:--------"+x);


        ThreadDemo demo=new ThreadDemo();

        Thread t1=new Thread(demo);
        t1.start();


        Thread t2=new Thread(demo);
        t2.start();


        Thread t3=new Thread(demo);
        t3.start();


        Thread t4=new Thread(demo);
        t4.start();
    }

}
