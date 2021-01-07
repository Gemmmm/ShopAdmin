package com.tc.shop.service;

import com.tc.shop.model.OaPoster;

import java.util.List;

public interface OaPosterService {
    List<OaPoster> getByOaIdAndTitle(Integer oaId, String posterTitle);

    List<OaPoster> getByOaId(Integer oaId);

    int deleteById(Integer id);

    int insert(OaPoster poster);

    OaPoster getById(Integer id);

    int modifyById(OaPoster poster);

    List<OaPoster> getByTypeId(Integer id);
}
