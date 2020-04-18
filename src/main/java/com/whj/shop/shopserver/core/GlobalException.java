package com.whj.shop.shopserver.core;

import com.whj.shop.shopserver.core.configuration.ExceptionCodeConfigertion;
import com.whj.shop.shopserver.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;


//系统会自动检测以下异常：
@ControllerAdvice
public class GlobalException {
    @Autowired
    private ExceptionCodeConfigertion codeConfigertion;

    //处理未知异常，只要全局有【Exception】异常就会触发到这个函数
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest req, Exception e){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        System.out.println(e);
        UnifyResponse message = new UnifyResponse(9999,"服务器异常",method+" "+requestUrl);
        return message;
    }


    //处理已知异常，只要全局有【HttpException的子类】异常就会触发到这个函数
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest req, HttpException e){ //处理未知异常，只要全局有异常就会触发到这个函数
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        UnifyResponse message = new UnifyResponse(e.getCode(),codeConfigertion.getMessage(e.getCode()),method+" "+requestUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        ResponseEntity<UnifyResponse> r = new ResponseEntity<>(message,headers,httpStatus);
        return r;
    }


    //处理自定义注解校验异常： - 仅针对POST请求的body体的异常处理
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST) //参数错误是400
    @ResponseBody
    public UnifyResponse handleBeanValidation(HttpServletRequest req,MethodArgumentNotValidException e){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = formatAllErrorMessages(errors);
        return new UnifyResponse(10001,message,method+" "+requestUrl);

    }

    //处理自定义注解校验异常： - 仅针对GET请求的URL异常处理
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST) //参数错误是400
    @ResponseBody
    public UnifyResponse handleConstraintException(HttpServletRequest req, ConstraintViolationException e){
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();
        String message = e.getMessage();
        for(ConstraintViolation error:e.getConstraintViolations()){
            ConstraintViolation a = error;
        }
        return new UnifyResponse(10001,message,method+" "+requestUrl);
    }





    private String formatAllErrorMessages(List<ObjectError> errors){
        StringBuffer errMessage = new StringBuffer();
        errors.forEach(error->
                errMessage.append(error.getDefaultMessage()).append(';')
        );
        return errMessage.toString();
    }
}
