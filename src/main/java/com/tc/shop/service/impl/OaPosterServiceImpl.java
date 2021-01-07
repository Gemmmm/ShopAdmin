package com.tc.shop.service.impl;

import com.tc.shop.dao.OaPosterMapper;
import com.tc.shop.model.OaPoster;
import com.tc.shop.service.OaPosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaPosterServiceImpl implements OaPosterService {

    @Autowired(required = false)
    private OaPosterMapper mapper;

    @Override
    public List<OaPoster> getByOaIdAndTitle(Integer oaId, String posterTitle) {
        Example example = new Example(OaPoster.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        criteria.andLike("title", "%" + posterTitle + "%");
        List<OaPoster> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }

        return null;
    }

    @Override
    public List<OaPoster> getByOaId(Integer oaId) {
        Example example = new Example(OaPoster.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        List<OaPoster> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }

        return null;
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OaPoster poster) {
        return mapper.insertSelective(poster);
    }

    @Override
    public OaPoster getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int modifyById(OaPoster poster) {
        return mapper.updateByPrimaryKeySelective(poster);
    }

    @Override
    public List<OaPoster> getByTypeId(Integer id) {
        Example example = new Example(OaPoster.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId", id);
        List<OaPoster> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }return null;
    }
}
