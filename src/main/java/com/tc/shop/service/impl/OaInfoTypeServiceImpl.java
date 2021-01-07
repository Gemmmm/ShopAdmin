package com.tc.shop.service.impl;

import com.tc.shop.dao.OaInfoTypeMapper;
import com.tc.shop.model.OaInfoType;
import com.tc.shop.service.OaInfoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaInfoTypeServiceImpl implements OaInfoTypeService {

    @Autowired(required = false)
    private OaInfoTypeMapper mapper;

    @Override
    public List<OaInfoType> getByOaId(Integer oaId) {
        Example example = new Example(OaInfoType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        List<OaInfoType> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public OaInfoType getById(Integer typeId) {

        return mapper.selectByPrimaryKey(typeId);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OaInfoType infoType) {
        return mapper.insertSelective(infoType);
    }

    @Override
    public int modify(OaInfoType infoType) {
        return mapper.updateByPrimaryKeySelective(infoType);
    }
}
