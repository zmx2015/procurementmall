package com.zmx.procurementmall.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.zmx.procurementmall.R;
import com.zmx.procurementmall.pojo.ClassifyPojo;

import java.util.List;

public class ClassifyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<ClassifyPojo> mTitle;
    public ClassifyAdapter(Context context,List<ClassifyPojo> title){
        mContext=context;
        mTitle=title;
        mLayoutInflater=LayoutInflater.from(context);
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public NormalViewHolder(View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.Tv);
        }
    }

    //在该方法中我们创建一个ViewHolder并返回，ViewHolder必须有一个带有View的构造函数，这个View就是我们Item的根布局，在这里我们使用自定义Item的布局；
    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalViewHolder(mLayoutInflater.inflate(R.layout.adapter_classify,parent,false));
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        NormalViewHolder viewholder = (NormalViewHolder) holder;
        viewholder.mTextView.setText(mTitle.get(position).getFsPName());
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                一定要刷新适配器 当条目发生改变这是必须的
                getListener.onClick(position);
                notifyDataSetChanged();

            }
        });

        //        如果下标和传回来的下标相等 那么确定是点击的条目 把背景设置一下颜色
        if (position == getmPosition()) {

            viewholder.mTextView.setTextColor(mContext.getColor(R.color.there));
        }else{
//            否则的话就全白色初始化背景
            viewholder.mTextView.setTextColor(Color.BLACK);
        }
    }


    //他用于告诉RecyclerView有多少子项，直接返回数据源的长度就行了
    @Override
    public int getItemCount() {
        return mTitle.size();
    }

    public interface GetListener {

        void onClick(int position);
    }

    private GetListener getListener;
    public void setGetListener(GetListener getListener) {
        this.getListener = getListener;
    }
    private  int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

}