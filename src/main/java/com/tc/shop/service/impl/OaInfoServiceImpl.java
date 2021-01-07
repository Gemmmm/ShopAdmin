package com.tc.shop.service.impl;

import com.tc.shop.dao.OaInfoMapper;
import com.tc.shop.model.OaInfo;
import com.tc.shop.model.OaInfoType;
import com.tc.shop.service.OaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaInfoServiceImpl implements OaInfoService {

    @Autowired(required = false)
    private OaInfoMapper mapper;

    @Override
    public List<OaInfo> getByOaIdAndTitleAndType(Integer oaId, String infoTitle, Integer infoType) {
        Example example=new Example(OaInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId",oaId);
        criteria.andEqualTo("title",infoTitle);
        criteria.andEqualTo("typeId",infoType);
        List<OaInfo> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<OaInfo> getByOaId(Integer oaId) {
        Example example=new Example(OaInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId",oaId);
        List<OaInfo> list = mapper.selectByExample(example);
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
    public Integer getByTypeId(Integer typeId) {
        Example example=new Example(OaInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId",typeId);
        List<OaInfo> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.size();
        }
        return null;
    }

    @Override
    public int insert(OaInfo info) {
        return mapper.insertSelective(info);
    }

    @Override
    public OaInfo getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int modify(OaInfo info) {
        return mapper.updateByPrimaryKeySelective(info);
    }
}
