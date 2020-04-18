/**
 * @作者 7七月
 * @微信公号 林间有风
 * @开源项目 $ http://talelin.com
 * @免费专栏 $ http://course.talelin.com
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020-03-22 13:49
 */
package com.whj.shop.shopserver.exception;


import com.whj.shop.shopserver.exception.http.HttpException;

public class UpdateSuccess extends HttpException {
    public UpdateSuccess(int code){
        this.httpStatusCode = 200;
        this.code = code;
    }
//    201 202 204
    //    201创建成功
    //    202更新成功 - 已经接收到了请求，但是请求结果没有完成
    //    204删除成功，但是不会返回结果（有结果也不会返回）
    //    建议：更新和删除都用200
}
