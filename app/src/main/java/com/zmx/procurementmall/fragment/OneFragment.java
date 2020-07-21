package com.zmx.procurementmall.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.wang.adapters.interfaceabstract.OnItemClickListener;
import com.zmx.procurementmall.R;
import com.zmx.procurementmall.adapter.CategoryAdapter;
import com.zmx.procurementmall.adapter.ClassifyAdapter;
import com.zmx.procurementmall.adapter.GoodsItemAdapter;
import com.zmx.procurementmall.okhttp.OkHttp3ClientManager;
import com.zmx.procurementmall.okhttp.UrlConfig;
import com.zmx.procurementmall.pojo.ClassifyPojo;
import com.zmx.procurementmall.pojo.FsCategory;
import com.zmx.procurementmall.pojo.Goods;
import com.zmx.procurementmall.pojo.GoodsImage;
import com.zmx.procurementmall.view.util.CustomToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OneFragment extends SimpleImmersionFragment {

    private RecyclerView recycler_view,rv_rvliandong_Left,rv_rvliandong_Right;
    private ClassifyAdapter adapter;
    private List<ClassifyPojo> lists;

    private CategoryAdapter category_adapter;
    private List<FsCategory> fcs;

    private GoodsItemAdapter goodsItemAdapter;
    private List<Goods> goods;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);

        lists = new ArrayList<>();
        recycler_view = view.findViewById(R.id.recycler_view);
        LinearLayoutManager layout = new LinearLayoutManager(this.getContext());
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);//设置为横向排列
        recycler_view.setLayoutManager(layout);

        rv_rvliandong_Left = view.findViewById(R.id.rv_rvliandong_Left);
        fcs = new ArrayList<>();
        rv_rvliandong_Left.setLayoutManager(new LinearLayoutManager(this.getContext()));
        category_adapter = new CategoryAdapter(this.getActivity());
        rv_rvliandong_Left.setAdapter(category_adapter);
        category_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            protected void onItemClick(View view, int listPosition) {

                category_adapter.mCheckedPosition = listPosition;
                category_adapter.notifyDataSetChanged();
                Log.e("设置右边数据","设置右边数据");
                selectGoods(fcs.get(listPosition).getFsCId()+"");

            }
        });


        rv_rvliandong_Right = view.findViewById(R.id.rv_rvliandong_Right);
        rv_rvliandong_Right.setLayoutManager(new LinearLayoutManager(this.getContext()));
        goodsItemAdapter = new GoodsItemAdapter(this.getActivity(),handler);
        goods = new ArrayList<>();
        rv_rvliandong_Right.setAdapter(goodsItemAdapter);

        handler.sendEmptyMessage(1);

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

            Gson gson = new Gson();
            switch (msg.what){

                case 1:

                    OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.SELECT_ALL_CATEGORY, null, handler, 2, 404);

                    break;

                case 2:

                    JSONObject json = null;

                    try {
                        json = new JSONObject(msg.obj.toString());
                        //先判断有没有分类先
                        if (json.getString("status").equals("200")) {

                            //有分类先保存分类数据
                            JSONArray j_store = json.getJSONArray("data");
                            for (int i = 0; i < j_store.length(); i++) {

                                ClassifyPojo cp = new ClassifyPojo();

                                JSONObject js = j_store.getJSONObject(i);
                                cp.setFsPId(js.getInt("fsPId"));
                                cp.setFsPName(js.getString("fsPName"));

                                JSONArray ja = js.getJSONArray("categories");
                                List<FsCategory> list_fs = new ArrayList<>();
                                for (int j=0;j < ja.length();j++){

                                    FsCategory fsCategory = gson.fromJson(ja.getJSONObject(j).toString(), FsCategory.class);
                                    list_fs.add(fsCategory);

                                }
                                cp.setCategories(list_fs);
                                lists.add(cp);
                            }
                        }

                        adapter = new ClassifyAdapter(OneFragment.this.getActivity(),lists);
                        recycler_view.setAdapter(adapter);
                        adapter.setGetListener(position -> {

                            //                把点击的下标回传给适配器 确定下标
                            adapter.setmPosition(position);
                            adapter.notifyDataSetChanged();
                            fcs.clear();
                            for (FsCategory f:lists.get(position).getCategories()){

                                fcs.add(f);

                            }

                            category_adapter.setListAndNotifyDataSetChanged(fcs);//就是设置list然后刷新
                            if (fcs.size()>0){

                                category_adapter.mCheckedPosition = 0;
                                category_adapter.notifyDataSetChanged();
                                selectGoods(fcs.get(0).getFsCId()+"");
                            }

                        });

                        fcs.clear();
                        for (FsCategory f:lists.get(0).getCategories()){

                            fcs.add(f);

                        }
                        category_adapter.setListAndNotifyDataSetChanged(fcs);//就是设置list然后刷新
                        if (fcs.size()>0){

                            selectGoods(fcs.get(0).getFsCId()+"");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:

                    try {

                        goods.clear();
                        JSONObject object = new JSONObject(msg.obj.toString());

                        JSONArray array = object.getJSONArray("data");
                        for (int i=0;i<array.length();i++){

                            Goods g = gson.fromJson(array.getJSONObject(i).toString(),Goods.class);

                            JSONArray ja = array.getJSONObject(i).getJSONArray("images");
                            List<GoodsImage> gis = new ArrayList<>();
                            for (int j=0;j < ja.length();j++){

                                GoodsImage gi = gson.fromJson(ja.getJSONObject(j).toString(), GoodsImage.class);
                                gis.add(gi);

                            }
                            g.setImages(gis);
                            goods.add(g);
                        }
                        goodsItemAdapter.setListAndNotifyDataSetChanged(goods);//就是设置list然后刷新

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    break;

                case 4:

                    String gid = msg.obj.toString();
                    Log.e("商品id","商品id"+gid);
                    addGoods(gid);

                    break;

                case 5:

                    try {

                        JSONObject object = new JSONObject(msg.obj.toString());
                        CustomToast.getInstance().showToastCustom(OneFragment.this.getContext(), object.getString("message"), Gravity.BOTTOM);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;

            }

        }
    };

    /**
     * 根据分类id查询全部商品
     */
    public void selectGoods(String fs_c_id){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("fs_c_id", fs_c_id);

        OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.SELECT_SELECT_GOODS, params, handler, 3, 404);


    }


    //加入购物车
    public void addGoods(String gid){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gid", gid);
        params.put("uid", "1");
        params.put("gNum", "1");

        OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.ADD_CART_GOODS, params, handler, 5, 404);

    }


}