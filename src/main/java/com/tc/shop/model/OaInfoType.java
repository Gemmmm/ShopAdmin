package com.tc.shop.model;

import javax.persistence.Id;

public class OaInfoType {
    @Id
    private Integer id;

    private Integer oaId;

    private String name;

    private Integer isShow;

    private Integer sorting;

    private Integer typeId;

    private String icoImg;

    private String color;

    private String keyword;

    private String pcColor;

    private String phoneImg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOaId() {
        return oaId;
    }

    public void setOaId(Integer oaId) {
        this.oaId = oaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Integer getSorting() {
        return sorting;
    }

    public void setSorting(Integer sorting) {
        this.sorting = sorting;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getIcoImg() {
        return icoImg;
    }

    public void setIcoImg(String icoImg) {
        this.icoImg = icoImg == null ? null : icoImg.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getPcColor() {
        return pcColor;
    }

    public void setPcColor(String pcColor) {
        this.pcColor = pcColor == null ? null : pcColor.trim();
    }

    public String getPhoneImg() {
        return phoneImg;
    }

    public void setPhoneImg(String phoneImg) {
        this.phoneImg = phoneImg == null ? null : phoneImg.trim();
    }
}