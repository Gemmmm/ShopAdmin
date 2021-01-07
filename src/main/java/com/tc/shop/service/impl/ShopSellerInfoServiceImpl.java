package com.tc.shop.service.impl;

import com.tc.shop.dao.ShopSellerInfoMapper;
import com.tc.shop.model.ShopSeller;
import com.tc.shop.model.ShopSellerInfo;
import com.tc.shop.service.ShopSellerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.util.List;

@Service
public class ShopSellerInfoServiceImpl implements ShopSellerInfoService {
    @Autowired(required = false)
    private ShopSellerInfoMapper mapper;

    @Override
    public List<ShopSellerInfo> getByCompany(String shopName) {
        Example example=new Example(ShopSellerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("company","%"+shopName+"%");
        List<ShopSellerInfo> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public ShopSellerInfo getBySId(String sid) {
        Example example=new Example(ShopSellerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sid",sid);
        List<ShopSellerInfo> list = mapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public int insert(ShopSellerInfo sellerInfo) {
        return mapper.insertSelective(sellerInfo);
    }

    @Override
    public int deleteById(Integer shopId) {
        return mapper.deleteByPrimaryKey(shopId);
    }

    @Override
    public int modifyBySid(ShopSellerInfo sellerInfo) {
        Example example=new Example(ShopSellerInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sid",sellerInfo.getSid());
        return mapper.updateByExample(sellerInfo,example);
    }
}
