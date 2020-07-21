package com.zmx.procurementmall.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.zmx.procurementmall.R;
import com.zmx.procurementmall.adapter.GoodsCartAdapter;
import com.zmx.procurementmall.okhttp.OkHttp3ClientManager;
import com.zmx.procurementmall.okhttp.UrlConfig;
import com.zmx.procurementmall.pojo.GoodsCartMessage;
import com.zmx.procurementmall.view.util.CustomToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoFragment extends SimpleImmersionFragment implements GoodsCartAdapter.AddSubtractNumberListener {

    private TextView total_price;
    private Button submit;

    private ImageView empty_image;

    private GoodsCartAdapter adapter;
    private RecyclerView re_view;
    private List<GoodsCartMessage> lists;

    private BigDecimal totalPrice;//总价
    private int totalNumber=0;//总数

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        lists = new ArrayList<>();
        adapter = new GoodsCartAdapter(this.getActivity(),handler);
        adapter.setAddSubtractNumberListener(this);
        re_view = view.findViewById(R.id.re_view);
        re_view.setLayoutManager(new LinearLayoutManager(this.getContext()));
        re_view.setAdapter(adapter);

        empty_image = view.findViewById(R.id.empty_image);

        total_price = view.findViewById(R.id.total_price);
        submit = view.findViewById(R.id.submit);
        submit.setOnClickListener(v -> {

            if (lists.size()>0){

                pop_up();

            }else {

                CustomToast.getInstance().showToastCustom(TwoFragment.this.getContext(), "购物车空空如也", Gravity.BOTTOM);

            }

        });

        selectCartMessage();

        return view;

    }

    @Override
    public void initImmersionBar() {

        ImmersionBar.with(this).titleBarMarginTop(R.id.tool_bar).statusBarDarkFont(true).init();

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {

            totalNumber = 0;
            selectCartMessage();

        }


    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            Gson gson = new Gson();

            switch (msg.what){

                case 1:

                    try {

                        lists.clear();
                        JSONObject object = new JSONObject(msg.obj.toString());

                        JSONArray array = object.getJSONArray("data");

                        double tp = 0;

                        for (int i=0;i<array.length();i++){

                            GoodsCartMessage gcm = gson.fromJson(array.getJSONObject(i).toString(),GoodsCartMessage.class);
                            lists.add(gcm);

                            tp =tp+ gcm.getgNum()*gcm.getGoodsPrice();
                            totalNumber = totalNumber+gcm.getgNum();
                        }

                        adapter.setListAndNotifyDataSetChanged(lists);//就是设置list然后刷新

                        if (lists.size() > 0){
                            empty_image.setVisibility(View.GONE);
                        }else{
                            empty_image.setVisibility(View.VISIBLE);
                        }

                        totalPrice = new BigDecimal(tp);
                        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
                        total_price.setText( Html.fromHtml("合计：<font color='#FF0000'>"+totalPrice+"</font>"));
                        if (totalNumber == 0){

                            submit.setText("结算");
                        }else{

                            submit.setText("结算（"+totalNumber+"）");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;

                case 2:

                    try {

                        JSONObject object = new JSONObject(msg.obj.toString());

                        if (object.getInt("status") == 200){

                            lists.get(position).setgNum(num);

                            if (logo == 1){

                                totalPrice = totalPrice.add(new BigDecimal(lists.get(position).getGoodsPrice()));
                                totalNumber = totalNumber+1;
                            }else{

                                totalPrice = totalPrice.subtract(new BigDecimal(lists.get(position).getGoodsPrice()));
                                totalNumber = totalNumber-1;

                            }

                            totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
                            total_price.setText( Html.fromHtml("合计：<font color='#FF0000'>"+totalPrice+"</font>"));
                            submit.setText("结算（"+totalNumber+"）");
                            adapter.notifyDataSetChanged();

                        }else{

                            CustomToast.getInstance().showToastCustom(TwoFragment.this.getContext(), object.getString("message"), Gravity.BOTTOM);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;

                case 3:

                    try {

                        JSONObject object = new JSONObject(msg.obj.toString());
                        CustomToast.getInstance().showToastCustom(TwoFragment.this.getContext(), object.getString("message"), Gravity.BOTTOM);
                        totalNumber = 0;
                        selectCartMessage();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;

                case 4:

                    String cid = msg.obj.toString();
                    AlertDialog.Builder builder = new AlertDialog.Builder(TwoFragment.this.getContext());
                    builder.setTitle("是否移除该商品？");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            deleteGoodsCart(cid);
                            dialog.dismiss();

                        }
                    });
                    builder.show();


                    break;

                case 5:

                    try {

                        JSONObject object = new JSONObject(msg.obj.toString());

                        CustomToast.getInstance().showToastCustom(TwoFragment.this.getContext(), object.getString("message"), Gravity.BOTTOM);
                        if (object.getInt("status") == 200){

                            totalNumber = 0;
                            selectCartMessage();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;

            }

        }
    };

    //查询购物车信息
    public void selectCartMessage(){
        OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.SELECT_CART_GOODS, null, handler, 1, 404);

    }

    /**
     * 根据购物车id删除某个商品
     * @param cid
     * @return
     */
    public void deleteGoodsCart(String cid){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("cid", cid);
        OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.DELETE_CART_GOODS, params, handler, 5, 404);

    }

    //更新购物车某个商品数量
    public void updateGoodsNum(int gid,int num){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gid", gid);
        params.put("num", num);

        OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.UPDATE_CART_GOODS_NUM, params, handler, 2, 404);


    }

    private Integer position,num,logo;
    @Override
    public void setaddNumberListener(Integer gid, Integer num,Integer position,Integer logo) {
        this.position = position;
        this.logo = logo;
        if (logo == 1){

            this.num = num+1;
            updateGoodsNum(gid,(num+1));

        }else{

            this.num = num-1;
            updateGoodsNum(gid,(num-1));

        }
    }


    //弹出支付界面
    private Dialog modify_dialogs;//弹出框
    private Integer pay = 0;//1为微信，2为支付宝

    public void pop_up() {

        View view;//选择性别的view

        modify_dialogs = new Dialog(this.getActivity(), R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        view = LayoutInflater.from(this.getActivity()).inflate(R.layout.dialog_pay_choose, null);

        //将布局设置给Dialog
        modify_dialogs.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = modify_dialogs.getWindow();

        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        //        lp.y = 20;//设置Dialog距离底部的距离

        //// 以下这两句是为了保证按钮可以水平满屏
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        modify_dialogs.onWindowAttributesChanged(lp);
        //       将属性设置给窗体
        modify_dialogs.setCanceledOnTouchOutside(false);
        modify_dialogs.show();//显示对话框

        TextView money = view.findViewById(R.id.money);
        money.setText("￥"+totalPrice);

        ImageView wx_image,zfb_image;
        wx_image = view.findViewById(R.id.wx_image);
        zfb_image = view.findViewById(R.id.zfb_image);
        RelativeLayout wx_layout,zfb_layout;

        zfb_layout = view.findViewById(R.id.zfb_layout);
        zfb_layout.setOnClickListener(v -> {
            pay = 1;
            wx_image.setVisibility(View.GONE);
            zfb_image.setVisibility(View.VISIBLE);
        });
        wx_layout = view.findViewById(R.id.wx_layout);
        wx_layout.setOnClickListener(v -> {
            pay = 2;
            wx_image.setVisibility(View.VISIBLE);
            zfb_image.setVisibility(View.GONE);
        });

        ImageView close = view.findViewById(R.id.close);
        close.setOnClickListener(v -> modify_dialogs.dismiss());



        Button button = view.findViewById(R.id.pay);
        button.setOnClickListener(v -> {

            if(pay == 0){

                CustomToast.getInstance().showToastCustom(TwoFragment.this.getContext(), "请选择支付方式", Gravity.BOTTOM);

            }else{

                //先判断点击了哪种支付类型
                if(pay == 1){

                    OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.ADD_ORDER_INFO, null, handler, 3, 404);

                }else {

                    OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.ADD_ORDER_INFO, null, handler, 3, 404);

                }

                modify_dialogs.dismiss();
            }
        });

    }


}
