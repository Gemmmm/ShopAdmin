package com.tc.shop.model;

import java.util.List;
import java.util.Map;

/*
 *封装的返回的结果类
 * */
public class Result {
    private String code;
    private String message;
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setSuccess() {
        this.code = "200";
        this.message = "数据操作成功";
    }
    public void setSuccess(String message) {
        this.code = "200";
        this.message = message;
    }
    public void setFail(){
        this.code = "-2";
        this.message = "数据操作失败";
    }
    public void setFail(String  message){
        this.code = "-2";
        this.message = message;
    }
}
