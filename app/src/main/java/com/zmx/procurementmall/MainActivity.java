package com.zmx.procurementmall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;


import com.zmx.procurementmall.fragment.FourFragment;
import com.zmx.procurementmall.fragment.OneFragment;
import com.zmx.procurementmall.fragment.ThreeFragment;
import com.zmx.procurementmall.fragment.TwoFragment;
import com.zmx.procurementmall.pojo.Tab;
import com.zmx.procurementmall.view.util.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private FragmentTabHost mTabhost;
    private LayoutInflater inFlater;
    private List<Tab> mTabs = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initViews() {
        initTab();
    }


    private void initTab() {

        Tab home = new Tab("首页", R.drawable.selector_icon_home, OneFragment.class);
        Tab dynamic = new Tab("购物车", R.drawable.selector_icon_hot, TwoFragment.class);
//        Tab cart = new Tab("消息", R.drawable.selector_icon_cart, ThreeFragment.class);
        Tab mine = new Tab("我的", R.drawable.selector_icon_mine, FourFragment.class);

        mTabs.add(home);
        mTabs.add(dynamic);
//        mTabs.add(cart);
        mTabs.add(mine);

        inFlater = LayoutInflater.from(this);
        mTabhost = this.findViewById(R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (Tab tab : mTabs) {

            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(tab.getTitle());
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);

        }

        //去除分割线
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabhost.setCurrentTab(0);

    }


    private View buildIndicator(Tab tab) {

        View view = inFlater.inflate(R.layout.tab_indicator, null);
        ImageView img = view.findViewById(R.id.icon_tab);
        TextView text = view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText("" + tab.getTitle());
        return view;
    }

}
