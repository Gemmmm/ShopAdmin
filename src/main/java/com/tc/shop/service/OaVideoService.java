package com.tc.shop.service;

import com.tc.shop.model.OaVideo;

import java.util.List;

public interface OaVideoService {
    List<OaVideo> getByTypeId(Integer id);

    List<OaVideo> getByOaId(Integer oaId);

    List<OaVideo> getByOaIdAndTitleAndTypeId(Integer oaId, String videoTitle, Integer videoType);

    int deleteById(Integer id);

    int insert(OaVideo video);

    OaVideo getById(Integer id);

    int modifyById(OaVideo video);
}
