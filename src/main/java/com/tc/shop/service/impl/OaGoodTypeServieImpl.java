package com.tc.shop.service.impl;

import com.tc.shop.dao.OaGoodTypeMapper;
import com.tc.shop.model.OaGoodType;
import com.tc.shop.service.OaGoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaGoodTypeServieImpl implements OaGoodTypeService {

    @Autowired(required = false)
    private OaGoodTypeMapper mapper;

    @Override
    public OaGoodType getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OaGoodType goodType) {
        return mapper.insertSelective(goodType);
    }

    @Override
    public Integer modify(OaGoodType goodType) {
        return mapper.updateByPrimaryKeySelective(goodType);
    }

    @Override
    public List<OaGoodType> getByOaId(Integer oaId) {
        Example example = new Example(OaGoodType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        List<OaGoodType> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }
}
