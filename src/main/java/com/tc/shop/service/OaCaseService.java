package com.tc.shop.service;

import com.tc.shop.model.OaCase;

import java.util.List;

public interface OaCaseService {
    List<OaCase> getByOaId(Integer oaId);

    List<OaCase> getByOaIdAndTitleAndType(Integer oaId, String title, Integer typeId);

    Integer insert(OaCase oaCase);

    OaCase getByExample(OaCase cases);

    OaCase getById(Integer id);

    int deleteById(Integer id);

    int modify(OaCase cases);


    List<OaCase> getByOaidAndTypeId(Integer oaId, Integer id);
}
