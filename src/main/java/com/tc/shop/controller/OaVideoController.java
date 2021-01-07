package com.tc.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaUser;
import com.tc.shop.model.OaVideo;
import com.tc.shop.model.OaVideoType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.service.OaVideoService;
import com.tc.shop.service.OaVideoTypeService;
import com.tc.shop.util.Constants;
import com.tc.shop.util.FileUtil;

@Controller
@RequestMapping("/video")
public class OaVideoController {

    @Autowired
    private HttpSession session;
    @Autowired
    private OaVideoService videoService;
    @Autowired
    private OaVideoTypeService vtService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allVideos(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "3") Integer pageSize,
            @RequestParam(required = false) String videoTitle,
            @RequestParam(required = false) Integer videoType
    ) {
        ModelAndView modelAndView = new ModelAndView();
        OaUser admin = (OaUser) session.getAttribute("admin");
        // 用户id
        Integer oaId = admin.getId();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;

        List<OaVideo> videos = null;
        PageHelper.startPage(pageNum, pageSize);
        if (videoTitle != null && !"".equals(videoTitle)) {
            videos = videoService.getByOaIdAndTitleAndTypeId(oaId, videoTitle, videoType);
            modelAndView.addObject("videoTitle", videoTitle);
            modelAndView.addObject("videoType", videoType);
        } else {
            videos = videoService.getByOaId(oaId);
            modelAndView.addObject("videoTitle", "");
            modelAndView.addObject("videoType", "");
        }
        PageInfo<OaVideo> page = new PageInfo<>();
        if (videos != null) {
            for (OaVideo video : videos) {
                map = new HashMap<String, Object>();
                map.put("id", video.getId());
                Integer typeId = video.getTypeId();
                OaVideoType oaVideoType = vtService.getById(typeId);
                if (videoType != null) {
                    map.put("type", oaVideoType.getName());
                } else {
                    map.put("type", "");
                }

                String img = video.getImg();
                map.put("img", Constants.SUBJECT_PATH + img);
                map.put("title", video.getTitle());
                maps.add(map);
            }
            page = new PageInfo<OaVideo>(videos);
        }
        modelAndView.addObject("videos", maps);

        modelAndView.addObject("page", page);
        modelAndView.setViewName("videos");
        return modelAndView;
    }

    /**
     * @param id 主键
     * @return 按主键删除记录
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        int count = videoService.deleteById(id);
        if (count < 0) {
            modelAndView.addObject("msg", "删除错误");
        } else {
            modelAndView.addObject("msg", "删除成功");
        }
        modelAndView.setViewName("redirect:/video/all");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        OaVideo video = new OaVideo();
        video.setIsShow(1);
        model.addAttribute("video", video);
        OaUser admin = (OaUser) session.getAttribute("admin");
        Integer oaId = admin.getId();
        List<OaVideoType> videoTypes = vtService.getByOaId(oaId);
        model.addAttribute("videoTypes", videoTypes);

        return "videosAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(OaVideo video, HttpServletRequest request,
                                @RequestParam("file") CommonsMultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        OaUser admin = (OaUser) session.getAttribute("admin");
        Integer oaId = admin.getId();
        video.setOaId(oaId);
        Date date = new Date();
        video.setCreateTime(date);
        video.setLastUpdateTime(date);
        String filename = file.getOriginalFilename();
        if (filename != null && !"".equals(filename)) {
            String address = FileUtil.uploadImage(request, file, "video");
            if (address != null) {
                video.setImg(address);
            }
        }
        int count = videoService.insert(video);
        if (count < 0) {
            modelAndView.addObject("msg", "添加错误");
            modelAndView.setViewName("add");

        } else {
            modelAndView.addObject("msg", "添加成功");
            modelAndView.setViewName("redirect:/video/all");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyPage(HttpServletRequest request,
                                   @PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaVideo video = videoService.getById(id);
        String img = video.getImg();
        video.setImg(Constants.SUBJECT_PATH + img);
        modelAndView.addObject("video", video);
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        List<OaVideoType> videoTypes = vtService.getByOaId(oaId);

        modelAndView.addObject("videoTypes", videoTypes);
        modelAndView.setViewName("videosModify");
        return modelAndView;
    }

    /**
     * @param id 商品案例的主键
     * @return 根据id查询信息，并返回到案例修改页面
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(OaVideo video, HttpServletRequest request,
                                   @PathVariable(value = "id", required = true) Integer id, @RequestParam(required = false) CommonsMultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();

        String filename = file.getOriginalFilename();
        if (filename != null && !"".equals(filename)) {
            String address = FileUtil.uploadImage(request, file, "video");
            if (address != null) {
                video.setImg(address);
            }
        }
        video.setLastUpdateTime(new Date());
        int count = videoService.modifyById(video);
        if (count < 0) {
            modelAndView.addObject("msg", "修改错误");
        } else {
            modelAndView.addObject("msg", "修改成功");
        }

        modelAndView.setViewName("redirect:/video/all");
        return modelAndView;
    }

}
