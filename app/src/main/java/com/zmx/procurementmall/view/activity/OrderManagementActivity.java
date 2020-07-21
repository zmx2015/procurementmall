package com.zmx.procurementmall.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.zmx.procurementmall.BaseActivity;
import com.zmx.procurementmall.R;
import com.zmx.procurementmall.fragment.order.AllOrdersFragment;
import com.zmx.procurementmall.fragment.order.ForTheOrderFragment;
import com.zmx.procurementmall.fragment.order.ToBePaidOrderFragment;
import com.zmx.procurementmall.fragment.order.ToSendTheOrderFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderManagementActivity extends BaseActivity{


    private Integer logo = 0;

    static final int NUM_ITEMS = 4;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private String[] strings = new String[]{"全部","待付款","待发货","待收货"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_management;
    }

    @Override
    protected void initViews() {

        setToolbar(R.id.tool_bar);

        AllOrdersFragment aof = new AllOrdersFragment();
        ForTheOrderFragment ftf = new ForTheOrderFragment();
        ToBePaidOrderFragment tpof = new ToBePaidOrderFragment();
        ToSendTheOrderFragment tstof = new ToSendTheOrderFragment();

        fragmentList.add(aof);
        fragmentList.add(tpof);
        fragmentList.add(tstof);
        fragmentList.add(ftf);

        TabLayout tab_layout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.viewPager);
        OrderManagementActivity.MyAdapter fragmentAdater = new OrderManagementActivity.MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdater);
        tab_layout.setupWithViewPager(viewPager);
        tab_layout.getTabAt(logo).select();//默认选中


    }


    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return strings[position];
        }
    }


}
