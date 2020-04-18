package com.whj.shop.shopserver.exception.http;

public class HttpException extends RuntimeException{//运行时异常，不用考虑编译时异常
    protected Integer code;
    protected Integer httpStatusCode = 500;

    public Integer getCode() {
        return code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
