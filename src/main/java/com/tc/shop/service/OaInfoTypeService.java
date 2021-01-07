package com.tc.shop.service;

import com.tc.shop.model.OaInfoType;

import java.util.List;

public interface OaInfoTypeService {
    List<OaInfoType> getByOaId(Integer oaId);

    OaInfoType getById(Integer typeId);

    int deleteById(Integer id);

    int insert(OaInfoType infoType);

    int modify(OaInfoType infoType);
}
