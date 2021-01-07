package com.tc.shop.model;

import javax.persistence.Id;
import java.math.BigDecimal;

public class OaGood {
    @Id
    private Integer id;

    private Integer oaId;

    private Integer goodTypeId;

    private String goodName;

    private String goodImg;

    private BigDecimal goodPrice;

    private Integer lookNum;

    private Integer upNum;

    private Integer goodNumber;

    private String goodKeyword;

    private Integer payType;

    private String barcode;

    private String createTime;

    private String upTime;

    private String downTime;

    private Integer status;

    private Integer sort;

    private Integer goodsCatId;

    private Integer buyNum;

    private String sellerId;

    private Integer type;

    private Integer isHot;

    private Integer isClear;

    private Integer isNew;

    private BigDecimal marketPrice;

    private Integer windowIntro;

    private String customerServer;

    private String updatedAt;

    private Integer isTj;

    private Integer shopTypeId;

    private Integer sellerNumber;

    private Integer isWholesale;

    private Integer isEx;

    private Integer isIndex;

    private Integer isTimeBuy;

    private String isCold;

    private Integer isExchange;

    private String isProcess;

    private String isProcessTittle;

    private String isProcessType;

    private Integer isLimit;

    private Integer limitNumber;

    private Integer isShow;

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

    public Integer getGoodTypeId() {
        return goodTypeId;
    }

    public void setGoodTypeId(Integer goodTypeId) {
        this.goodTypeId = goodTypeId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName == null ? null : goodName.trim();
    }

    public String getGoodImg() {
        return goodImg;
    }

    public void setGoodImg(String goodImg) {
        this.goodImg = goodImg == null ? null : goodImg.trim();
    }

    public BigDecimal getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(BigDecimal goodPrice) {
        this.goodPrice = goodPrice;
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

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public String getGoodKeyword() {
        return goodKeyword;
    }

    public void setGoodKeyword(String goodKeyword) {
        this.goodKeyword = goodKeyword == null ? null : goodKeyword.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime == null ? null : upTime.trim();
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime == null ? null : downTime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getGoodsCatId() {
        return goodsCatId;
    }

    public void setGoodsCatId(Integer goodsCatId) {
        this.goodsCatId = goodsCatId;
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getIsClear() {
        return isClear;
    }

    public void setIsClear(Integer isClear) {
        this.isClear = isClear;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getWindowIntro() {
        return windowIntro;
    }

    public void setWindowIntro(Integer windowIntro) {
        this.windowIntro = windowIntro;
    }

    public String getCustomerServer() {
        return customerServer;
    }

    public void setCustomerServer(String customerServer) {
        this.customerServer = customerServer == null ? null : customerServer.trim();
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt == null ? null : updatedAt.trim();
    }

    public Integer getIsTj() {
        return isTj;
    }

    public void setIsTj(Integer isTj) {
        this.isTj = isTj;
    }

    public Integer getShopTypeId() {
        return shopTypeId;
    }

    public void setShopTypeId(Integer shopTypeId) {
        this.shopTypeId = shopTypeId;
    }

    public Integer getSellerNumber() {
        return sellerNumber;
    }

    public void setSellerNumber(Integer sellerNumber) {
        this.sellerNumber = sellerNumber;
    }

    public Integer getIsWholesale() {
        return isWholesale;
    }

    public void setIsWholesale(Integer isWholesale) {
        this.isWholesale = isWholesale;
    }

    public Integer getIsEx() {
        return isEx;
    }

    public void setIsEx(Integer isEx) {
        this.isEx = isEx;
    }

    public Integer getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(Integer isIndex) {
        this.isIndex = isIndex;
    }

    public Integer getIsTimeBuy() {
        return isTimeBuy;
    }

    public void setIsTimeBuy(Integer isTimeBuy) {
        this.isTimeBuy = isTimeBuy;
    }

    public String getIsCold() {
        return isCold;
    }

    public void setIsCold(String isCold) {
        this.isCold = isCold == null ? null : isCold.trim();
    }

    public Integer getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(Integer isExchange) {
        this.isExchange = isExchange;
    }

    public String getIsProcess() {
        return isProcess;
    }

    public void setIsProcess(String isProcess) {
        this.isProcess = isProcess == null ? null : isProcess.trim();
    }

    public String getIsProcessTittle() {
        return isProcessTittle;
    }

    public void setIsProcessTittle(String isProcessTittle) {
        this.isProcessTittle = isProcessTittle == null ? null : isProcessTittle.trim();
    }

    public String getIsProcessType() {
        return isProcessType;
    }

    public void setIsProcessType(String isProcessType) {
        this.isProcessType = isProcessType == null ? null : isProcessType.trim();
    }

    public Integer getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(Integer isLimit) {
        this.isLimit = isLimit;
    }

    public Integer getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(Integer limitNumber) {
        this.limitNumber = limitNumber;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}