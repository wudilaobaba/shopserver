package com.whj.shop.shopserver.core;

import com.whj.shop.shopserver.exception.CreateSuccess;

public class UnifyResponse {
    private int code;
    private String message;
    private  String request;
    //一定要加get方法
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public UnifyResponse(int code, String message, String request){
        this.code = code;
        this.message = message;
        this.request =request;
    }
    public static void createSuccess(int code) {
        throw new CreateSuccess(code);
    }
}
