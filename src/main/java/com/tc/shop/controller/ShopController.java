package com.tc.shop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.regexp.internal.RE;
import com.tc.shop.model.OaUser;
import com.tc.shop.model.ShopSeller;
import com.tc.shop.model.ShopSellerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tc.shop.service.ShopSellerInfoService;
import com.tc.shop.service.ShopSellerService;
import com.tc.shop.util.StringUtil;

@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopSellerService sellerService;
    @Autowired
    private ShopSellerInfoService sellerInfoService;
    @Autowired
    private HttpSession session;

    /**
     * @param pageNum  页面数量
     * @param pageSize 页面大小
     * @return 获取前端输出的门店名称，如果为空则查询所有的门店信息，如果不为空则查询该门店的信息
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView allShops(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(required = false) String shopName
    ) {
        ModelAndView modelAndView = new ModelAndView();
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        //企业信息
        OaUser oaUser = (OaUser) session.getAttribute("admin");
        if (oaUser == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        //企业编号
        Integer oaUserId = oaUser.getId();
        // 获取前端的输入的门店名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 门店信息的声明
        List<ShopSeller> ShopSellerList = null;
        List<ShopSellerInfo> SellerInfoList = null;
        PageInfo<ShopSellerInfo> page = new PageInfo<>();
        PageInfo<ShopSeller> page1 = new PageInfo<>();
        if (shopName != null && !"".equals(shopName)) {

            modelAndView.addObject("shopName", shopName);
            // 如果不为空则按照店铺名称开始分页查询
            PageHelper.startPage(pageNum, pageSize);
            SellerInfoList = sellerInfoService.getByCompany(shopName);
            if (SellerInfoList != null) {
                for (ShopSellerInfo sInfo : SellerInfoList) {

                    ShopSeller seller = sellerService.getBySId(sInfo.getSid());
                    Integer sellerOaUserId = seller.getOaUserId();
                    if (sellerOaUserId.equals(oaUserId)) {
                        map = new HashMap<String, Object>();
                        map.put("id", String.valueOf(seller.getId()));
                        map.put("sellerName", seller.getSellername());
                        Date createTime = seller.getCreateTime();
                        if (createTime != null) {
                            map.put("createTime", sdf.format(createTime));
                        } else {
                            map.put("createTime", "");
                        }
                        map.put("name", seller.getName());
                        map.put("phone", seller.getPhone());
                        map.put("company", sInfo.getCompany());
                        map.put("addr", sInfo.getAddress());
                        map.put("shopId", sInfo.getShopId());
                        maps.add(map);
                    }

                }
                page = new PageInfo<ShopSellerInfo>(SellerInfoList);
            }

            modelAndView.addObject("page", page);
        } else {

            modelAndView.addObject("shopName", "");
            // 如果门店名称为空则查询所有的门店信息
            PageHelper.startPage(pageNum, pageSize);
            ShopSellerList = sellerService.getByOaUserId(oaUserId);
            if (ShopSellerList != null) {
                for (ShopSeller seller : ShopSellerList) {
                    map = new HashMap<String, Object>();

                    ShopSellerInfo sInfo = sellerInfoService.getBySId(seller.getSid());
                    map.put("id", String.valueOf(seller.getId()));
                    map.put("sellerName", seller.getSellername());
                    Date createTime = seller.getCreateTime();
                    if (createTime != null) {
                        map.put("createTime", sdf.format(createTime));
                    } else {
                        map.put("createTime", "");
                    }
                    map.put("name", seller.getName());
                    map.put("phone", seller.getPhone());
                    map.put("company", sInfo.getCompany());
                    map.put("addr", sInfo.getAddress());
                    map.put("shopId", sInfo.getShopId());
                    maps.add(map);
                }
                page1 = new PageInfo<ShopSeller>(ShopSellerList);

            }
            modelAndView.addObject("page", page1);
        }
        modelAndView.addObject("shops", maps);
        modelAndView.setViewName("shops");

        return modelAndView;

    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage() {
        OaUser oaUser = (OaUser) session.getAttribute("admin");
        if(oaUser==null){
            return null;
        }
        return "shopAdd";

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addForm(
            @RequestParam(required = false) String sellername,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String address) {
        ModelAndView modelAndView = new ModelAndView();
        Date date = new Date();
        //企业信息
        OaUser oaUser = (OaUser) session.getAttribute("admin");
        //企业编号
        Integer oaUserId = oaUser.getId();
        String sid = StringUtil.seqGenerate().toString();// 随机生成的字符串用作用户的SId字段
        ShopSeller seller = new ShopSeller();
        seller.setPassword(StringUtil.EncoderByMd5(password));
        seller.setOaUserId(oaUserId);
        seller.setSid(sid);
        seller.setSellername(sellername);
        seller.setName(name);
        seller.setPhone(phone);
        seller.setCreateTime(date);

        int count = sellerService.insert(seller);// 添加信息
        if (count < 0) {
            modelAndView.addObject("error", "添加错误");// 错误操作的消息返回
        }
        ShopSellerInfo sellerInfo = new ShopSellerInfo();
        sellerInfo.setSid(sid);
        sellerInfo.setCreateTime(date);
        if (company != null && !"".equals(company)) {
            sellerInfo.setCompany(company);
        }
        if (address != null && !"".equals(address)) {
            sellerInfo.setAddress(address);
        }
        int count1 = sellerInfoService.insert(sellerInfo);// 添加详细信息
        if (count1 < 0) {
            modelAndView.addObject("error", "添加错误");
        }
        modelAndView.setViewName("redirect:/shop/all");
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteShop(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        ShopSeller seller = sellerService.getById(id);
        ShopSellerInfo sellerInfo = sellerInfoService.getBySId(seller.getSid());

        int flagSeller = sellerService.deleteById(id);
        int flagInfo = sellerInfoService.deleteById(sellerInfo.getShopId());
        if (flagSeller < 0 || flagInfo < 0) {
            modelAndView.addObject("error", "删除错误");
        }
        modelAndView.setViewName("redirect:/shop/all");
        return modelAndView;
    }

    /**
     * @return 门店的信息
     */
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public ModelAndView detailShop(@PathVariable(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        ShopSeller seller = sellerService.getById(id);
        modelAndView.addObject("seller", seller);
        ShopSellerInfo sellerInfo = sellerInfoService.getBySId(seller.getSid());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("company", sellerInfo.getCompany());
        map.put("address", sellerInfo.getAddress());
        modelAndView.addObject("map", map);
        modelAndView.setViewName("shopModify");
        return modelAndView;
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public ModelAndView modifyForm(
            @PathVariable(value = "id", required = true) Integer id,
            @RequestParam(required = false) String sid,
            @RequestParam(required = false) String sellername,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String address,
            HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        ShopSeller seller = sellerService.getById(id);

        sid = seller.getSid();
        seller.setSellername(sellername);
        seller.setName(name);
        seller.setPhone(phone);
        // 修改信息
        int count = sellerService.modify(seller);
        if (count < 0) {
            modelAndView.addObject("error", "修改错误");// 错误操作的消息返回
        }
        ShopSellerInfo sellerInfo = sellerInfoService.getBySId(sid);
        sellerInfo.setSid(sid);
        if (company != null && !"".equals(company)) {
            sellerInfo.setCompany(company);
        }
        if (address != null && !"".equals(address)) {
            sellerInfo.setAddress(address);
        }

        int count1 = sellerInfoService.modifyBySid(sellerInfo);// 添加详细信息
        if (count1 < 0) {
            modelAndView.addObject("msg", "添加错误");
        }
        modelAndView.setViewName("redirect:/shop/all");
        return modelAndView;
    }


    @RequestMapping("/import")
    public ModelAndView importExcel(MultipartFile excel)
            throws InvalidFormatException, IOException {
        ModelAndView modelAndView = new ModelAndView();

        if (excel == null) {
            modelAndView.addObject("msg", "未上传文件，上传失败！");
            modelAndView.setViewName("redirect:/shop/all");
            return modelAndView;
        }
        String filename = excel.getOriginalFilename();
        if (!filename.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
            modelAndView.addObject("msg", "文件格式不正确！请使用.xls或.xlsx后缀的文档，导入失败！");
            modelAndView.setViewName("redirect:/shop/all");
            return modelAndView;
        }
        //shopService.importExcel(excel);
        modelAndView.addObject("msg", "导入成功！");
        modelAndView.setViewName("redirect:/shop/all");
        return modelAndView;
    }

}
