package com.tc.shop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tc.shop.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.service.OaGoodPicService;
import com.tc.shop.service.OaGoodService;
import com.tc.shop.service.OaGoodTypeService;
import com.tc.shop.util.Constants;
import com.tc.shop.util.FileUtil;
import com.tc.shop.util.ImageUploadUtil;
import com.tc.shop.util.OcrUtil;

@Controller
@RequestMapping("good")
public class OaGoodController {

    @Autowired
    private OaGoodService goodService;
    @Autowired
    private OaGoodTypeService gtService;
    @Autowired
    private HttpSession session;
    @Autowired
    private OaGoodPicService gpService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allGoods(
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "4") Integer pageSize,
            @RequestParam(required = false) String goodName,
            @RequestParam(required = false) Integer goodType) {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;

        // 商家信息
        OaUser admin = (OaUser) session.getAttribute("admin");
        if (admin == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        Integer oaId = admin.getId();

        // 商品类型的list
        List<OaGoodType> goodTypeList = gtService.getByOaId(oaId);
        if (goodTypeList != null) {
            // 所有的商品类别
            modelAndView.addObject("goodTypes", goodTypeList);
        } else {
            goodTypeList = new ArrayList<>();
            modelAndView.addObject("goodTypes", goodTypeList);
        }


        List<OaGood> goodList = null;
        // 开始分页查询
        PageHelper.startPage(pageNum, pageSize);
        if ((goodName != null && !"".equals(goodName)) || (goodType != null && !"".equals(goodType) && !"0".equals(goodType))) {
            goodList = goodService.getByOaIdAndTitleAndType(oaId, goodName, goodType);
            modelAndView.addObject("goodName", goodName);
            modelAndView.addObject("goodType", goodType);
        } else {
            goodList = goodService.getByOaId(oaId);
            modelAndView.addObject("" +
                    "", "");
            modelAndView.addObject("goodType", "");
        }
        PageInfo<OaGood> page = new PageInfo<>();
        if (goodList != null) {
            for (OaGood good : goodList) {
                map = new HashMap<String, Object>();
                System.out.println(good);
                map.put("id", String.valueOf(good.getId()));
                Integer typeId = good.getGoodTypeId();
                //System.out.println("goodTypeId" + typeId);
                OaGoodType oaGoodType = gtService.getById(typeId);
                if (oaGoodType != null) {
                    map.put("type", oaGoodType.getName());
                    //System.out.println("goodType" + oaGoodType.getName());
                } else {
                    map.put("type", "");
                }
                String goodImg = good.getGoodImg();
                goodImg = Constants.SUBJECT_PATH + goodImg;
                map.put("img", goodImg);
                map.put("name", good.getGoodName());
                map.put("content", good.getContent());
                map.put("price", good.getGoodPrice());
                maps.add(map);

            }
            page = new PageInfo<OaGood>(goodList);
        }


        // 商品
        modelAndView.addObject("goods", maps);
        modelAndView.addObject("page", page);
        modelAndView.setViewName("goods");
        return modelAndView;
    }

    /**
     * @param model
     * @return 商品添加页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addGoodPage(Model model) {
        OaUser admin = (OaUser) session.getAttribute("admin");
        Integer oaId = admin.getId();
        List<OaGoodType> goodTypeList = gtService.getByOaId(oaId);
        model.addAttribute("goodTypes", goodTypeList);
        return "goodsAdd";
    }

    /**
     * @param good    前端提交的商品信息
     * @param request 用于获取项目路径
     * @param file    图片文件
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addGood(
            OaGood good, HttpServletRequest request,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) MultipartFile[] goodPics) {
        ModelAndView modelAndView = new ModelAndView();
        OaUser admin = (OaUser) session.getAttribute("admin");
        Integer oaId = admin.getId();
        good.setOaId(oaId);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        good.setCreateTime(sdf.format(date));
        String filename = file.getOriginalFilename();
        if (filename != null && !"".equals(filename)) {
            String address = FileUtil.uploadImage(request, file, "good");
            if (address != null) {
                good.setGoodImg(address);
            }
        }
        int count = goodService.insert(good);

        if (count < 0) {
            modelAndView.addObject("error", "添加错误");
        }
        good = goodService.getByExample(good);
        Integer goodId = null;
        if (good.getId() != null) {
            goodId = good.getId();
        }
        if (goodId != null) {
            if (goodPics.length > 0) {
                for (MultipartFile pic : goodPics) {
                    String filename2 = pic.getOriginalFilename();
                    if (filename2 != null && !"".equals(filename2)) {
                        String address = FileUtil.uploadImage(request, pic, "good");
                        OaGoodPic goodPic = new OaGoodPic();
                        goodPic.setGoodId(goodId);
                        goodPic.setGoodsPic(address);
                        gpService.insert(goodPic);
                    }
                }
            }
        }

        modelAndView.setViewName("redirect:/good/all");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteGood(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        int count = goodService.deleteById(id);
        if (count < 0) {
            modelAndView.addObject("error", "删除错误");
        }
        modelAndView.setViewName("redirect:/good/all");
        return modelAndView;
    }

    /**
     * @param id 商品的主键
     * @return 商品修改页面
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView detailPage(HttpServletRequest request,
                                   @PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaGood good = goodService.getById(id);

        String goodImg = good.getGoodImg();
        goodImg = Constants.SUBJECT_PATH + goodImg;
        good.setGoodImg(goodImg);
        modelAndView.addObject("good", good);
        OaUser admin = (OaUser) session.getAttribute("admin");
        if (admin == null) {
            return null;
        }
        Integer oaId = admin.getId();


        // 商品类型
        List<OaGoodType> goodTypeList = new ArrayList<>();
        if (oaId != null) {
            goodTypeList = gtService.getByOaId(oaId);
        }
        modelAndView.addObject("goodTypes", goodTypeList);
        List<String> pics = new ArrayList<>();
        List<OaGoodPic> goodPics = gpService.getByGoodId(id);
        if (goodPics != null) {
            for (OaGoodPic goodPic : goodPics) {
                pics.add(Constants.SUBJECT_PATH + goodPic.getGoodsPic());
            }
        }
        modelAndView.addObject("goodPics", pics);

        modelAndView.setViewName("goodsModify");
        return modelAndView;
    }

    /**
     * @param id   商品主键
     * @param good 商品信息
     * @param file 图片文件
     * @return
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView detail(OaGood good, @PathVariable(value = "id", required = true) Integer id,
                               @RequestParam(required = false) MultipartFile file,
                               HttpServletRequest request,
                               @RequestParam(required = false) MultipartFile[] goodPics) {
        ModelAndView modelAndView = new ModelAndView();
        good.setId(id);
        String filename = file.getOriginalFilename();
        System.out.println("fileName:" + filename);
        if (filename != null && !"".equals(filename)) {
            String address = FileUtil.uploadImage(request, file, "good");
            if (address != null && !"".equals(address)) {
                good.setGoodImg(address);
            }
        }
        int count = goodService.modify(good);
        if (count < 1) {
            modelAndView.addObject("error", "修改错误");
        }
        int flag = 0;
        for (MultipartFile pic : goodPics) {
            String filename2 = pic.getOriginalFilename();
            if (filename2 != null && !"".equals(filename2)) {
                flag = 1;
            }
        }
        if (flag == 1) {
            gpService.deleteByGoodId(id);
            for (MultipartFile pic : goodPics) {
                System.out.println("pic" + pic.getOriginalFilename());
                String filename2 = pic.getOriginalFilename();
                if (filename2 != null && !"".equals(filename2)) {
                    String address = FileUtil.uploadImage(request, pic, "good");
                    OaGoodPic goodPic = new OaGoodPic();
                    goodPic.setGoodId(id);
                    goodPic.setGoodsPic(address);
                    gpService.insert(goodPic);
                }
            }
        }
        modelAndView.setViewName("redirect:/good/all");
        return modelAndView;
    }

    @RequestMapping("/import")
    public ModelAndView importExcel(MultipartFile excel) throws InvalidFormatException, IOException {
        ModelAndView modelAndView = new ModelAndView();

        if (excel == null) {
            modelAndView.addObject("msg", "未上传文件，上传失败！");
            modelAndView.setViewName("redirect:/good/all");
            return modelAndView;
        }
        String filename = excel.getOriginalFilename();
        if (!filename.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
            modelAndView.addObject("msg", "文件格式不正确！请使用.xls或.xlsx后缀的文档，导入失败！");
            modelAndView.setViewName("redirect:/good/all");
        }
        // goodService.importExcel(excel);
        modelAndView.addObject("msg", "导入成功！");
        modelAndView.setViewName("redirect:/good/all");
        return modelAndView;
    }

    /**
     * @param request
     * @param response
     * @return 图片文件的上传
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
        String DirectoryName = Constants.Good_Dir_Name;
        System.out.println("文件上传");
        try {
            ImageUploadUtil.ckeditor(request, response, DirectoryName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/uploadPic")
    @ResponseBody
    public Result parseCardFont(HttpServletRequest request, MultipartFile picFile) {
        Result result = new Result();
        String filename = picFile.getOriginalFilename();

        if (filename != null && !"".equals(filename)) {
            String uploadImgAddr = FileUtil.uploadImage(request, picFile, "good");
            if (uploadImgAddr != null) {
                result.setCode("1");
                result.setSuccess();
                result.setData(uploadImgAddr);
            }
        }
        return result;
    }
}
