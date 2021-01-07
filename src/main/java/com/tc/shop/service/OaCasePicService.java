package com.tc.shop.service;

import com.tc.shop.model.OaCasePic;

import java.util.List;

public interface OaCasePicService {
    int insert(OaCasePic casePic);

    int deleteByCaseId(Integer caseId);

    List<OaCasePic> getByCaseId(Integer id);
}
