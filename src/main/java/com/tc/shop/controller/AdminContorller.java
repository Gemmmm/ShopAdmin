package com.tc.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tc.shop.model.OaUser;
import com.tc.shop.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tc.shop.service.OaUserService;
import com.tc.shop.util.Constants;
import com.tc.shop.util.FileUtil;
import com.tc.shop.util.OcrUtil;
import com.tc.shop.util.StringUtil;

@Controller
@RequestMapping("admin")
public class AdminContorller {

	@Autowired
	private HttpSession session;

	@Autowired
	private OaUserService oaUserService;

	/**
	 * 去除session中缓存的用户信息
	 * 
	 * @return 返回登录页面
	 */
	@RequestMapping(value = "logout")
	public String logout() {
		if (session.getAttribute("admin") != null) {
			session.removeAttribute("admin");
		}
		return "redirect:/login";
	}

	/**
	 * 修改密码 GET
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
	public ModelAndView modifypasswordpage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("modifyPwdAdmin");
		return modelAndView;

	}

	/**
	 * 
	 * @return 修改密码 POST
	 * 
	 */
	@RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
	public ModelAndView modifypassword(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		// session缓存的用户信息
		OaUser loginAdmin = (OaUser) session.getAttribute("admin");
		// 当前密码 修改密码 确认密码
		String currentPassword = request.getParameter("currentPassword");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		// System.out.println("password:" + currentPassword + " " + password + " " +
		// checkPassword);
		// 当前密码不为空
		if (currentPassword != null && !"".equals(currentPassword)) {
			// 验证当前密码是否与数据库中的一致
			if (!loginAdmin.getPassword().equals(StringUtil.EncoderByMd5(currentPassword))) {
				modelAndView.addObject("error1", "当前密码不正确");
				modelAndView.setViewName("modifyPwdAdmin");
				System.out.println("当前密码不正确");
			} else {
				// 修改密码和确认密码不为空
				if ((password != null && !"".equals(password))
						&& (checkPassword != null && !"".equals(checkPassword))) {
					// 修改密码和确认密码不同
					if (!password.equals(checkPassword)) {

						modelAndView.addObject("error1", "两次输入的密码不相同");
						modelAndView.setViewName("modifyPwdAdmin");
						System.out.println("两次输入的密码不相同");
					} else {
						// 修改密码和确认密码相同
						loginAdmin.setPassword(StringUtil.EncoderByMd5(password));
						int count1 = oaUserService.modify(loginAdmin);
						if (count1 < 0) {
							modelAndView.addObject("error1", "修改密码失败");
							modelAndView.setViewName("modifyPassword");
							System.out.println("修改密码失败");
						} else {
							session.setAttribute("admin", loginAdmin);
							modelAndView.setViewName("redirect:/shop/all");
						}
					}
				} else {
					modelAndView.setViewName("modifyPwdAdmin");
					System.out.println("密码或者确认密码为空");
				}
			}
		} else {
			// 密码为空的时候输出结果
			modelAndView.setViewName("modifyPwdAdmin");
			modelAndView.addObject("error1", "当前密码为空");
		}
		return modelAndView;

	}

	@RequestMapping(value = "/myInfo", method = RequestMethod.GET)
	public ModelAndView myInfoPage() {
		ModelAndView modelAndView = new ModelAndView();
		OaUser admin = (OaUser) session.getAttribute("admin");

		String companyLogo = admin.getCompanyLogo();
		if(companyLogo!=null&&!"".equals(companyLogo)) {
			String newImg = Constants.SUBJECT_PATH + "" + companyLogo;
			admin.setCompanyLogo(newImg);
		}
		String licenseImg = admin.getLicenseImg();
//		System.out.println("licenseImg：" + licenseImg);
		if (licenseImg != null && !"".equals(licenseImg)) {
			String newImg = Constants.SUBJECT_PATH + "" + licenseImg;
			admin.setLicenseImg(newImg);
//			System.out.println("licenseImg：" + newImg);
		}
		String cardImgFont = admin.getCardImgFont();
		if (cardImgFont != null && !"".equals(cardImgFont.trim())) {
			String newImg = Constants.SUBJECT_PATH + "" + cardImgFont;
			admin.setCardImgFont(newImg);
//			System.out.println("licenseImg：" + newImg);
		}
		String cardImgBack = admin.getCardImgBack();
		if (cardImgBack != null && !"".equals(cardImgBack.trim())) {
			String newImg = Constants.SUBJECT_PATH + "" + cardImgBack;
			admin.setCardImgBack(newImg);
//			System.out.println("licenseImg：" + newImg);
		}

		modelAndView.addObject("oaUser", admin);
		modelAndView.setViewName("basicInfo");
		return modelAndView;
	}

	@RequestMapping(value = "/myInfo", method = RequestMethod.POST)
	public ModelAndView myInfoForm( OaUser  admin,
			HttpServletRequest request,
			@RequestParam(required=false) CommonsMultipartFile companyLogoImg
			) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();

		String phone = admin.getPhone();
		if (phone.length() != 11) {
			modelAndView.addObject("error", "手机号长度错误");
			modelAndView.setViewName("redirect:/admin/myInfo");
			return modelAndView;
		}
		String filename = companyLogoImg.getOriginalFilename();
		System.out.println("filename:"+filename);
		if(filename!=null&&!"".equals(filename)) {
			String imgAddr = FileUtil.uploadImage(request, companyLogoImg, "other");
			if(imgAddr!=null&&!"".equals(imgAddr)) {
				admin.setCompanyLogo(imgAddr);
			}
		}


		Integer flag = oaUserService.modify(admin);
		if (flag < 0) {
			modelAndView.addObject("msg", "修改错误");
		}else {
			modelAndView.addObject("msg", "修改成功");
		}
		modelAndView.setViewName("redirect:/admin/myInfo");
		return modelAndView;
	}

	@RequestMapping("/test")
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("aaa");
		return modelAndView;
	}

	@RequestMapping("/parseLicence")
	@ResponseBody
	public Result parseLicence(HttpServletRequest request, MultipartFile file) {
		Result result = new Result();
		CommonsMultipartFile newfile = (CommonsMultipartFile) file;
		String filename = newfile.getOriginalFilename();

		OaUser oaUser = (OaUser) session.getAttribute("admin");
		if (filename != null && !"".equals(filename)) {
			String uploadImgAddr = FileUtil.uploadImage(request, newfile, "other");
			if (uploadImgAddr != null) {
				oaUser.setLicenseImg(uploadImgAddr);

				Integer flag = oaUserService.modify(oaUser);
				if (flag < 0) {
					System.out.println("修改错误");
				}
				String parseLicense = OcrUtil.parseLicense(Constants.SUBJECT_PATH + uploadImgAddr);
				System.out.println("parseLicense:" + parseLicense);
				result.setData(parseLicense);
			}
		}
		return result;
	}
	
	@RequestMapping("/parseCardFont")
	@ResponseBody
	public Result parseCardFont(HttpServletRequest request, MultipartFile cardFont) {
		Result result = new Result();
		CommonsMultipartFile newfile = (CommonsMultipartFile) cardFont;
		String filename = newfile.getOriginalFilename();

		OaUser oaUser = (OaUser) session.getAttribute("admin");
		if (filename != null && !"".equals(filename)) {
			String uploadImgAddr = FileUtil.uploadImage(request, newfile, "other");
			if (uploadImgAddr != null) {
				oaUser.setCardImgFont(uploadImgAddr);
				Integer flag = oaUserService.modify(oaUser);
				if (flag < 0) {
					System.out.println("修改错误");
				}
				String parseLicense = OcrUtil.parseIdCard(Constants.SUBJECT_PATH + uploadImgAddr);
				System.out.println("parseCardFont:" + parseLicense);
				result.setData(parseLicense);
			}
		}
		return result;
	}
	@RequestMapping("/parseCardBack")
	@ResponseBody
	public Result parseCardBack(HttpServletRequest request, MultipartFile cardBack) {
		Result result = new Result();
		CommonsMultipartFile newfile = (CommonsMultipartFile) cardBack;
		String filename = newfile.getOriginalFilename();

		OaUser oaUser = (OaUser) session.getAttribute("admin");
		if (filename != null && !"".equals(filename)) {
			String uploadImgAddr = FileUtil.uploadImage(request, newfile, "other");
			if (uploadImgAddr != null) {
				oaUser.setCardImgBack(uploadImgAddr);

				Integer flag = oaUserService.modify(oaUser);
				if (flag < 0) {
					System.out.println("修改错误");
				}
				String parseLicense = OcrUtil.parseIdCard(Constants.SUBJECT_PATH + uploadImgAddr);
				System.out.println("parseLicense:" + parseLicense);
				JSONObject parseObject = JSON.parseObject(parseLicense);
				Map<String, Object> map = (Map) parseObject;
				result.setData(parseLicense);
			}
		}
		return result;
	}

}
