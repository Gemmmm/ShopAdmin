package com.tc.shop.model;

import javax.persistence.Id;
import java.util.Date;

public class OaInfo {
    @Id
    private Integer id;

    private Integer oaId;

    private Integer typeId;

    private Integer sort;

    private String title;

    private String img;

    private Integer lookNum;

    private Integer upNum;

    private Integer isUsedNum;

    private Integer isCustomerNum;

    private Integer isShow;

    private Date createTime;

    private String isMustReport;

    private Date lastUpdateTime;

    private String shopGoods;

    private String shopCases;

    private String content;

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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getLookNum() {
        return lookNum;
    }

    public void setLookNum(Integer lookNum) {
        this.lookNum = lookNum;
    }

    public Integer getUpNum() {
        return upNum;
    }

    public void setUpNum(Integer upNum) {
        this.upNum = upNum;
    }

    public Integer getIsUsedNum() {
        return isUsedNum;
    }

    public void setIsUsedNum(Integer isUsedNum) {
        this.isUsedNum = isUsedNum;
    }

    public Integer getIsCustomerNum() {
        return isCustomerNum;
    }

    public void setIsCustomerNum(Integer isCustomerNum) {
        this.isCustomerNum = isCustomerNum;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIsMustReport() {
        return isMustReport;
    }

    public void setIsMustReport(String isMustReport) {
        this.isMustReport = isMustReport == null ? null : isMustReport.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getShopGoods() {
        return shopGoods;
    }

    public void setShopGoods(String shopGoods) {
        this.shopGoods = shopGoods == null ? null : shopGoods.trim();
    }

    public String getShopCases() {
        return shopCases;
    }

    public void setShopCases(String shopCases) {
        this.shopCases = shopCases == null ? null : shopCases.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}