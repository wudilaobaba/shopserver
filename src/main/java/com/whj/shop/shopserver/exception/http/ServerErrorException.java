package com.whj.shop.shopserver.exception.http;

public class ServerErrorException extends HttpException{
    public ServerErrorException(int code){
        this.httpStatusCode = 500;
        this.code = code;
    }
}
