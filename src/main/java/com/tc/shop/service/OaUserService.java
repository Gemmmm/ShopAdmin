package com.tc.shop.service;

import com.tc.shop.model.OaUser;

public interface OaUserService {

    OaUser findAdmin(String username, String password);

    int modify(OaUser loginAdmin);
}
