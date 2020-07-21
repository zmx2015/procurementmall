package com.zmx.procurementmall.fragment.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zmx.procurementmall.R;

/**
*时间：2020/7/20 1:30
*功能：待收货
*/
public class ForTheOrderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        return view;

    }

}