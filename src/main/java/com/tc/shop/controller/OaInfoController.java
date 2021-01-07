package com.tc.shop.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaInfo;
import com.tc.shop.model.OaInfoType;
import com.tc.shop.model.OaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.service.OaInfoService;
import com.tc.shop.service.OaInfoTypeService;
import com.tc.shop.util.Constants;
import com.tc.shop.util.FileUtil;

@Controller
@RequestMapping("info")
public class OaInfoController {
    @Autowired
    private HttpSession session;
    @Autowired
    private OaInfoService infoService;
    @Autowired
    private OaInfoTypeService itService;

    /**
     * @param pageNum
     * @param pageSize
     * @return 所有资讯信息的查询
     */
    @RequestMapping("/all")
    public ModelAndView all(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "4") Integer pageSize,
            @RequestParam(required = false) String infoTitle,
            @RequestParam(required = false) Integer infoType) {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        // 获取用户信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // 用户id
        Integer oaId = admin.getId();
        List<OaInfoType> infoTypes = itService.getByOaId(oaId);
        if (infoTypes != null) {
            modelAndView.addObject("infoTypes", infoTypes);
        } else {
            modelAndView.addObject("infoTypes", new ArrayList<>());
        }
        List<OaInfo> infoList = null;
        // 分页查询开始
        PageHelper.startPage(pageNum, pageSize);
        if ((infoTitle != null && !"".equals(infoTitle)) || (infoType != null && infoType != 0 && !"".equals(infoType))) {
            infoList = infoService.getByOaIdAndTitleAndType(oaId, infoTitle, infoType);
            modelAndView.addObject("infoTitle", infoTitle);
            modelAndView.addObject("infoType", infoType);
        } else {
            infoList = infoService.getByOaId(oaId);
            modelAndView.addObject("infoTitle", "");
            modelAndView.addObject("infoType", "");
        }
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (infoList != null)
            for (OaInfo info : infoList) {
                map = new HashMap<String, Object>();
                map.put("id", String.valueOf(info.getId()));
                String img = info.getImg();
                img = Constants.SUBJECT_PATH + img;
                map.put("img", img);
                map.put("title", info.getTitle());
                map.put("content", info.getContent());
                map.put("lookNum", info.getLookNum());
                map.put("upNum", info.getUpNum());
                map.put("isMustReport", info.getIsMustReport());
                map.put("isShow", info.getIsShow().toString());

                Date createTime = info.getCreateTime();
                if (createTime != null && !"".equals(createTime)) {
                    map.put("createTime", sdf.format(createTime));
                }
                Integer typeId = info.getTypeId();
                OaInfoType oaInfoType = itService.getById(typeId);
                if (infoType != null) {
                    map.put("type", oaInfoType.getName());
                } else {
                    map.put("type", "");
                }

                maps.add(map);
            }

        PageInfo<OaInfo> page = new PageInfo<OaInfo>(infoList);
        modelAndView.addObject("page", page);
        modelAndView.addObject("infos", maps);
        modelAndView.setViewName("infos");
        return modelAndView;
    }

    /**
     * @param id 主键
     * @return 按主键删除记录
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        int count = infoService.deleteById(id);
        if (count < 0) {
            modelAndView.addObject("error", "删除错误");
        }
        modelAndView.setViewName("redirect:/info/all");
        return modelAndView;
    }

    /**
     * @param model
     * @return 返回到信息添加页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        // 获取用户信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // 用户id
        Integer oaId = admin.getId();
        List<OaInfoType> infoTypes = itService.getByOaId(oaId);
        OaInfo oaInfo = new OaInfo();
        oaInfo.setIsShow(1);
        oaInfo.setIsMustReport("0");
        model.addAttribute("info", oaInfo);
        model.addAttribute("infoTypes", infoTypes);
        return "infosAdd";
    }

    /**
     * @param info    前端提交的信息
     * @param request
     * @param file    图片文件
     * @return 资讯的添加页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(OaInfo info, HttpServletRequest request,
                                @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        OaUser admin = (OaUser) session.getAttribute("admin");
        Integer oaId = admin.getId();
        info.setOaId(oaId);
        Date date = new Date();
        info.setCreateTime(date);
        info.setLastUpdateTime(date);
        String imgAddr = FileUtil.uploadImage(request, file, "info");
        if (imgAddr != null) {
            info.setImg(imgAddr);
        }
        int count = infoService.insert(info);

        if (count < 0) {
            modelAndView.addObject("error", "添加错误");
        }
        modelAndView.setViewName("redirect:/info/all");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyPage(
            HttpServletRequest request,
            @PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaInfo info = infoService.getById(id);

        String img = info.getImg();
        img = Constants.SUBJECT_PATH + img;
        info.setImg(img);
        modelAndView.addObject("info", info);
        OaUser admin = (OaUser) session.getAttribute("admin");
        Integer oaId = admin.getId();
        List<OaInfoType> infoTypes = itService.getByOaId(oaId);
        modelAndView.addObject("infoTypes", infoTypes);
        modelAndView.setViewName("infosModify");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(@PathVariable(value = "id", required = true) Integer id,  OaInfo info,
                                   HttpServletRequest request, @RequestParam(required = false) MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        info.setId(id);

        String filename = file.getOriginalFilename();
        if (filename != null && !"".equals(filename)) {
            String imgAddr = FileUtil.uploadImage(request, file, "info");
            if (imgAddr != null && !"".equals(imgAddr)) {
                info.setImg(imgAddr);
            }
        }
        info.setLastUpdateTime(new Date());
        int count = infoService.modify(info);
        if (count < 0) {
            modelAndView.addObject("error", "修改错误");
        }
        modelAndView.setViewName("redirect:/info/all");
        return modelAndView;
    }

    @RequestMapping(value = "/modifyIsShow/{id}", method = RequestMethod.GET)
    public ModelAndView modifyIsShow(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaInfo info = infoService.getById(id);
        Integer isShow = info.getIsShow();
        if (isShow == 1) {
            info.setIsShow(0);
            //info.setIsMustReport("0");
        } else {
            info.setIsShow(1);
        }
        infoService.modify(info);
        modelAndView.setViewName("redirect:/info/all");
        return modelAndView;
    }

    @RequestMapping(value = "/modifyMustReport/{id}", method = RequestMethod.GET)
    public ModelAndView modifyMustReport(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaInfo info = infoService.getById(id);
        String isMustReport = info.getIsMustReport();
        if (isMustReport.equals("1")) {
            info.setIsMustReport("0");
        } else {
            info.setIsMustReport("1");
        }
        infoService.modify(info);
        modelAndView.setViewName("redirect:/info/all");
        return modelAndView;
    }

//	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
//		String DirectoryName = Constants.Info_Dir_Name;
//		String type=request.getParameter("type");
//		System.out.println("type:::"+type);
//		
//		System.out.println("文件开始上传");
//		try {
//			ImageUploadUtil.ckeditor(request, response, DirectoryName);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
