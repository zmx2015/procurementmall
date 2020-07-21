package com.zmx.procurementmall.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 父分类
 */
public class ClassifyPojo implements Serializable {

    private Integer fsPId;

    private String fsPName;

    private Integer imageId;

    private Integer fsPSort;

    private Integer fsPState;

    private Long fsPCreateTime;

    private Long fsPUpdateTime;

    private List<FsCategory> categories;

    public List<FsCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<FsCategory> categories) {
        this.categories = categories;
    }

    public Integer getFsPId() {
        return fsPId;
    }

    public void setFsPId(Integer fsPId) {
        this.fsPId = fsPId;
    }

    public String getFsPName() {
        return fsPName;
    }

    public void setFsPName(String fsPName) {
        this.fsPName = fsPName;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public Integer getFsPSort() {
        return fsPSort;
    }

    public void setFsPSort(Integer fsPSort) {
        this.fsPSort = fsPSort;
    }

    public Integer getFsPState() {
        return fsPState;
    }

    public void setFsPState(Integer fsPState) {
        this.fsPState = fsPState;
    }

    public Long getFsPCreateTime() {
        return fsPCreateTime;
    }

    public void setFsPCreateTime(Long fsPCreateTime) {
        this.fsPCreateTime = fsPCreateTime;
    }

    public Long getFsPUpdateTime() {
        return fsPUpdateTime;
    }

    public void setFsPUpdateTime(Long fsPUpdateTime) {
        this.fsPUpdateTime = fsPUpdateTime;
    }
}

