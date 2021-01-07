package com.tc.shop.service;

import com.tc.shop.model.OaVideoType;

import java.util.List;

public interface OaVideoTypeService {
    List<OaVideoType> getByOaId(Integer oaId);

    int deleteById(Integer id);

    int insert(OaVideoType videoType);

    OaVideoType getById(Integer id);

    int modify(OaVideoType videoType);
}
