package com.tc.shop.model;

import javax.persistence.Id;

public class OaCasePic {
    @Id
    private Integer id;

    private Integer caseId;

    private String casePic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCasePic() {
        return casePic;
    }

    public void setCasePic(String casePic) {
        this.casePic = casePic == null ? null : casePic.trim();
    }
}