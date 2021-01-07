package com.tc.shop.service.impl;

import com.tc.shop.dao.OaCaseTypeMapper;
import com.tc.shop.model.OaCaseType;
import com.tc.shop.service.OaCaseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaCaseTypeServiceImpl implements OaCaseTypeService {

    @Autowired(required = false)
    private OaCaseTypeMapper mapper;

    @Override
    public List<OaCaseType> getByOaId(Integer oaId) {
        Example example = new Example(OaCaseType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        List<OaCaseType> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public OaCaseType getById(Integer typeId) {
        Example example=new Example(OaCaseType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",typeId);
        List<OaCaseType> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return  list.get(0);
        }
        return null;

    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OaCaseType caseType) {
        return mapper.insertSelective(caseType);
    }

    @Override
    public int modify(OaCaseType caseType) {
        return mapper.updateByPrimaryKeySelective(caseType);
    }
}
