package com.djonce.banner;

/**
 * Created by wangj on 2016/6/24
 */
public abstract class JBannerModel {

    int id;

    String imgUrl; // banner图片url

    String desc; // 描述

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
