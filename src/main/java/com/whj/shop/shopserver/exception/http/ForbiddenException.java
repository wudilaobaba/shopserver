package com.whj.shop.shopserver.exception.http;

/**
 * 没有访问权限异常
 */
public class ForbiddenException extends HttpException{
    public ForbiddenException(int code){
        this.httpStatusCode = 403;
        this.code = code;
    }
}
