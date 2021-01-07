package com.tc.shop.model;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class ShopSeller {
    @Id
    private Integer id;

    private String sid;

    private Integer oaUserId;

    private String sellername;

    private String role;

    private String name;

    private String tximg;

    private String phone;

    private String password;

    private String token;

    private String appid;

    private String secret;

    private Date createTime;

    private String createIp;

    private BigDecimal money;

    private Integer goodNumber;

    private Integer isAcc;

    private Integer status;

    private Integer isPf;

    private String mchId;

    private String keyword;

    private String intoKey;

    private BigDecimal frozenMoney;

    private String wxopenid;

    private Boolean isSysPs;

    private Boolean isReturn;

    private String position;

    private String wxnum;

    private String cardPattern;

    private String cardBgimg;

    private String intro;

    private String email;

    private String veriryCode;

    private Date codeValidTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public Integer getOaUserId() {
        return oaUserId;
    }

    public void setOaUserId(Integer oaUserId) {
        this.oaUserId = oaUserId;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername == null ? null : sellername.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTximg() {
        return tximg;
    }

    public void setTximg(String tximg) {
        this.tximg = tximg == null ? null : tximg.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getGoodNumber() {
        return goodNumber;
    }

    public void setGoodNumber(Integer goodNumber) {
        this.goodNumber = goodNumber;
    }

    public Integer getIsAcc() {
        return isAcc;
    }

    public void setIsAcc(Integer isAcc) {
        this.isAcc = isAcc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsPf() {
        return isPf;
    }

    public void setIsPf(Integer isPf) {
        this.isPf = isPf;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getIntoKey() {
        return intoKey;
    }

    public void setIntoKey(String intoKey) {
        this.intoKey = intoKey == null ? null : intoKey.trim();
    }

    public BigDecimal getFrozenMoney() {
        return frozenMoney;
    }

    public void setFrozenMoney(BigDecimal frozenMoney) {
        this.frozenMoney = frozenMoney;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid == null ? null : wxopenid.trim();
    }

    public Boolean getIsSysPs() {
        return isSysPs;
    }

    public void setIsSysPs(Boolean isSysPs) {
        this.isSysPs = isSysPs;
    }

    public Boolean getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Boolean isReturn) {
        this.isReturn = isReturn;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getWxnum() {
        return wxnum;
    }

    public void setWxnum(String wxnum) {
        this.wxnum = wxnum == null ? null : wxnum.trim();
    }

    public String getCardPattern() {
        return cardPattern;
    }

    public void setCardPattern(String cardPattern) {
        this.cardPattern = cardPattern == null ? null : cardPattern.trim();
    }

    public String getCardBgimg() {
        return cardBgimg;
    }

    public void setCardBgimg(String cardBgimg) {
        this.cardBgimg = cardBgimg == null ? null : cardBgimg.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getVeriryCode() {
        return veriryCode;
    }

    public void setVeriryCode(String veriryCode) {
        this.veriryCode = veriryCode == null ? null : veriryCode.trim();
    }

    public Date getCodeValidTime() {
        return codeValidTime;
    }

    public void setCodeValidTime(Date codeValidTime) {
        this.codeValidTime = codeValidTime;
    }
}