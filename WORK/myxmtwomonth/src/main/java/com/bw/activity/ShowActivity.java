package com.bw.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.bw.adapter.MyPullRecyclerAdapter;
import com.bw.bean.ShowBean;
import com.bw.util.OkHttp;
import com.bw.view.PullBaseView;
import com.bw.view.PullRecyclerView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
/**
 * autour: 徐仕奇
 * action：列表展示数据，并注册了上拉加载下拉刷新
 * date: 2016/11/24 0024 下午 4:21
 * update: 2016/11/24 0024
 */
public class ShowActivity extends AppCompatActivity implements PullBaseView.OnHeaderRefreshListener,
        PullBaseView.OnFooterRefreshListener{
    private int page=1;
    private PullRecyclerView mPullRecycler;
    private MyPullRecyclerAdapter mAdapter;
    private List<ShowBean.ResultBean.DataBean> list=new ArrayList<>();
    private List<ShowBean.ResultBean.DataBean> listAll=new ArrayList<>();
    private Gson gson;
    private ShowBean msbean;
    //为了让网络请求下数据再适配数据
    private Message mes=new Message();
    //在网络请求方法中sendMessage只执行一次所以设置这个变量
    private int num=1;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                //get请求网络加载数据，并设置给pullRecyclerview的布局
              InitDataGet();
            }else if (msg.what==2){
                num++;
                setList();
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //初始化PullRecyclerView，设置布局管理器
        InitpullRecycler();

        //绑定监听的方法
        PullListener();
    }
    //把请求下来的数据加到list集合中
    private void setList(){
        list.addAll(listAll);
        //适配数据的方法
        refalshAdaspter();
    }
    /**
     * 绑定监听的方法：PullRecyclerview的监听
     */
    private void PullListener() {
        mPullRecycler.setOnHeaderRefreshListener(this);//设置下拉监听
        mPullRecycler.setOnFooterRefreshListener(this);//设置上拉监听
    }

    /**
     * 初始化PullRecyclerView，设置布局管理器
     */
    private void InitpullRecycler() {
        mPullRecycler= (PullRecyclerView) findViewById(R.id.pullrecyclerview);
        LinearLayoutManager mllayout=new LinearLayoutManager(this);
        mllayout.setOrientation(LinearLayoutManager.VERTICAL);
        mPullRecycler.setLayoutManager(mllayout);
        mes.what=1;
        mHandler.sendMessage(mes);
    }

    /**
     * Get请求网络加载数据，并设置给pullRecyclerview的布局
     */
    private void InitDataGet() {

        String url1="http://japi.juhe.cn/joke/content/list.from?key= 874ed931559ba07aade103eee279bb37 &page="
                +page+"&pagesize=10&sort=asc&time=1418745237";
        OkHttp.getAsync(url1, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) { }
            @Override
            public void requestSuccess(String result) throws Exception {
                if (gson==null) {
                    gson = new Gson();
                }
                msbean=gson.fromJson(result, ShowBean.class);
                listAll=msbean.getResult().getData();
                //在第一次执行过后再handler中num++后，以后就不执行它了
                if (num==1) {
                    mes.what = 2;
                    mHandler.sendMessage(mes);
                }
            }
        });
        //******************以上是getAsync异步get请求******************//
    }

    private void refalshAdaspter() {
        //为了避免adapter多次实例化，占用内存
        if(mAdapter==null){
            mAdapter=new MyPullRecyclerAdapter(ShowActivity.this,list);
            mPullRecycler.setAdapter(mAdapter);
            //为adapter绑定监听
            setAdapterClickListener();
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 为adapter绑定监听
     */
    public void setAdapterClickListener(){
        //为adapter注册长按监听
        mAdapter.setOnItemLongClickListener(new MyPullRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int postion) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ShowActivity.this);  //先得到构造器
                builder.setTitle("提示"); //设置标题
                builder.setMessage("是否确认删除?"); //设置内容
                builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); //关闭dialog
                        list.remove(postion);
                        mAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //参数都设置完成了，创建并显示出来
                builder.create().show();
            }
        });
        //为adapter注册点击监听
//        mAdapter.setOnItemClickListener(new MyPullRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int postion) {
//                String bean = list.get(postion).getContent();
//                if(bean.equals(null)!=true){
//                    Toast.makeText(ShowActivity.this, "Click "+bean, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }
    /**
     * 上拉加载的接口实现方法
     * @param view
     */
    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // mDatas.add("TEXT更多");
                //page页增加
                page++;
                //调用get网络请求方法，加载新数据
                InitDataGet();
                //把新数据添加到list集合中（list相当一请求下来的数据的大集合）
                list.addAll(listAll);
                //刷新适配器
                refalshAdaspter();
                //脚部的加载动画
                mPullRecycler.onFooterRefreshComplete();
            }
        }, 2000);
    }

    /**
     * 下拉刷新实现的接口方法（头部刷新）
     * @param view
     */
    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //  mDatas.add(0, "TEXT新增");
                //page页增加
                page++;
                //清除list中的数据
                list.clear();
                //添加刷新后的数据
                list.addAll(listAll);
                //刷新适配器
                refalshAdaspter();
                //这个方法是recyclerView的头部动画
                mPullRecycler.onHeaderRefreshComplete();
            }
        }, 3000);
    }

}
