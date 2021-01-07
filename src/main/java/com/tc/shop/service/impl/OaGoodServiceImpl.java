package com.tc.shop.service.impl;

import com.tc.shop.dao.OaGoodMapper;
import com.tc.shop.model.OaGood;
import com.tc.shop.service.OaGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaGoodServiceImpl implements OaGoodService {
    @Autowired(required = false)
    private OaGoodMapper mapper;

    @Override
    public List<OaGood> getByOaIdAndTitle(Integer oaId, String goodName) {
        Example example = new Example(OaGood.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        criteria.andLike("goodName", "%" + goodName + "%");
        List<OaGood> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<OaGood> getByOaId(Integer oaId) {
        Example example = new Example(OaGood.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        List<OaGood> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public int insert(OaGood good) {
        return mapper.insertSelective(good);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public OaGood getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int modify(OaGood good) {
        return mapper.updateByPrimaryKeySelective(good);
    }

    @Override
    public List<OaGood> getByOaIdAndTitleAndType(Integer oaId, String name, Integer type) {
        Example example = new Example(OaGood.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        if(type!=null){
            criteria.andEqualTo("goodTypeId", type);
        }
        if(name!=null){
            criteria.andLike("goodName","%"+name+"%");
        }
        List<OaGood> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }

        return null;
    }

    @Override
    public List<OaGood> getByOaIdAndTypeId(Integer oaId,Integer typeId) {
        Example example = new Example(OaGood.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        if(typeId!=null){
            criteria.andEqualTo("goodTypeId", typeId);
        }
        List<OaGood> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }


    @Override
    public OaGood getByExample(OaGood good) {
        Example example = new Example(OaGood.class);
        Example.Criteria criteria = example.createCriteria();
        if(good.getGoodName()!=null) {
            criteria.andEqualTo("goodName",good.getGoodName());
        }
        if(good.getGoodPrice()!=null) {
            criteria.andEqualTo("goodPrice",good.getGoodPrice());
        }
        if(good.getGoodTypeId()!=null) {
            criteria.andEqualTo("goodTypeId",good.getGoodTypeId());
        }

        List<OaGood> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0) {
            return list.get(0);
        }

            return null;
    }

}
