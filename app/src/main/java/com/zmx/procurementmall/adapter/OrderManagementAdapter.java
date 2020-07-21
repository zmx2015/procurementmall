package com.zmx.procurementmall.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wang.adapters.adapter.BaseAdapterRvList;
import com.wang.adapters.base.BaseViewHolder;
import com.zmx.procurementmall.R;
import com.zmx.procurementmall.pojo.GoodsCartMessage;
import com.zmx.procurementmall.pojo.OrderInfo;
import com.zmx.procurementmall.pojo.OrderProduct;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderManagementAdapter extends BaseAdapterRvList<OrderManagementAdapter.ViewHolder, OrderInfo> {

    private OrderProductAdapter adapter;
    private List<OrderProduct> lists;

    public OrderManagementAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected void onBindVH(ViewHolder holder, int listPosition, OrderInfo orderInfo) {

        adapter = new OrderProductAdapter(mActivity);
        lists = orderInfo.getLists();
        holder.reView.setLayoutManager(new LinearLayoutManager(mActivity));
        holder.reView.setAdapter(adapter);
        adapter.setListAndNotifyDataSetChanged(lists);

    }

    @NonNull
    @Override
    protected ViewHolder onCreateVH(ViewGroup parent, LayoutInflater inflater) {

        OrderManagementAdapter.ViewHolder holder = new OrderManagementAdapter.ViewHolder(inflater.inflate(R.layout.adapter_order_management, parent, false));
        return holder;

    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.state)
        TextView state;
        @BindView(R.id.total_money)
        TextView total_money;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.reView)
        RecyclerView reView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
