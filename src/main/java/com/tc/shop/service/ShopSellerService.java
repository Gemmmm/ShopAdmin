package com.tc.shop.service;

import com.tc.shop.model.ShopSeller;

import java.util.List;

public interface ShopSellerService {

    ShopSeller getBySId(String sid);

    List<ShopSeller> getByOaUserId(Integer oaUserId);

    int insert(ShopSeller seller);

    ShopSeller getById(Integer id);

    int deleteById(Integer id);

    int modify(ShopSeller seller);
}
