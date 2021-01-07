package com.tc.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaPoster;
import com.tc.shop.model.OaPosterType;
import com.tc.shop.model.OaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.service.OaPosterService;
import com.tc.shop.service.OaPosterTypeService;

@Controller
@RequestMapping("/posterType")
public class OaPosterTypeController {

    @Autowired
    private HttpSession session;
    @Autowired
    private OaPosterTypeService ptService;
    @Autowired
    private OaPosterService posterService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allPosterTypes(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        PageHelper.startPage(pageNum, pageSize);
        List<OaPosterType> posterTypes = ptService.getByOaId(oaId);
        if (posterTypes == null) {
            modelAndView.setViewName("posterTypes");
            return modelAndView;
        }
        for (OaPosterType posterType : posterTypes) {
            map = new HashMap<String, Object>();
            map.put("id", posterType.getId());
            map.put("name", posterType.getName());
            Integer isShow = posterType.getIsShow();
            if (isShow == 1) {
                map.put("isShow", "是");
            } else {
                map.put("isShow", "否");
            }
            maps.add(map);
        }
        modelAndView.addObject("posterTypes", maps);
        PageInfo<OaPosterType> page = new PageInfo<OaPosterType>(posterTypes);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("posterTypes");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("id:" + id);
        List<OaPoster> posters = posterService.getByTypeId(id);
        if (posters != null) {
            modelAndView.addObject("error", "给分类下有内容，无法删除");
            modelAndView.setViewName("redirect:/posterType/all");
            return modelAndView;
        }
        int count = ptService.deleteById(id);
        if (count < 0) {
            modelAndView.addObject("error", "删除错误");
        }
        modelAndView.setViewName("redirect:/posterType/all");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        OaPosterType posterType = new OaPosterType();
        posterType.setIsShow(1);
        modelAndView.addObject("posterType", posterType);
        modelAndView.setViewName("posterTypesAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(OaPosterType posterType) {
        ModelAndView modelAndView = new ModelAndView();
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        posterType.setOaId(oaId);
        int flag = ptService.insert(posterType);
        if (flag < 0) {
            modelAndView.addObject("error", "添加错误");
        }
        modelAndView.setViewName("redirect:/posterType/all");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyPage(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaPosterType posterType = ptService.getById(id);
        modelAndView.addObject("posterType", posterType);
        modelAndView.setViewName("posterTypesModify");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(
            OaPosterType posterType,
            @PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        posterType.setId(id);
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        posterType.setOaId(oaId);
        int flag = ptService.modify(posterType);
        if (flag < 0) {
            modelAndView.addObject("error", "修改错误");
        }
        modelAndView.setViewName("redirect:/posterType/all");
        return modelAndView;
    }

}
