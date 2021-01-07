package com.tc.shop.service;

import com.tc.shop.model.ShopSeller;
import com.tc.shop.model.ShopSellerInfo;

import java.util.List;

public interface ShopSellerInfoService {
    List<ShopSellerInfo> getByCompany(String shopName);

    ShopSellerInfo getBySId(String sid);

    int insert(ShopSellerInfo sellerInfo);

    int deleteById(Integer shopId);

    int modifyBySid(ShopSellerInfo sellerInfo);
}
