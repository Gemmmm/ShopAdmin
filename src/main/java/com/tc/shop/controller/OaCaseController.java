package com.tc.shop.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaCase;
import com.tc.shop.model.OaCasePic;
import com.tc.shop.model.OaCaseType;
import com.tc.shop.model.OaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.service.OaCasePicService;
import com.tc.shop.service.OaCaseService;
import com.tc.shop.service.OaCaseTypeService;
import com.tc.shop.util.Constants;
import com.tc.shop.util.FileUtil;
import com.tc.shop.util.ImageUploadUtil;
import com.tc.shop.util.StringUtil;

@Controller
@RequestMapping("case")
public class OaCaseController {
    @Autowired
    private OaCaseService caseService;
    @Autowired
    private HttpSession session;
    @Autowired
    private OaCaseTypeService ctService;
    @Autowired
    private OaCasePicService cpService;

    /**
     * @return 查询全部的案例信息
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allCase(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "4") Integer pageSize,
            @RequestParam(required = false) String caseTitle,
            @RequestParam(required = false) Integer caseTypeId) {

        ModelAndView modelAndView = new ModelAndView();
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        List<OaCaseType> caseTypes = ctService.getByOaId(oaId);
        if(caseTypes!=null){
            modelAndView.addObject("caseTypes", caseTypes);
        }else{
            modelAndView.addObject("caseTypes", new ArrayList<>());
        }
        // 声明所返回的数据Map
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;

        // 案例 数组
        List<OaCase> caseList = null;
        // 开始分页查询
        PageHelper.startPage(pageNum, pageSize);
        if ((caseTitle != null && !"".equals(caseTitle)) || (caseTypeId != null && caseTypeId != 0)) {
            caseList = caseService.getByOaIdAndTitleAndType(oaId, caseTitle, caseTypeId);
            modelAndView.addObject("caseTitle", caseTitle);
            modelAndView.addObject("caseType", caseTypeId);
        } else {
            caseList = caseService.getByOaId(oaId);
            modelAndView.addObject("caseTitle", "");
            modelAndView.addObject("caseType", "");
        }

        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PageInfo<OaCase> page = new PageInfo<>();
        if (caseList != null) {
            page = new PageInfo<>(caseList);
            for (OaCase c : caseList) {
                map = new HashMap<>();
                map.put("id", c.getId());
                Integer typeId = c.getTypeId();
                OaCaseType caseType = ctService.getById(typeId);
                if (caseType != null) {
                    map.put("type", caseType.getName());
                } else {
                    map.put("type", "");
                }
                map.put("title", c.getTitle());
                map.put("example", c.getExample());
                map.put("lookNum", c.getLookNum());
                map.put("upNum", c.getUpNum());
                Date createTime = c.getCreateTime();
                if (createTime != null) {
                    map.put("createTime", sdf.format(createTime));
                } else {
                    map.put("createTime", "");
                }
                map.put("content", c.getContent());
                String img = Constants.SUBJECT_PATH + c.getImg();
                map.put("img", img);
                maps.add(map);
            }
        }

        modelAndView.addObject("cases", maps);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("cases");
        return modelAndView;
    }

    /**
     * @return 数据添加页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        List<OaCaseType> caseTypes = ctService.getByOaId(oaId);
        if(caseTypes!=null){
            modelAndView.addObject("caseTypes", caseTypes);
        }else{
            modelAndView.addObject("caseTypes", new ArrayList<>());
        }
        modelAndView.setViewName("casesAdd");
        return modelAndView;

    }

    /**
     * @return 数据添加表单
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(
            OaCase cases,
            HttpServletRequest request,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) MultipartFile[] casePics) {
        ModelAndView modelAndView = new ModelAndView();
        // session中的seller用户信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        cases.setOaId(admin.getId());

        Date date = new Date();
        cases.setCreateTime(date);// 设置日期
        cases.setLastUpdateTime(date);
        String filename = file.getOriginalFilename();
        if (filename != null && !"".equals(filename)) {
            String imgAddr = FileUtil.uploadImage(request, file, "case");
            if (imgAddr != null) {
                cases.setImg(imgAddr);
            }
        }
        caseService.insert(cases);// 插入

        cases = caseService.getByExample(cases);
        Integer caseId = null;
        if (cases != null) {
            caseId = cases.getId();
        }
        int flag = 0;
        for (MultipartFile pic : casePics) {
            String filename2 = pic.getOriginalFilename();
            if (filename2 != null && !"".equals(filename2)) {
                flag = 1;
            }
        }

        if (flag == 1) {
            cpService.deleteByCaseId(caseId);
            if (casePics.length > 0) {
                for (MultipartFile pic : casePics) {
                    String filename2 = pic.getOriginalFilename();
                    if (filename2 != null && !"".equals(filename2)) {
                        String address = FileUtil.uploadImage(request, pic, "case");
                        OaCasePic casePic = new OaCasePic();
                        casePic.setCaseId(caseId);
                        casePic.setCasePic(address);
                        int flag1 = cpService.insert(casePic);
                    }
                }
            }
        }
        // 插入后的正常返回
        modelAndView.setViewName("redirect:/case/all");
        return modelAndView;

    }

    /**
     * @param id 商品案例的主键
     * @return 根据id查询信息，并返回到案例修改页面
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        // session中的企业信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        OaCase cases = caseService.getById(id);
        String img = cases.getImg();
        cases.setImg(Constants.SUBJECT_PATH + img);

        modelAndView.addObject("cases", cases);

        List<OaCaseType> caseTypes = ctService.getByOaId(oaId);
        modelAndView.addObject("caseTypes", caseTypes);
        List<String> pics = new ArrayList<>();
        List<OaCasePic> casePics = cpService.getByCaseId(id);
        if (casePics != null) {
            for (OaCasePic casePic : casePics) {
                pics.add(Constants.SUBJECT_PATH + casePic.getCasePic());
            }
        }
        modelAndView.addObject("casePics", pics);
        modelAndView.setViewName("casesModify");
        return modelAndView;
    }

    /**
     * @param id      门店案例的主键
     * @param cases   前端提交的门店案例信息
     * @param request
     * @param file    前端提交的图片文件
     * @return 案例修改成功后返回到案例列表页面
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(
            OaCase cases, HttpServletRequest request,
            @PathVariable(value = "id", required = true) Integer id,
            @RequestParam(required = false)  MultipartFile file,
            @RequestParam(required = false) MultipartFile[] casePics) {
        ModelAndView modelAndView = new ModelAndView();
        cases.setId(id);
        // 获取文件名
        String filename = file.getOriginalFilename();
        System.out.println("fileName:" + filename);
        cases.setLastUpdateTime(new Date());
        if (filename != null && !"".equals(filename)) {
            String imgAddr = FileUtil.uploadImage(request, file, "case");
            if (imgAddr != null && !"".equals(imgAddr)) {
                cases.setImg(imgAddr);
            }
        }
        int count = caseService.modify(cases);
        if (count < 0) {
            modelAndView.addObject("error", "修改错误");
        }
        int flag = 0;
        for (MultipartFile pic : casePics) {
            String filename2 = pic.getOriginalFilename();
            if (filename2 != null && !"".equals(filename2)) {
                flag = 1;
            }
        }
        if (flag > 0) {
            cpService.deleteByCaseId(id);
            for (MultipartFile pic : casePics) {
                String filename2 = pic.getOriginalFilename();
                if (filename2 != null && !"".equals(filename2)) {
                    String address = FileUtil.uploadImage(request, pic, "case");
                    OaCasePic casePic = new OaCasePic();
                    casePic.setCaseId(id);
                    casePic.setCasePic(address);
                    cpService.insert(casePic);
                }
            }
        }
        modelAndView.setViewName("redirect:/case/all");
        return modelAndView;
    }

    /**
     * @param id
     * @return 删除成功后返回到案例列表页面
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();

        int count = caseService.deleteById(id);

        if (count < 0) {
            // 错误返回的信息
            modelAndView.addObject("error", "删除错误");
        }
        modelAndView.setViewName("redirect:/case/all");
        return modelAndView;
    }

//	/**
//	 *
//	 * @param request
//	 * @param response
//	 * @return 图片文件的上传
//	 */
//	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
//		String DirectoryName = Constants.Case_Dir_Name;
//		System.out.println("文件上传");
//		try {
//			ImageUploadUtil.ckeditor(request, response, DirectoryName);
//		} catch (IllegalStateException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
