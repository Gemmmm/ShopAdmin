package com.tc.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.common.utils.DateUtil;
import com.tc.shop.model.Result;
import com.tc.shop.util.Constants;
import com.tc.shop.util.FileUtil;
import com.tc.shop.util.ImageUploadUtil;
import com.tc.shop.util.OssUtil;

@Controller
@RequestMapping("file")
public class FileController {


    /**
     * 已经在各页面重新定位文件上传的路径
     *
     * @param request
     * @param response
     * @return 文件上传成功
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
        String type = request.getParameter("type");
        System.out.println("type::" + type);
        String directoryName = null;
        if (type != null && !"".equals(type)) {
            directoryName = type;
        } else {
            directoryName = "other";
        }
        try {
            ImageUploadUtil.ckeditor(request, response, directoryName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * img文件上传
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public Result uploadApp(HttpServletRequest request,
                            @RequestParam(required = false) MultipartFile file,
                            @RequestParam String path
    ) {

        Result result = new Result();
        try {
            String filename = file.getOriginalFilename();
            if (filename != null && !"".equals(filename)) {
                String uploadImgAddr = FileUtil.uploadImage(request,file, path);
                if (uploadImgAddr != null) {
                    result.setData(uploadImgAddr);
                    result.setSuccess("上传成功");
                    return result;
                }
            }
        } catch (Exception e) {
            System.out.println("上传失败！");
        }
        result.setFail("上传失败");
        return result;
    }


}
