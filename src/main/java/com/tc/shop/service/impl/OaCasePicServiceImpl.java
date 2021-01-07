package com.tc.shop.service.impl;

import com.tc.shop.dao.OaCasePicMapper;
import com.tc.shop.model.OaCasePic;
import com.tc.shop.service.OaCasePicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaCasePicServiceImpl implements OaCasePicService {

    @Autowired(required = false)
    private OaCasePicMapper mapper;

    @Override
    public int insert(OaCasePic casePic) {
        return mapper.insertSelective(casePic);
    }

    @Override
    public int deleteByCaseId(Integer caseId) {
        Example example = new Example(OaCasePic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("caseId", caseId);
        return mapper.deleteByExample(example);
    }

    @Override
    public List<OaCasePic> getByCaseId(Integer caseId) {
        Example example = new Example(OaCasePic.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("caseId", caseId);
        List<OaCasePic> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }

        return null;
    }
}
