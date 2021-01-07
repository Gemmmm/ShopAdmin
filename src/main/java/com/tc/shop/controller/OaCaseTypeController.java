package com.tc.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaCase;
import com.tc.shop.model.OaCaseType;
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
import com.tc.shop.service.OaCaseService;
import com.tc.shop.service.OaCaseTypeService;

@Controller
@RequestMapping("/caseType")
public class OaCaseTypeController {
    @Autowired
    private HttpSession session;
    @Autowired
    private OaCaseTypeService ctService;
    @Autowired
    private OaCaseService caseService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allType(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        PageHelper.startPage(pageNum, pageSize);
        List<OaCaseType> caseTypes = ctService.getByOaId(oaId);
        if (caseTypes != null) {
            for (OaCaseType caseType : caseTypes) {
                map = new HashMap<String, Object>();
                map.put("id", caseType.getId());
                map.put("name", caseType.getName());
                Integer isShow = caseType.getIsShow();
                if (isShow == 1) {
                    map.put("isShow", "是");
                } else {
                    map.put("isShow", "否");
                }

                maps.add(map);
            }
            PageInfo<OaCaseType> page = new PageInfo<OaCaseType>(caseTypes);
            modelAndView.addObject("page", page);
        }
        modelAndView.addObject("caseTypes", maps);

        modelAndView.setViewName("caseTypes");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        List<OaCase> cases = caseService.getByOaidAndTypeId(oaId, id);
        if (cases != null && cases.size() > 0) {
            modelAndView.addObject("error", "给分类下有内容，无法删除");
            modelAndView.setViewName("redirect:/caseType/all");
            return modelAndView;
        }
        int count = ctService.deleteById(id);
        if (count < 0) {
            modelAndView.addObject("error", "删除错误");
        }
        modelAndView.setViewName("redirect:/caseType/all");
        return modelAndView;

    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        OaCaseType caseType = new OaCaseType();
        caseType.setIsShow(1);
        modelAndView.addObject("caseType", caseType);
        modelAndView.setViewName("caseTypesAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(OaCaseType caseType) {
        ModelAndView modelAndView = new ModelAndView();
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        caseType.setOaId(oaId);
        int flag = ctService.insert(caseType);
        if (flag < 0) {
            modelAndView.addObject("error", "添加错误");
        }
        modelAndView.setViewName("redirect:/caseType/all");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyPage(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaCaseType caseType = ctService.getById(id);
        modelAndView.addObject("caseType", caseType);
        modelAndView.setViewName("caseTypesModify");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(OaCaseType caseType, @PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        caseType.setId(id);
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        caseType.setOaId(oaId);
        Integer flag = ctService.modify(caseType);
        if (flag < 0) {
            modelAndView.addObject("error", "修改错误");
        }
        modelAndView.setViewName("redirect:/caseType/all");
        return modelAndView;
    }
}
