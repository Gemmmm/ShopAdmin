package com.tc.shop.service.impl;

import com.tc.shop.dao.OaVideoMapper;
import com.tc.shop.model.OaVideo;
import com.tc.shop.service.OaVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaVideoServiceImpl implements OaVideoService {

    @Autowired(required = false)
    private OaVideoMapper mapper;

    @Override
    public List<OaVideo> getByTypeId(Integer id) {
        Example example=new Example(OaVideo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId",id);
        List<OaVideo> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }

        return null;
    }

    @Override
    public List<OaVideo> getByOaId(Integer oaId) {
        Example example=new Example(OaVideo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId",oaId);
        List<OaVideo> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<OaVideo> getByOaIdAndTitleAndTypeId(Integer oaId, String videoTitle, Integer videoType) {
        Example example=new Example(OaVideo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId",oaId);
        if(videoType!=null&&videoType!=0){
            criteria.andEqualTo("typeId",videoType);
        }
        if(videoTitle!=null&&!"".equals(videoTitle)){
            criteria.andEqualTo("title","%"+videoTitle+"%");
        }
        List<OaVideo> list = mapper.selectByExample(example);
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
    public int insert(OaVideo video) {
        return mapper.insertSelective(video);
    }

    @Override
    public OaVideo getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int modifyById(OaVideo video) {
        return mapper.updateByPrimaryKeySelective(video);
    }
}
