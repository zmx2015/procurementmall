package com.zmx.procurementmall.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.zmx.procurementmall.BaseActivity;
import com.zmx.procurementmall.R;
import com.zmx.procurementmall.okhttp.OkHttp3ClientManager;
import com.zmx.procurementmall.okhttp.UrlConfig;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.pwd_edt)
    EditText pwdEdt;
    @BindView(R.id.code)
    Button code;
    @BindView(R.id.login)
    Button login;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {

        setToolbar(R.id.tool_bar);
        ButterKnife.bind(this);

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

    @OnClick({R.id.code, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.code:
                break;
            case R.id.login:

                addUser(etAccount.getText().toString());

                break;
        }
    }



    public void addUser(String phone){

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("phone", phone);

        OkHttp3ClientManager.getInstance().NetworkRequestMode(UrlConfig.ADD_USER, params, handler, 1, 404);


    }

}
