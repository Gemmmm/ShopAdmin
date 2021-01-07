package com.tc.shop.service.impl;

import com.tc.shop.dao.OaCaseMapper;
import com.tc.shop.model.OaCase;
import com.tc.shop.model.OaCaseType;
import com.tc.shop.service.OaCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class OaCaseServiceImpl implements OaCaseService {


    @Autowired(required = false)
    private OaCaseMapper mapper;

    @Override
    public List<OaCase> getByOaId(Integer oaId) {
        Example example = new Example(OaCase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        List<OaCase> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }
        return null;
    }

    @Override
    public List<OaCase> getByOaIdAndTitleAndType(Integer oaId, String title, Integer typeId) {
        Example example = new Example(OaCase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        criteria.andLike("title", "%" + title + "%");
        criteria.andEqualTo("typeId", typeId);
        List<OaCase> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }

        return null;
    }

    @Override
    public Integer insert(OaCase oaCase) {
        return mapper.insertSelective(oaCase);
    }

    @Override
    public OaCase getByExample(OaCase cases) {
        Example example = new Example(OaCase.class);
        Example.Criteria criteria = example.createCriteria();
        Integer oaId = cases.getOaId();
        if (oaId != null) {
            criteria.andEqualTo("oaId", oaId);
        }
        Date createTime = cases.getCreateTime();
        if (createTime != null) {
            criteria.andEqualTo("createTime", createTime);
        }
        String img = cases.getImg();
        if (img != null) {
            criteria.andEqualTo("img", img);
        }
        String title = cases.getTitle();
        if (title != null) {
            criteria.andEqualTo("title", title);
        }
        Integer typeId = cases.getTypeId();
        if(typeId!=null){
            criteria.andEqualTo("typeId",typeId);
        }

        List<OaCase> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }


        return null;
    }

    @Override
    public OaCase getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int modify(OaCase cases) {
        return mapper.updateByPrimaryKeySelective(cases);
    }

    @Override
    public List<OaCase> getByOaidAndTypeId(Integer oaId,Integer typeId) {

        Example example = new Example(OaCase.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("oaId", oaId);
        criteria.andEqualTo("typeId", typeId);
        List<OaCase> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list;
        }

        return null;
    }
}
