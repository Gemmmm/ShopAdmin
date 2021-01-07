package com.tc.shop.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import com.tencentcloudapi.ocr.v20181119.models.BizLicenseOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.BizLicenseOCRResponse;

public class OcrUtil {
	public static void main(String[] args) {
	//String parseLicense = parseLicense("http://customersystemimg.tiancekj.com/other/2020-08-22/1598063493736.jpg");
		String parseLicense = parseIdCardByBase64("test");
		if (parseLicense != null && !"".equals(parseLicense)) {
			System.out.println(parseLicense);
			JSONObject jsonObject = JSON.parseObject(parseLicense);
		}

	}

	
	
	public static String parseIdCardByBase64(String imgBase) {
		String result = null;
		try {

			Credential cred = new Credential(Constants.OcrSecretId, Constants.OcrSecretKey);

			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setEndpoint("ocr.tencentcloudapi.com");

			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);

			OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);

			String params = "{\"ImageBase64\":\"" + imgBase + "\"}";
			IDCardOCRRequest req = IDCardOCRRequest.fromJsonString(params, IDCardOCRRequest.class);

			IDCardOCRResponse resp = client.IDCardOCR(req);
			result = IDCardOCRResponse.toJsonString(resp);
			System.out.println(result);
		} catch (TencentCloudSDKException e) {
		}
		return result;
	}
	public static String parseIdCard(String imgUrl) {
		String result = null;
		try {
			
			Credential cred = new Credential(Constants.OcrSecretId, Constants.OcrSecretKey);
			
			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setEndpoint("ocr.tencentcloudapi.com");
			
			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);
			
			OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);
			
			String params = "{\"ImageUrl\":\"" + imgUrl + "\"}";
			IDCardOCRRequest req = IDCardOCRRequest.fromJsonString(params, IDCardOCRRequest.class);
			
			IDCardOCRResponse resp = client.IDCardOCR(req);
			result = IDCardOCRResponse.toJsonString(resp);
			System.out.println(result);
		} catch (TencentCloudSDKException e) {
		}
		return result;
	}

	public static String parseLicense(String imgUrl) {
		String result = null;
		try {
			Credential cred = new Credential(Constants.OcrSecretId, Constants.OcrSecretKey);

			HttpProfile httpProfile = new HttpProfile();
			httpProfile.setEndpoint("ocr.tencentcloudapi.com");

			ClientProfile clientProfile = new ClientProfile();
			clientProfile.setHttpProfile(httpProfile);

			OcrClient client = new OcrClient(cred, "ap-shanghai", clientProfile);

			String params = "{\"ImageUrl\":\"" + imgUrl + "\"}";
			BizLicenseOCRRequest req = BizLicenseOCRRequest.fromJsonString(params, BizLicenseOCRRequest.class);

			BizLicenseOCRResponse resp = client.BizLicenseOCR(req);
			result = BizLicenseOCRResponse.toJsonString(resp);

		} catch (TencentCloudSDKException e) {

		}
		System.out.println(result);
		return result;

	}

}