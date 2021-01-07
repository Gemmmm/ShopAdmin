package com.tc.shop.model;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

public class ShopSellerInfo {
    @Id
    private Integer shopId;

    private String sid;

    private String txImg;

    private String company;

    private String address;

    private String tel;

    private Integer number;

    private String companyType;

    private String sProvince;

    private String sCity;

    private String sCounty;

    private String tpeople;

    private String people;

    private String email;

    private String briefIntro;

    private String intro;

    private String shopLogPic;

    private String nickname;

    private String qq;

    private Integer isFine;

    private String businessLicense;

    private String legalPerson;

    private String companyCTime;

    private String operatingPeriod;

    private Integer isLong;

    private String chargePeople;

    private String chargePeoplePhone;

    private String zipCode;

    private String companyBank;

    private String legalIdent;

    private String chargeIdent;

    private String certificateNum;

    private String brandName;

    private String sellScope;

    private Integer isExam;

    private BigDecimal regCapital;

    private Integer isInline;

    private Integer visitorNum;

    private String visitorFlag;

    private String alww;

    private Integer sellerLevel;

    private String notice;

    private Integer invite;

    private String longitude;

    private String latitude;

    private String shopNo;

    private BigDecimal dadaMoney;

    private BigDecimal jxDadaMoney;

    private BigDecimal money;

    private BigDecimal hhrMoney;

    private String sqUserid;

    private Integer isOk;

    private String openBank;

    private String bankType;

    private String imgYyzz;

    private String imgSpltskz;

    private String imgYhk;

    private String imgOne;

    private String imgTwo;

    private String letter;

    private String charterAddress;

    private String idcardName;

    private String sex;

    private Date createTime;

    private String charterTime;

    private String bank;

    private String idcardBranch;

    private String idcardAddress;

    private String idcardPlace;

    private String idcardYear;

    private String idcardTime;

    private String charterName;

    private String person;

    private Integer isMail;

    private String wxSettlementBankCode;

    private String companyImg;

    private String couponImg;

    private String myQrCode;
    private String shopEdit;

    private String shopSignsEdit;

    private String reason;

    public String getShopEdit() {
        return shopEdit;
    }

    public void setShopEdit(String shopEdit) {
        this.shopEdit = shopEdit == null ? null : shopEdit.trim();
    }

    public String getShopSignsEdit() {
        return shopSignsEdit;
    }

    public void setShopSignsEdit(String shopSignsEdit) {
        this.shopSignsEdit = shopSignsEdit == null ? null : shopSignsEdit.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getTxImg() {
        return txImg;
    }

    public void setTxImg(String txImg) {
        this.txImg = txImg == null ? null : txImg.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType == null ? null : companyType.trim();
    }

    public String getsProvince() {
        return sProvince;
    }

    public void setsProvince(String sProvince) {
        this.sProvince = sProvince == null ? null : sProvince.trim();
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity == null ? null : sCity.trim();
    }

    public String getsCounty() {
        return sCounty;
    }

    public void setsCounty(String sCounty) {
        this.sCounty = sCounty == null ? null : sCounty.trim();
    }

    public String getTpeople() {
        return tpeople;
    }

    public void setTpeople(String tpeople) {
        this.tpeople = tpeople == null ? null : tpeople.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBriefIntro() {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro) {
        this.briefIntro = briefIntro == null ? null : briefIntro.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getShopLogPic() {
        return shopLogPic;
    }

    public void setShopLogPic(String shopLogPic) {
        this.shopLogPic = shopLogPic == null ? null : shopLogPic.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Integer getIsFine() {
        return isFine;
    }

    public void setIsFine(Integer isFine) {
        this.isFine = isFine;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getCompanyCTime() {
        return companyCTime;
    }

    public void setCompanyCTime(String companyCTime) {
        this.companyCTime = companyCTime == null ? null : companyCTime.trim();
    }

    public String getOperatingPeriod() {
        return operatingPeriod;
    }

    public void setOperatingPeriod(String operatingPeriod) {
        this.operatingPeriod = operatingPeriod == null ? null : operatingPeriod.trim();
    }

    public Integer getIsLong() {
        return isLong;
    }

    public void setIsLong(Integer isLong) {
        this.isLong = isLong;
    }

    public String getChargePeople() {
        return chargePeople;
    }

    public void setChargePeople(String chargePeople) {
        this.chargePeople = chargePeople == null ? null : chargePeople.trim();
    }

    public String getChargePeoplePhone() {
        return chargePeoplePhone;
    }

    public void setChargePeoplePhone(String chargePeoplePhone) {
        this.chargePeoplePhone = chargePeoplePhone == null ? null : chargePeoplePhone.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getCompanyBank() {
        return companyBank;
    }

    public void setCompanyBank(String companyBank) {
        this.companyBank = companyBank == null ? null : companyBank.trim();
    }

    public String getLegalIdent() {
        return legalIdent;
    }

    public void setLegalIdent(String legalIdent) {
        this.legalIdent = legalIdent == null ? null : legalIdent.trim();
    }

    public String getChargeIdent() {
        return chargeIdent;
    }

    public void setChargeIdent(String chargeIdent) {
        this.chargeIdent = chargeIdent == null ? null : chargeIdent.trim();
    }

    public String getCertificateNum() {
        return certificateNum;
    }

    public void setCertificateNum(String certificateNum) {
        this.certificateNum = certificateNum == null ? null : certificateNum.trim();
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public String getSellScope() {
        return sellScope;
    }

    public void setSellScope(String sellScope) {
        this.sellScope = sellScope == null ? null : sellScope.trim();
    }

    public Integer getIsExam() {
        return isExam;
    }

    public void setIsExam(Integer isExam) {
        this.isExam = isExam;
    }

    public BigDecimal getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }

    public Integer getIsInline() {
        return isInline;
    }

    public void setIsInline(Integer isInline) {
        this.isInline = isInline;
    }

    public Integer getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(Integer visitorNum) {
        this.visitorNum = visitorNum;
    }

    public String getVisitorFlag() {
        return visitorFlag;
    }

    public void setVisitorFlag(String visitorFlag) {
        this.visitorFlag = visitorFlag == null ? null : visitorFlag.trim();
    }

    public String getAlww() {
        return alww;
    }

    public void setAlww(String alww) {
        this.alww = alww == null ? null : alww.trim();
    }

    public Integer getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(Integer sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice == null ? null : notice.trim();
    }

    public Integer getInvite() {
        return invite;
    }

    public void setInvite(Integer invite) {
        this.invite = invite;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public BigDecimal getDadaMoney() {
        return dadaMoney;
    }

    public void setDadaMoney(BigDecimal dadaMoney) {
        this.dadaMoney = dadaMoney;
    }

    public BigDecimal getJxDadaMoney() {
        return jxDadaMoney;
    }

    public void setJxDadaMoney(BigDecimal jxDadaMoney) {
        this.jxDadaMoney = jxDadaMoney;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getHhrMoney() {
        return hhrMoney;
    }

    public void setHhrMoney(BigDecimal hhrMoney) {
        this.hhrMoney = hhrMoney;
    }

    public String getSqUserid() {
        return sqUserid;
    }

    public void setSqUserid(String sqUserid) {
        this.sqUserid = sqUserid == null ? null : sqUserid.trim();
    }

    public Integer getIsOk() {
        return isOk;
    }

    public void setIsOk(Integer isOk) {
        this.isOk = isOk;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank == null ? null : openBank.trim();
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    public String getImgYyzz() {
        return imgYyzz;
    }

    public void setImgYyzz(String imgYyzz) {
        this.imgYyzz = imgYyzz == null ? null : imgYyzz.trim();
    }

    public String getImgSpltskz() {
        return imgSpltskz;
    }

    public void setImgSpltskz(String imgSpltskz) {
        this.imgSpltskz = imgSpltskz == null ? null : imgSpltskz.trim();
    }

    public String getImgYhk() {
        return imgYhk;
    }

    public void setImgYhk(String imgYhk) {
        this.imgYhk = imgYhk == null ? null : imgYhk.trim();
    }

    public String getImgOne() {
        return imgOne;
    }

    public void setImgOne(String imgOne) {
        this.imgOne = imgOne == null ? null : imgOne.trim();
    }

    public String getImgTwo() {
        return imgTwo;
    }

    public void setImgTwo(String imgTwo) {
        this.imgTwo = imgTwo == null ? null : imgTwo.trim();
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter == null ? null : letter.trim();
    }

    public String getCharterAddress() {
        return charterAddress;
    }

    public void setCharterAddress(String charterAddress) {
        this.charterAddress = charterAddress == null ? null : charterAddress.trim();
    }

    public String getIdcardName() {
        return idcardName;
    }

    public void setIdcardName(String idcardName) {
        this.idcardName = idcardName == null ? null : idcardName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCharterTime() {
        return charterTime;
    }

    public void setCharterTime(String charterTime) {
        this.charterTime = charterTime == null ? null : charterTime.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getIdcardBranch() {
        return idcardBranch;
    }

    public void setIdcardBranch(String idcardBranch) {
        this.idcardBranch = idcardBranch == null ? null : idcardBranch.trim();
    }

    public String getIdcardAddress() {
        return idcardAddress;
    }

    public void setIdcardAddress(String idcardAddress) {
        this.idcardAddress = idcardAddress == null ? null : idcardAddress.trim();
    }

    public String getIdcardPlace() {
        return idcardPlace;
    }

    public void setIdcardPlace(String idcardPlace) {
        this.idcardPlace = idcardPlace == null ? null : idcardPlace.trim();
    }

    public String getIdcardYear() {
        return idcardYear;
    }

    public void setIdcardYear(String idcardYear) {
        this.idcardYear = idcardYear == null ? null : idcardYear.trim();
    }

    public String getIdcardTime() {
        return idcardTime;
    }

    public void setIdcardTime(String idcardTime) {
        this.idcardTime = idcardTime == null ? null : idcardTime.trim();
    }

    public String getCharterName() {
        return charterName;
    }

    public void setCharterName(String charterName) {
        this.charterName = charterName == null ? null : charterName.trim();
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person == null ? null : person.trim();
    }

    public Integer getIsMail() {
        return isMail;
    }

    public void setIsMail(Integer isMail) {
        this.isMail = isMail;
    }

    public String getWxSettlementBankCode() {
        return wxSettlementBankCode;
    }

    public void setWxSettlementBankCode(String wxSettlementBankCode) {
        this.wxSettlementBankCode = wxSettlementBankCode == null ? null : wxSettlementBankCode.trim();
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg == null ? null : companyImg.trim();
    }

    public String getCouponImg() {
        return couponImg;
    }

    public void setCouponImg(String couponImg) {
        this.couponImg = couponImg == null ? null : couponImg.trim();
    }

    public String getMyQrCode() {
        return myQrCode;
    }

    public void setMyQrCode(String myQrCode) {
        this.myQrCode = myQrCode == null ? null : myQrCode.trim();
    }
}