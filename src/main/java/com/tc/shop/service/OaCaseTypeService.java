package com.tc.shop.service;

import com.tc.shop.model.OaCaseType;

import java.util.List;

public interface OaCaseTypeService {
    List<OaCaseType> getByOaId(Integer oaId);

    OaCaseType getById(Integer typeId);

    int deleteById(Integer id);

    int insert(OaCaseType caseType);

    int modify(OaCaseType caseType);
}
