package com.tc.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaInfoType;
import com.tc.shop.model.OaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.tc.shop.service.OaInfoService;
import com.tc.shop.service.OaInfoTypeService;

@Controller
@RequestMapping(value = "/infoType")
public class OaInfoTypeController {
    @Autowired
    private HttpSession session;
    @Autowired
    private OaInfoTypeService infoTypeService;
    @Autowired
    private OaInfoService infoService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allInfoType(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "1") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();

        List<OaInfoType> infoTypes = infoTypeService.getByOaId(oaId);
        if (infoTypes != null) {
            for (OaInfoType it : infoTypes) {
                map = new HashMap<String, Object>();
                map.put("id", it.getId());
                map.put("name", it.getName());
                Integer isShow = it.getIsShow();
                if (isShow == 1)
                    map.put("isShow", "是");
                else
                    map.put("isShow", "否");
                map.put("icoImg", it.getIcoImg());
                map.put("color", it.getColor());
                map.put("keyword", it.getKeyword());
                map.put("pcColor", it.getPcColor());
                map.put("phoneImg", it.getPhoneImg());
                Integer count = infoService.getByTypeId(it.getId());
                if (count != null && count > 0) {
                    map.put("delFlag", "1");
                } else {
                    map.put("delFlag", "0");
                }

                maps.add(map);
            }
        }
        modelAndView.addObject("infoTypes", maps);
        PageInfo<OaInfoType> page = new PageInfo<OaInfoType>(infoTypes);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("infoTypes");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteInfoType(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        int flag = infoTypeService.deleteById(id);
        if (flag < 0) {
            modelAndView.addObject("error", "删除错误");
        }
        modelAndView.setViewName("redirect:/infoType/all");
        return modelAndView;

    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        OaInfoType oaInfoType = new OaInfoType();
        oaInfoType.setIsShow(1);
        modelAndView.addObject("infoType", oaInfoType);
        modelAndView.setViewName("infoTypesAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(OaInfoType infoType) {
        ModelAndView modelAndView = new ModelAndView();
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        infoType.setOaId(oaId);
        int flag = infoTypeService.insert(infoType);
        if (flag < 0) {
            modelAndView.addObject("error", "添加错误");
        }
        modelAndView.setViewName("redirect:/infoType/all");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyPage(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaInfoType infoType = infoTypeService.getById(id);
        modelAndView.addObject("infoType", infoType);
        modelAndView.setViewName("infoTypesModify");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(OaInfoType infoType, @PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        infoType.setId(id);
        Integer flag = infoTypeService.modify(infoType);
        System.out.println("infoTypeName::" + infoType.getName());
        if (flag < 0) {
            modelAndView.addObject("error", "修改错误");
        }
        modelAndView.setViewName("redirect:/infoType/all");
        return modelAndView;
    }

}
