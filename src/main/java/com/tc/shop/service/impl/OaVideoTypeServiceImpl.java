package com.tc.shop.service.impl;

import com.tc.shop.dao.OaVideoTypeMapper;
import com.tc.shop.model.OaVideo;
import com.tc.shop.model.OaVideoType;
import com.tc.shop.service.OaVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaVideoTypeServiceImpl implements OaVideoTypeService {

    @Autowired(required = false)
    private OaVideoTypeMapper mapper;

    @Override
    public List<OaVideoType> getByOaId(Integer oaId) {
        Example example=new Example(OaVideoType.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId",oaId);
        List<OaVideoType> list = mapper.selectByExample(example);
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
    public int insert(OaVideoType videoType) {
        return mapper.insertSelective(videoType);
    }

    @Override
    public OaVideoType getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int modify(OaVideoType videoType) {
        return mapper.updateByPrimaryKeySelective(videoType);
    }
}
