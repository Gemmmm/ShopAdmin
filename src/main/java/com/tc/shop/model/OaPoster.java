package com.tc.shop.model;

import javax.persistence.Id;
import java.util.Date;

public class OaPoster {
    @Id
    private Integer id;

    private Integer oaId;

    private Integer typeId;

    private String title;

    private Integer isUsedNum;

    private Integer isCustomerNum;

    private String img;

    private String content;

    private Date createTime;

    private Date lastUpdateTime;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}