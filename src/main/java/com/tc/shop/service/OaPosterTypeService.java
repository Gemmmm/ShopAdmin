package com.tc.shop.service;

import com.tc.shop.model.OaPosterType;

import java.util.List;

public interface OaPosterTypeService {
    OaPosterType getById(Integer typeId);

    List<OaPosterType> getByOaId(Integer oaId);

    int deleteById(Integer id);

    int insert(OaPosterType posterType);

    int modify(OaPosterType posterType);
}
