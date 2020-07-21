package com.zmx.procurementmall.pojo;

import java.math.BigDecimal;

public class OrderProduct {

    private String opOrderNumber;

    private String opGoodsName;

    private Integer opGoodsNum;

    private BigDecimal opGoodsPrice;

    private Integer opGoodsId;

    private String image_url;

    private String op_goods_unit;

    public String getOp_goods_unit() {
        return op_goods_unit;
    }

    public void setOp_goods_unit(String op_goods_unit) {
        this.op_goods_unit = op_goods_unit;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getOpOrderNumber() {
        return opOrderNumber;
    }

    public void setOpOrderNumber(String opOrderNumber) {
        this.opOrderNumber = opOrderNumber;
    }

    public String getOpGoodsName() {
        return opGoodsName;
    }

    public void setOpGoodsName(String opGoodsName) {
        this.opGoodsName = opGoodsName;
    }

    public Integer getOpGoodsNum() {
        return opGoodsNum;
    }

    public void setOpGoodsNum(Integer opGoodsNum) {
        this.opGoodsNum = opGoodsNum;
    }

    public BigDecimal getOpGoodsPrice() {
        return opGoodsPrice;
    }

    public void setOpGoodsPrice(BigDecimal opGoodsPrice) {
        this.opGoodsPrice = opGoodsPrice;
    }

    public Integer getOpGoodsId() {
        return opGoodsId;
    }

    public void setOpGoodsId(Integer opGoodsId) {
        this.opGoodsId = opGoodsId;
    }
}