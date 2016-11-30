package com.bw.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.activity.R;
import com.bw.bean.ShowBean;

import java.util.List;

/**
 * autour: 徐仕奇
 * action：PullRecyclerview的适配器，加载布局和数据，并注册长按监听的事件
 * date: 2016/11/24 0024 下午 3:43
 * update: 2016/11/24 0024
 */
public class MyPullRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener,View.OnLongClickListener {
    //上下文
    Context context;
    //数据的集合
    List<ShowBean.ResultBean.DataBean> list;
    //自定义内部接口的对象(点击)
    private OnItemClickListener mOnItemClickListener = null;
    //自定义内部接口的对象(长按点击)
    private OnItemLongClickListener mLongClickListener=null;
    /**
     * 点击接口回调
     */
    //实现条目点击事件用的方法
    //define（定义） interface（接口）
    public static interface OnItemClickListener {
        //两个参数可改（自定义）
        void onItemClick(View view , int position);
    }
    //提供的对外调用setOnItemClickListener的方法，参数是内部接口
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //点击方法（实现的是实现的接口的抽象方法）
    //点击的实现靠它，因为它是实现的点击方法
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //（内部点击接口）注意这里使用getTag方法获取数据
            //参数对应内部点击接口参数一致
            mOnItemClickListener.onItemClick(v,(Integer) v.getTag());
        }
    }

    /**
     * 长按点击接口回调
     */
    //长按监听
    @Override
    public boolean onLongClick(View v) {
        if(mLongClickListener != null){
            mLongClickListener.onItemLongClick(v, (Integer) v.getTag());
        }
        return true;
    }
    //长按接口
    public interface OnItemLongClickListener {
        void onItemLongClick(View view,int position);
    }
    //对外的调用长按点击的方法
    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.mLongClickListener = listener;
    }
    //布局填充器
    LayoutInflater minflater;
    public MyPullRecyclerAdapter(Context context, List<ShowBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
        minflater=LayoutInflater.from(context);
    }

    /**
     * 该方法中导入布局，实例化VIewHolder
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mView=minflater.inflate(R.layout.recycler_item,viewGroup,false);
        ItemViewHolder vh=new ItemViewHolder(mView);
        mView.setOnLongClickListener(this);
        mView.setOnClickListener(this);
        return vh;
    }

    /**
     * 绑定VIewHolder，加载数据
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ItemViewHolder){
            ((ItemViewHolder) viewHolder).mTextView.setText(list.get(i).getContent());
            //将数据保存在itemView的Tag中，以便点击时进行获取
            viewHolder.itemView.setTag(i);
        }
    }

    /**
     *
     * @return 返回的总条目
     */
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.recycler_item_tv);
        }
    }
}
