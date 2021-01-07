package com.tc.shop.service.impl;

import com.tc.shop.dao.ShopSellerMapper;
import com.tc.shop.model.ShopSeller;
import com.tc.shop.service.ShopSellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ShopSellerServiceImpl implements ShopSellerService {
    @Autowired(required = false)
    private ShopSellerMapper mapper;

    @Override
    public ShopSeller getBySId(String sid) {
        Example example=new Example(ShopSeller.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("sid",sid);
        List<ShopSeller> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<ShopSeller> getByOaUserId(Integer oaUserId) {
        Example example=new Example(ShopSeller.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("oaUserId",oaUserId);
        List<ShopSeller> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }

        return null;
    }

    @Override
    public int insert(ShopSeller seller) {
        return mapper.insertSelective(seller);
    }

    @Override
    public ShopSeller getById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int modify(ShopSeller seller) {
        return mapper.updateByPrimaryKeySelective(seller);
    }
}

