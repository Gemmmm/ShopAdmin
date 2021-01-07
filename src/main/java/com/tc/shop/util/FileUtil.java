package com.tc.shop.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import com.tc.wx.util.DateUtil;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

public class FileUtil {


	public static String uploadImage(HttpServletRequest request,MultipartFile file,  String folderName) {

		//随机名处理
		try {
			String tomcatPath = request.getSession().getServletContext().getRealPath("/upload");
			String filename = file.getOriginalFilename();
			if (file != null && !"".equals(filename)) {
				String suffix = filename.substring(filename.lastIndexOf("."));
				InputStream inputStream = file.getInputStream();
				File folder = new File(tomcatPath);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				File targetFile = new File(folder.getPath() + "/" + filename);
				if (!targetFile.exists()) {
					targetFile.createNewFile();
				}
				byte[] buffer = new byte[8192];
				int bytesRead = 0;
				FileOutputStream out = new FileOutputStream(targetFile);
				while ((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				inputStream = new FileInputStream(targetFile);
				filename = DateUtil.thisDayBeginNew() + "/" + System.currentTimeMillis() + suffix;
				String flag = OssUtil.uploadFile(inputStream, folderName, filename);
				if (flag != null && !"".equals(flag)) {
					return folderName + "/" + filename;
				}
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
}
