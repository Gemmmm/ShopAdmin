package com.tc.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaGood;
import com.tc.shop.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.model.OaGoodType;
import com.tc.shop.model.OaUser;
import com.tc.shop.service.OaGoodService;
import com.tc.shop.service.OaGoodTypeService;

@Controller
@RequestMapping("/goodType")
public class OaGoodTypeController {
    @Autowired
    private HttpSession session;
    @Autowired
    private OaGoodTypeService goodTypeService;
    @Autowired
    private OaGoodService goodService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allTypes(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        if(admin==null){
            return null;
        }
        // admin用户的sid
        Integer oaId = admin.getId();

        PageHelper.startPage(pageNum, pageSize);
        List<OaGoodType> goodTypeList = goodTypeService.getByOaId(oaId);
        PageInfo<OaGoodType> page = new PageInfo<>();
        if (goodTypeList != null) {
            page = new PageInfo<OaGoodType>(goodTypeList);
        }

        modelAndView.addObject("page", page);

        if (goodTypeList != null){
            for (OaGoodType gt : goodTypeList) {
                map = new HashMap<String, Object>();
                map.put("id", gt.getId());
                map.put("name", gt.getName());
                Integer isShow = gt.getIsShow();
                if (isShow == 1){
                    map.put("isShow", "是");
                }
                else{
                    map.put("isShow", "否");
                }
                map.put("icoImg", gt.getIcoImg());
                map.put("color", gt.getColor());
                map.put("keyword", gt.getKeyword());
                map.put("pcColor", gt.getPcColor());
                map.put("phoneImg", gt.getPhoneImg());

                List<OaGood> goods  = goodService.getByOaIdAndTypeId(oaId,gt.getId());
                if (goods != null) {
                    map.put("delFlag", 1);
                } else {
                    map.put("delFlag", 0);
                }

                maps.add(map);
            }
        }

        modelAndView.addObject("goodTypes", maps);


        modelAndView.setViewName("goodTypes");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();


        List<OaGood> goods = goodService.getByOaIdAndTypeId(oaId,id);
        if (goods != null) {
            modelAndView.addObject("error", "该分类下有商品，请先删除商品");
            modelAndView.setViewName("redirect:/goodType/all");
            return modelAndView;
        }
        int count = goodTypeService.deleteById(id);
        if (count < 0) {
            modelAndView.addObject("error", "删除错误");
        }
        modelAndView.setViewName("redirect:/goodType/all");
        return modelAndView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        OaGoodType goodType = new OaGoodType();
        goodType.setIsShow(1);
        modelAndView.addObject("goodType", goodType);
        modelAndView.setViewName("goodTypesAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(
            @RequestParam String name,
            @RequestParam Integer isShow

    ) {
        ModelAndView modelAndView = new ModelAndView();
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");

        Integer oaId = admin.getId();
        OaGoodType goodType=new OaGoodType();
        goodType.setOaId(oaId);
        if(name==null||"".equals(name.trim())){
            modelAndView.addObject("msg", "名称不为空");
            System.out.println("名称不为空");
            modelAndView.setViewName("goodTypesAdd");
            return modelAndView;
        }
        goodType.setName(name);
        goodType.setIsShow(isShow);
        int flag = goodTypeService.insert(goodType);
        if (flag < 0) {
            modelAndView.addObject("msg", "添加错误");
        }else{
            modelAndView.addObject("msg","添加成功");
        }
        modelAndView.setViewName("redirect:/goodType/all");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyPage(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaGoodType goodType = goodTypeService.getById(id);
        modelAndView.addObject("goodType", goodType);
        modelAndView.setViewName("goodTypesModify");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(OaGoodType goodType, @PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        goodType.setId(id);
        Integer flag = goodTypeService.modify(goodType);
        if (flag < 0) {
            modelAndView.addObject("error", "修改错误");
        }
        modelAndView.setViewName("redirect:/goodType/all");
        return modelAndView;
    }
}
