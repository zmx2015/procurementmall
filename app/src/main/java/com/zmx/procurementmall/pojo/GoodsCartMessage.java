package com.zmx.procurementmall.pojo;

public class GoodsCartMessage {

    private Integer cid;
    private Integer gNum; //数量
    private Integer goodsId;
    private String goodsName; //标题
    private Double goodsPrice;//价格
    private String goodsUnit;//规格
    private String goodsimage;//商品主图

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getgNum() {
        return gNum;
    }

    public void setgNum(Integer gNum) {
        this.gNum = gNum;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsimage() {
        return goodsimage;
    }

    public void setGoodsimage(String goodsimage) {
        this.goodsimage = goodsimage;
    }
}
