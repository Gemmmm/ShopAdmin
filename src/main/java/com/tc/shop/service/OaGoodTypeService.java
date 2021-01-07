package com.tc.shop.service;

import java.util.List;


import com.tc.shop.model.OaGoodType;

public interface OaGoodTypeService {

	OaGoodType getById(Integer id);

	int deleteById(Integer id);

	int insert(OaGoodType goodType);

	Integer modify( OaGoodType goodType);

	List<OaGoodType> getByOaId(Integer oaId);

}
