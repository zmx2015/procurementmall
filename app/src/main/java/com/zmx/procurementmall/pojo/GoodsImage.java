package com.zmx.procurementmall.pojo;

public class GoodsImage {
    private Integer imageId;

    private Integer goodsId;

    private String imageUrl;

    private String imageSort;

    private Integer imageLogo;

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageSort() {
        return imageSort;
    }

    public void setImageSort(String imageSort) {
        this.imageSort = imageSort;
    }

    public Integer getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(Integer imageLogo) {
        this.imageLogo = imageLogo;
    }
}