package com.zmx.procurementmall.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.ImmersionBar;
import com.zmx.procurementmall.R;
import com.zmx.procurementmall.okhttp.OkHttp3ClientManager;
import com.zmx.procurementmall.okhttp.UrlConfig;
import com.zmx.procurementmall.view.activity.LoginActivity;
import com.zmx.procurementmall.view.activity.OrderManagementActivity;

import java.util.HashMap;
import java.util.Map;

public class FourFragment extends SimpleImmersionFragment {

    private ImageView head;
    private TextView all_order;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_four, container, false);

        head = view.findViewById(R.id.head);
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(FourFragment.this.getContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

        all_order = view.findViewById(R.id.all_order);
        all_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(FourFragment.this.getContext(), OrderManagementActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void initImmersionBar() {

        ImmersionBar.with(this).titleBarMarginTop(R.id.tool_bar).statusBarDarkFont(true).init();

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 1:
                    Log.e("数据","数据"+msg.obj.toString());
                    break;

            }

        }
    };

    //更新购物车某个商品数量
    public void updateGoodsNum(Integer uid){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);

        OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.SELECT_ALL_ORDER_INFO, params, handler, 1, 404);


    }

}