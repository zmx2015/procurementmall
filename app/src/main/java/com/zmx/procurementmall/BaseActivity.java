package com.zmx.procurementmall;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.gyf.immersionbar.ImmersionBar;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public abstract class BaseActivity  extends AppCompatActivity implements View.OnClickListener{

    protected Activity mActivity;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public SimpleDateFormat format_a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Toolbar toolbar;

    /**
     * 跳转到下一个activity
     **/
    protected static final int REQUEST_ACTIVITY = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base);
        mActivity = this;
        //找到资源文件的XML
        if (getLayoutId() != 0) {

            View vContent = LayoutInflater.from(mActivity).inflate(getLayoutId(), null);
            ((FrameLayout) findViewById(R.id.frame_content)).addView(vContent);

        }


        initViews();
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.fontScale != 1) {
            //非默认值
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }

    /**
     * 初始化Activity的常用变量 举例:
     * <b>mLayoutResID=页面XML资源ID 必须存在的</b>
     */
    protected abstract int getLayoutId();

    /**
     * 初始化视图
     **/
    protected abstract void initViews();

    /**
     * 通过Class跳转界面
     *
     * @param cls
     */
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     *
     * @param cls
     * @param bundle
     */
    protected void startActivity(Class<?> cls, Bundle bundle) {
        startActivity(cls, bundle, REQUEST_ACTIVITY);
    }

    /**
     * 通过Class跳转界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    protected void startActivity(Class<?> cls, Bundle bundle, int requestCode) {

        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    //获取状态栏的高度
//    public int getStatusBarHeight() {
//
//        Class<?> c = null;
//        Object obj = null;
//        Field field = null;
//        int x = 0, statusBarHeight = 0;
//        try {
//            c = Class.forName("com.android.internal.R$dimen");
//            obj = c.newInstance();
//            field = c.getField("status_bar_height");
//            x = Integer.parseInt(field.get(obj).toString());
//            statusBarHeight = getResources().getDimensionPixelSize(x);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        return statusBarHeight;
//    }
//
//    //设置一体化
//    public void setTitleColors(int id){
//
//        // 沉浸式状态栏
//        positionView = findViewById(id);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//
//            Window window = getWindow();
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//
//            int statusBarHeight = getStatusBarHeight();
//            ViewGroup.LayoutParams lp = positionView.getLayoutParams();
//            lp.height = statusBarHeight;
//            positionView.setLayoutParams(lp);
//
//        }
//
//    }
//
    public void setToolbar(int id){

        ImmersionBar.with(this).titleBar(id).keyboardEnable(true).init();
        toolbar = findViewById(id);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//关闭页面
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //获取某个时间后的时间
    public static String addDateMinut(String day, int hour){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }

    //获取某个时间几个小时后的时间
    public static Date addDateMinut(Date date, int hour) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        return date;

    }

    /**
     * 字符Base64加密
     * @param str
     * @return
     */
    public static String encodeToString(String str){
        try {
            return Base64.encodeToString(str.getBytes("UTF-8"), Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }


}