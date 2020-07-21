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
import com.zmx.procurementmall.pojo.GoodsCartMessage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsCartAdapter extends BaseAdapterRvList<GoodsCartAdapter.ViewHolder, GoodsCartMessage> {

    private Handler handler;
    public GoodsCartAdapter(Activity activity, Handler handler) {
        super(activity);
        this.handler = handler;
    }
    public GoodsCartAdapter(Activity activity) {
        super(activity);
    }

    @Override
    protected void onBindVH(GoodsCartAdapter.ViewHolder holder, int listPosition, GoodsCartMessage goodsCartMessage) {

        holder.goods_title.setText(goodsCartMessage.getGoodsName());
        Glide.with(mActivity).load(goodsCartMessage.getGoodsimage()).into(holder.goods_image);
        holder.goods_price.setText("￥" + goodsCartMessage.getGoodsPrice() + "元");
        holder.goods_unit.setText(goodsCartMessage.getGoodsUnit());
        holder.num.setText(goodsCartMessage.getgNum() + "");

        holder.add_image.setOnClickListener(v ->
                asul.setaddNumberListener(goodsCartMessage.getGoodsId(),goodsCartMessage.getgNum(),listPosition,1));

        holder.subtract_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (goodsCartMessage.getgNum() == 1){

                    Message mess = handler.obtainMessage();
                    mess.what = 4;
                    mess.obj = goodsCartMessage.getCid();
                    handler.sendMessage(mess);

                }else{

                    asul.setaddNumberListener(goodsCartMessage.getGoodsId(),goodsCartMessage.getgNum(),listPosition,0);

                }

            }
        });

    }

    @NonNull
    @Override
    protected GoodsCartAdapter.ViewHolder onCreateVH(ViewGroup parent, LayoutInflater inflater) {
        GoodsCartAdapter.ViewHolder holder = new GoodsCartAdapter.ViewHolder(inflater.inflate(R.layout.adapter_goods_cart, parent, false));
        return holder;
    }

    static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.goods_image)
        ImageView goods_image;
        @BindView(R.id.add_image)
        ImageView add_image;
        @BindView(R.id.subtract_image)
        ImageView subtract_image;
        @BindView(R.id.goods_title)
        TextView goods_title;
        @BindView(R.id.goods_price)
        TextView goods_price;
        @BindView(R.id.goods_unit)
        TextView goods_unit;
        @BindView(R.id.num)
        TextView num;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    //添加数量监听
    public interface AddSubtractNumberListener {

        void setaddNumberListener(Integer gid, Integer num,Integer position,Integer logo);

    }

    private AddSubtractNumberListener asul;
    public void setAddSubtractNumberListener(AddSubtractNumberListener asul){

        this.asul = asul;

    }

}
