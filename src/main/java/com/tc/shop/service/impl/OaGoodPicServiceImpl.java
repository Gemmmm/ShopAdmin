package com.tc.shop.service.impl;

import com.tc.shop.dao.OaGoodPicMapper;
import com.tc.shop.model.OaGoodPic;
import com.tc.shop.service.OaGoodPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class OaGoodPicServiceImpl implements OaGoodPicService {
    @Autowired(required = false)
    private OaGoodPicMapper mapper;
    @Override
    public int insert(OaGoodPic goodPic) {
        return mapper.insertSelective(goodPic);
    }

    @Override
    public List<OaGoodPic> getByGoodId(Integer goodId) {
        Example example=new Example(OaGoodPic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodId",goodId);
        List<OaGoodPic> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public int deleteByGoodId(Integer id) {
        Example example=new Example(OaGoodPic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("goodId",id);
        return mapper.deleteByExample(example);
    }
}
