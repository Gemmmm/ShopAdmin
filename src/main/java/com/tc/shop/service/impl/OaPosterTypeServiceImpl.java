package com.tc.shop.service.impl;

import com.tc.shop.dao.OaPosterTypeMapper;
import com.tc.shop.model.OaPosterType;
import com.tc.shop.service.OaPosterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaPosterTypeServiceImpl implements OaPosterTypeService {

    @Autowired(required = false)
    private OaPosterTypeMapper mapper;

    @Override
    public OaPosterType getById(Integer typeId) {
        return mapper.selectByPrimaryKey(typeId);
    }

    @Override
    public List<OaPosterType> getByOaId(Integer oaId) {
        Example example=new Example(OaPosterType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId",oaId);
        List<OaPosterType> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }

        return null;
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OaPosterType posterType) {
        return mapper.insertSelective(posterType);
    }

    @Override
    public int modify(OaPosterType posterType) {
        return mapper.updateByPrimaryKeySelective(posterType);
    }
}
