package com.zmx.procurementmall.fragment.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zmx.procurementmall.R;
import com.zmx.procurementmall.adapter.OrderManagementAdapter;
import com.zmx.procurementmall.fragment.FourFragment;
import com.zmx.procurementmall.okhttp.OkHttp3ClientManager;
import com.zmx.procurementmall.okhttp.UrlConfig;
import com.zmx.procurementmall.pojo.OrderInfo;
import com.zmx.procurementmall.pojo.OrderProduct;
import com.zmx.procurementmall.view.activity.OrderManagementActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*时间：2020/7/20 1:27
*功能：全部订单
*/
public class AllOrdersFragment extends Fragment {

    private OrderManagementAdapter adapter;
    private List<OrderInfo> lists;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        lists = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new OrderManagementAdapter(this.getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(AllOrdersFragment.this.getContext()));
        recyclerView.setAdapter(adapter);
        selectAllOrderInfo(1);
        return view;

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what){

                case 1:

                    try {

                        JSONObject object = new JSONObject(msg.obj.toString());

                        JSONArray array = object.getJSONArray("data");
                        Gson gson = new Gson();
                        for (int i=0;i<array.length();i++){

                            JSONObject jsonObject = array.getJSONObject(i);

                            OrderInfo oi = new OrderInfo();
                            oi.setOrderNumber(jsonObject.getString("orderNumber"));
                            oi.setoUid(jsonObject.getInt("oUid"));
                            oi.setOrderTime(jsonObject.getLong("orderTime"));
                            oi.setoPayment(jsonObject.getInt("oPayment"));
                            if (!jsonObject.isNull("taskReward")) {
                                oi.setPaymentTime(jsonObject.getLong("paymentTime"));

                            }
                            if (!jsonObject.isNull("outTradeNo")) {
                                oi.setOutTradeNo(jsonObject.getString("outTradeNo"));

                            }
                            oi.setShipmentStatus(jsonObject.getInt("shipmentStatus"));
                            oi.setShippingAddress(jsonObject.getString("shippingAddress"));
                            oi.setGoodsRental(new BigDecimal(jsonObject.getString("actualPayment")));
                            oi.setOrderState(jsonObject.getInt("orderState"));

                            JSONArray info = array.getJSONObject(i).getJSONArray("lists");
                            List<OrderProduct> ops = new ArrayList<>();
                            for (int j = 0;j<info.length();j++){

                                JSONObject jsob = info.getJSONObject(j);

                                OrderProduct op = new OrderProduct();
                                op.setOpGoodsId(jsob.getInt("opGoodsId"));
                                op.setOpGoodsName(jsob.getString("opGoodsName"));
                                op.setOpGoodsNum(jsob.getInt("opGoodsNum"));
                                op.setOpGoodsPrice(new BigDecimal(jsob.getString("opGoodsPrice")));
                                op.setOpOrderNumber(jsob.getString("opOrderNumber"));
                                op.setImage_url(jsob.getString("image_url"));
                                op.setOp_goods_unit(jsob.getString("op_goods_unit"));

                                ops.add(op);
                            }
                            oi.setLists(ops);
                            lists.add(oi);

                        }

                        adapter.setListAndNotifyDataSetChanged(lists);


                    } catch (JSONException e) {

                        Log.e("fasf","fasdf"+e.toString());

                        e.printStackTrace();
                    }

                    break;

            }

        }
    };

    public void selectAllOrderInfo(Integer uid){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uid", uid);

        OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.SELECT_ALL_ORDER_INFO, params, handler, 1, 404);


    }

}