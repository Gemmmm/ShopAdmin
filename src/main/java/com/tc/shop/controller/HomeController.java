package com.tc.shop.controller;

import com.tc.shop.model.OaUser;
import com.tc.shop.service.OaUserService;
import com.tc.shop.service.ShopSellerService;
import com.tc.shop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private OaUserService userService;
    @Autowired
    private HttpSession session;

    @Autowired
    private ShopSellerService sellerService;

    /**
     * @param model
     * @return 重定位到商家用户的添加页面
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(Model model) {
        return "redirect:/login";
    }


    // 请求登录页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(required = false) String username, @RequestParam(required = false) String password) {
        ModelAndView modelAndView=new ModelAndView();
        // 登录的用户信息
        if (username == null || "".equals(username)) {
            System.out.println("账号不能为空");
            modelAndView.addObject("loginError", "请输入用户名");

            modelAndView.setViewName("login");
            return  modelAndView;
        }
        if (password == null || "".equals(password)) {
            System.out.println("密码不能为空");
            modelAndView.addObject("loginError", "请输入密码");
            modelAndView.setViewName("login");
            return  modelAndView;
        }

        OaUser findAdmin = userService.findAdmin(username, StringUtil.EncoderByMd5(password));
        System.out.println("admin:" + findAdmin);

        if (findAdmin == null) {
            System.out.println("用户名或密码错误");
            modelAndView.addObject("loginError", "用户名或密码错误");
            modelAndView.setViewName("login");
            return  modelAndView;
        }


        //将user类存在session中
        session.setAttribute("admin", findAdmin);

        modelAndView.setViewName("redirect:/shop/all");
        return modelAndView;
    }

    // 处理对首页--index发起的请求
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Map<String, Object>> resultList = new ArrayList<>();

        OaUser admin = (OaUser) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/login";
        }
        model.addAttribute("admin", admin);
        return "redirect:/shop/all";
    }

    //无权限页面
    @RequestMapping(value = "/powerOff", method = RequestMethod.GET)
    public String powerOffPage() {
        return "powerOff";
    }


}
