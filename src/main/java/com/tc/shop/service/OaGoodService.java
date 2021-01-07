package com.tc.shop.service;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import com.tc.shop.model.OaGood;

public interface OaGoodService {

	List<OaGood> getByOaIdAndTitle(Integer oaId, String goodName);

	List<OaGood> getByOaId(Integer oaId);

	int insert( OaGood good);

	int deleteById(Integer id);

	OaGood getById(Integer id);

	int modify(OaGood good);

	List<OaGood> getByOaIdAndTitleAndType(Integer oaId, String name, Integer type);

	List<OaGood> getByOaIdAndTypeId(Integer oaId,Integer typeId);


	OaGood getByExample(OaGood good);

}
