package com.tc.shop.service.impl;

import com.tc.shop.dao.OaUserMapper;
import com.tc.shop.model.OaUser;
import com.tc.shop.service.OaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OaUserServiceImpl implements OaUserService {
    @Autowired(required = false)
    private OaUserMapper mapper;

    @Override
    public OaUser findAdmin(String username, String password) {
        Example example=new Example(OaUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);
        List<OaUser> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int modify(OaUser loginAdmin) {
        return mapper.updateByPrimaryKeySelective(loginAdmin);
    }
}
