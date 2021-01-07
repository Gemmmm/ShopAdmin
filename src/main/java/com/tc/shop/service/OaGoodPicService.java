package com.tc.shop.service;

import java.util.List;

import com.tc.shop.model.OaGoodPic;

public interface OaGoodPicService {

	int insert(OaGoodPic goodPic);

	List<OaGoodPic> getByGoodId(Integer goodId);

	int deleteByGoodId(Integer id);

}
