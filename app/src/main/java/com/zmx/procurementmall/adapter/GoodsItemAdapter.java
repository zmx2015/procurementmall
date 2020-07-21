package com.zmx.procurementmall.adapter;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
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
import com.zmx.procurementmall.pojo.Goods;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsItemAdapter extends BaseAdapterRvList<GoodsItemAdapter.ViewHolder, Goods> {

    private Handler handle;

    public GoodsItemAdapter(Activity activity) {
        super(activity);
    }

    public GoodsItemAdapter(Activity activity, Handler handler) {
        super(activity);
        this.handle = handler;
    }

    @Override
    protected void onBindVH(ViewHolder holder, int listPosition, Goods goods) {

        holder.goods_title.setText(goods.getGoodsName());
        holder.goods_detail.setText(goods.getGoodsDetail());
        Glide.with(mActivity).load(goods.getImages().get(0).getImageUrl()).into(holder.goods_image);
        holder.goods_price.setText("￥"+goods.getGoodsPrice()+"元");
        holder.goods_unit.setText(goods.getGoodsUnit());
        holder.add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Message mess = handle.obtainMessage();
                mess.what = 4;
                mess.obj = goods.getGoods_id()+"";
                handle.sendMessage(mess);

            }
        });

    }

    @NonNull
    @Override
    protected ViewHolder onCreateVH(ViewGroup parent, LayoutInflater inflater) {
        GoodsItemAdapter.ViewHolder holder = new GoodsItemAdapter.ViewHolder(inflater.inflate(R.layout.adapter_goods_item, parent, false));
        return holder;
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.goods_image)
        ImageView goods_image;
        @BindView(R.id.add_image)
        ImageView add_image;
        @BindView(R.id.goods_title)
        TextView goods_title;
        @BindView(R.id.goods_detail)
        TextView goods_detail;
        @BindView(R.id.goods_price)
        TextView goods_price;
        @BindView(R.id.goods_unit)
        TextView goods_unit;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
