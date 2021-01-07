package com.tc.shop.service;

import com.tc.shop.model.OaInfo;
import com.tc.shop.model.OaInfoType;

import java.util.List;

public interface OaInfoService {
    List<OaInfo> getByOaIdAndTitleAndType(Integer oaId, String infoTitle, Integer infoType);

    List<OaInfo> getByOaId(Integer oaId);

    int deleteById(Integer id);

    Integer getByTypeId(Integer typeId);

    int insert(OaInfo info);

    OaInfo getById(Integer id);

    int modify(OaInfo info);
}
