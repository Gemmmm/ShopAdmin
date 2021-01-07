package com.tc.shop.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaPoster;
import com.tc.shop.model.OaPosterType;
import com.tc.shop.model.OaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.service.OaPosterService;
import com.tc.shop.service.OaPosterTypeService;
import com.tc.shop.util.Constants;
import com.tc.shop.util.FileUtil;


@Controller
@RequestMapping("/poster")
public class OaPosterController {
    @Autowired
    private HttpSession session;
    @Autowired
    private OaPosterService posterService;
    @Autowired
    private OaPosterTypeService ptService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allPoster(
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize,
            @RequestParam(required = false) String posterTitle,
            @RequestParam(required = false) Integer posterType
    ) {
        ModelAndView modelAndView = new ModelAndView();

        OaUser admin = (OaUser) session.getAttribute("admin");
        // 用户id
        Integer oaId = admin.getId();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        PageHelper.startPage(pageNum, pageSize);
        List<OaPoster> posters = null;
        if (posterTitle != null && !"".equals(posterTitle)) {
            posters = posterService.getByOaIdAndTitle(oaId, posterTitle);
            modelAndView.addObject("posterTitle", posterTitle);
            modelAndView.addObject("posterType", posterType);
        } else {
            posters = posterService.getByOaId(oaId);
            modelAndView.addObject("posterTitle", "");
            modelAndView.addObject("posterType", "");
        }

        if (posters != null) {
            for (OaPoster poster : posters) {
                map = new HashMap<String, Object>();
                map.put("id", poster.getId());
                map.put("title", poster.getTitle());
                Integer typeId = poster.getTypeId();
                OaPosterType oaPosterType = ptService.getById(typeId);
                if (posterType != null) {
                    map.put("type", oaPosterType.getName());
                } else {
                    map.put("type", "");
                }
                map.put("isCustomerNum", poster.getIsCustomerNum());
                map.put("isUsedNum", poster.getIsUsedNum());
                Date createTime = poster.getCreateTime();
                String img = Constants.SUBJECT_PATH + poster.getImg();
                map.put("img", img);
                if (createTime != null) {
                    map.put("createTime", sdf.format(createTime));
                } else {
                    map.put("createTime", "");
                }
                maps.add(map);
            }
            PageInfo<OaPoster> page = new PageInfo<OaPoster>(posters);
            modelAndView.addObject("page", page);

        }

        modelAndView.addObject("posters", maps);

        modelAndView.setViewName("posters");
        return modelAndView;

    }


    /**
     * @param id 主键
     * @return 按主键删除记录
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        int count = posterService.deleteById(id);
        if (count < 0) {
            modelAndView.addObject("msg", "删除错误");
        } else {
            modelAndView.addObject("msg", "删除成功");
        }
        modelAndView.setViewName("redirect:/poster/all");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        model.addAttribute("poster", new OaPoster());
        OaUser admin = (OaUser) session.getAttribute("admin");
        Integer oaId = admin.getId();
        List<OaPosterType> posterTypes = ptService.getByOaId(oaId);
        model.addAttribute("posterTypes", posterTypes);
        return "postersAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(OaPoster poster, HttpServletRequest request,
                                @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        OaUser admin = (OaUser) session.getAttribute("admin");
        Integer oaId = admin.getId();
        poster.setOaId(oaId);
        Date date = new Date();
        poster.setCreateTime(date);
        poster.setLastUpdateTime(date);
        String filename = file.getOriginalFilename();
        if (filename != null && !"".equals(filename)) {
            String address = FileUtil.uploadImage(request, file, "poster");
            if (address != null) {
                poster.setImg(address);
            }
        }
        int count = posterService.insert(poster);
        if (count < 0) {
            modelAndView.addObject("msg", "添加错误");
            modelAndView.setViewName("add");
            return modelAndView;
        } else {
            modelAndView.addObject("msg", "添加成功");
            modelAndView.setViewName("redirect:/poster/all");
            return modelAndView;
        }

    }

    /**
     * @param id 商品案例的主键
     * @return 根据id查询信息，并返回到案例修改页面
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView modifyPage(HttpServletRequest request,
                                   @PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        OaPoster poster = posterService.getById(id);
        String img = poster.getImg();
        poster.setImg(Constants.SUBJECT_PATH + img);
        modelAndView.addObject("poster", poster);

        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        List<OaPosterType> posterTypes = ptService.getByOaId(oaId);
        if (posterTypes != null) {
            modelAndView.addObject("posterTypes", posterTypes);
        } else {
            modelAndView.addObject("posterTypes", new ArrayList<>());
        }
        modelAndView.setViewName("postersModify");
        return modelAndView;
    }

    /**
     * @param id 商品案例的主键
     * @return 根据id查询信息，并返回到案例修改页面
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(OaPoster poster, HttpServletRequest request,
                                   @PathVariable(value = "id", required = true) Integer id,
                                   @RequestParam(required = false) MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        poster.setLastUpdateTime(new Date());
        OaUser admin = (OaUser) session.getAttribute("admin");
        // admin用户的sid
        Integer oaId = admin.getId();
        poster.setOaId(oaId);
        String filename = file.getOriginalFilename();
        if (filename != null && !"".equals(filename)) {
            String address = FileUtil.uploadImage(request, file, "poster");
            if (address != null) {
                poster.setImg(address);
            }
        }
        int count = posterService.modifyById(poster);
        if (count < 0) {
            modelAndView.addObject("msg", "修改错误");
        } else {
            modelAndView.addObject("msg", "修改成功");
        }

        modelAndView.setViewName("redirect:/poster/all");
        return modelAndView;
    }

}
