package com.zmx.procurementmall.okhttp;

public class UrlConfig {

//    private static String URL = "http://119.23.230.124:8080/zmx";
    private static String URL = "http://192.168.1.15:8080/zmx/";

    //商品和购物车
    public static String SELECT_CART_GOODS = URL+"goods/selectCartMessage";//查询购物车的全部商品
    public static String DELETE_CART_GOODS = URL+"goods/deleteGoodsCart";//删除购物车某个商品
    public static String SELECT_SELECT_GOODS = URL+"goods/selectGoods";//根据分类id查询全部商品
    public static String UPDATE_CART_GOODS_NUM = URL+"goods/updateGoodsNum";//更新购物车某个商品的数量
    public static String ADD_CART_GOODS = URL+"goods/addCartGoods";//添加商品到购物车


    public static String ADD_ORDER_INFO = URL+"order/addOrderInfo";//提交订单
    public static String SELECT_ALL_ORDER_INFO = URL+"order/selectAllOrderInfo";//提交订单


    public static String ADD_USER = URL+"user/addUser";//登录注册

    public static String SELECT_ALL_CATEGORY = URL+"category/selectAllCategory";//查询全部分类


}
