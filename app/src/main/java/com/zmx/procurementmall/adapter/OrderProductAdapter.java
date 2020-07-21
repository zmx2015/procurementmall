package com.zmx.procurementmall.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.wang.adapters.adapter.BaseAdapterRvList;
import com.wang.adapters.base.BaseViewHolder;
import com.zmx.procurementmall.R;
import com.zmx.procurementmall.pojo.OrderProduct;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderProductAdapter extends BaseAdapterRvList<OrderProductAdapter.ViewHolder, OrderProduct> {

    public OrderProductAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected void onBindVH(ViewHolder holder, int listPosition, OrderProduct orderProduct) {

        Glide.with(mActivity).load(orderProduct.getImage_url()).into(holder.image);
        holder.name.setText(orderProduct.getOpGoodsName());
        holder.specifications.setText(orderProduct.getOp_goods_unit());
        holder.number.setText("x"+orderProduct.getOpGoodsNum());
        holder.price.setText("￥"+orderProduct.getOpGoodsPrice());

    }

    @NonNull
    @Override
    protected ViewHolder onCreateVH(ViewGroup parent, LayoutInflater inflater) {
        OrderProductAdapter.ViewHolder holder = new OrderProductAdapter.ViewHolder(inflater.inflate(R.layout.adapter_order_product, parent, false));
        return holder;
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.specifications)
        TextView specifications;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.number)
        TextView number;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
