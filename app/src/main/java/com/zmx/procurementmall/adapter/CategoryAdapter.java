package com.zmx.procurementmall.adapter;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.adapters.adapter.BaseAdapterRvList;
import com.wang.adapters.base.BaseViewHolder;
import com.zmx.procurementmall.pojo.FsCategory;

public class CategoryAdapter extends BaseAdapterRvList<BaseViewHolder, FsCategory> {


    public int mCheckedPosition = 0;

    public CategoryAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected void onBindVH(BaseViewHolder holder, int listPosition, FsCategory fc) {

        TextView tv = (TextView) holder.itemView;
        tv.setText(fc.getFsCName());
        tv.setBackgroundColor(mCheckedPosition == listPosition ? 0xffeeeeee : 0xffffffff);//选中灰色，不选择白色

    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateVH(ViewGroup parent, LayoutInflater inflater) {
        TextView tv = new AppCompatTextView(mActivity);
        tv.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv.setPadding(0, 40, 0, 40);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(15);
        return new BaseViewHolder(tv);

    }

}
