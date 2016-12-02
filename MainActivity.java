package com.bw.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Mypost();
        MyThread();
    }
    public void Mypost(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "nishihao", Toast.LENGTH_SHORT).show();
                //参数一：本线程，参数二：时间，作用：延迟操作发布到事件队列的UI 线程
                mhandler.postDelayed(this, 2000);
            }
        }).start();
    }
    public void MyThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "UI操作...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }
}
