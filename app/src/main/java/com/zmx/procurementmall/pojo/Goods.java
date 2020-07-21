package com.zmx.procurementmall.pojo;

import java.util.Date;
import java.util.List;

public class Goods {

    private Integer goods_id;

    private String goodsName;

    private String goodsDetail;

    private Integer fsCId;

    private Double goodsWeight;

    private String goodsUnit;

    private Double goodsPrice;

    private Double goodsOriginalPrice;

    private Integer goodsSalesInitial;

    private Integer goodsSalesActual;

    private Integer goodsBasicStock;

    private Integer goodsState;

    private Long goodsCreateTime;

    private Long goodsUpdateTime;

    private List<GoodsImage> images;

    public List<GoodsImage> getImages() {
        return images;
    }

    public void setImages(List<GoodsImage> images) {
        this.images = images;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public Integer getFsCId() {
        return fsCId;
    }

    public void setFsCId(Integer fsCId) {
        this.fsCId = fsCId;
    }

    public Double getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(Double goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(Double goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    public Integer getGoodsSalesInitial() {
        return goodsSalesInitial;
    }

    public void setGoodsSalesInitial(Integer goodsSalesInitial) {
        this.goodsSalesInitial = goodsSalesInitial;
    }

    public Integer getGoodsSalesActual() {
        return goodsSalesActual;
    }

    public void setGoodsSalesActual(Integer goodsSalesActual) {
        this.goodsSalesActual = goodsSalesActual;
    }

    public Integer getGoodsBasicStock() {
        return goodsBasicStock;
    }

    public void setGoodsBasicStock(Integer goodsBasicStock) {
        this.goodsBasicStock = goodsBasicStock;
    }

    public Integer getGoodsState() {
        return goodsState;
    }

    public void setGoodsState(Integer goodsState) {
        this.goodsState = goodsState;
    }

    public Long getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(Long goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    public Long getGoodsUpdateTime() {
        return goodsUpdateTime;
    }

    public void setGoodsUpdateTime(Long goodsUpdateTime) {
        this.goodsUpdateTime = goodsUpdateTime;
    }
}