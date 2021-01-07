package com.tc.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaUser;
import com.tc.shop.model.OaVideo;
import com.tc.shop.model.OaVideoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.service.OaVideoService;
import com.tc.shop.service.OaVideoTypeService;

@Controller
@RequestMapping("/videoType")
public class OaVideoTypeController {
	@Autowired
	private HttpSession session;
	@Autowired
	private OaVideoTypeService vtService;
	@Autowired
	private OaVideoService videoService;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public ModelAndView allVideoType(
			@RequestParam( required = false, defaultValue = "1") Integer pageNum,
			@RequestParam( required = false, defaultValue = "10") Integer pageSize) {
		ModelAndView modelAndView=new ModelAndView();
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		// session中的企业信息
		OaUser admin = (OaUser) session.getAttribute("admin");
		// admin用户的sid
		Integer oaId = admin.getId();
		PageHelper.startPage(pageNum, pageSize);
		List<OaVideoType> videoTypes=vtService.getByOaId(oaId);
		if(videoTypes!=null) {
			for (OaVideoType videoType : videoTypes) {
				map=new HashMap<String, Object>();
				map.put("id", videoType.getId());
				map.put("name", videoType.getName());
				Integer isShow = videoType.getIsShow();
				if(isShow==1) {
					map.put("isShow", "是");
				}else {
					map.put("isShow", "否");
				}
				maps.add(map);
			}
			PageInfo<OaVideoType> page=new PageInfo<OaVideoType>(videoTypes);
			modelAndView.addObject("page", page);
			
		}
		modelAndView.addObject("videoTypes", maps);
		modelAndView.setViewName("videoTypes");
		return modelAndView;
		
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		List<OaVideo> videos = videoService.getByTypeId(id);
		if (videos != null) {
			modelAndView.addObject("error", "给分类下有内容，无法删除");
			modelAndView.setViewName("redirect:/videoType/all");
			return modelAndView;
		}
		int count = vtService.deleteById(id);
		if (count < 0) {
			modelAndView.addObject("error", "删除错误");
		}
		modelAndView.setViewName("redirect:/videoType/all");
		return modelAndView;
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addPage() {
		ModelAndView modelAndView = new ModelAndView();
		OaVideoType videoType = new OaVideoType();
		videoType.setIsShow(1);
		modelAndView.addObject("videoType", videoType);
		modelAndView.setViewName("videoTypesAdd");
		return modelAndView;
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addForm( OaVideoType videoType) {
		ModelAndView modelAndView = new ModelAndView();
		// session中的企业信息
		OaUser admin = (OaUser) session.getAttribute("admin");
		// admin用户的sid
		Integer oaId = admin.getId();
		videoType.setOaId(oaId);
		int flag = vtService.insert(videoType);
		if (flag < 0) {
			modelAndView.addObject("error", "添加错误");
		}
		modelAndView.setViewName("redirect:/videoType/all");
		return modelAndView;
	}
	
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
	public ModelAndView modifyPage(@PathVariable(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		OaVideoType videoType = vtService.getById(id);
		modelAndView.addObject("videoType", videoType);
		modelAndView.setViewName("videoTypesModify");
		return modelAndView;
	}
	
	@RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
	public ModelAndView modifyForm( OaVideoType videoType,
			@PathVariable(value = "id", required = true) Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		videoType.setId(id);
		// session中的企业信息
		OaUser admin = (OaUser) session.getAttribute("admin");
		// admin用户的sid
		Integer oaId = admin.getId();
		videoType.setOaId(oaId);
		int flag = vtService.modify(videoType);
		if (flag < 0) {
			modelAndView.addObject("error", "修改错误");
		}
		modelAndView.setViewName("redirect:/videoType/all");
		return modelAndView;
	}
}
