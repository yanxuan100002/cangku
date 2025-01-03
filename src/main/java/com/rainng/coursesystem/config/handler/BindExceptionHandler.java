package com.rainng.coursesystem.config.handler;

import com.rainng.coursesystem.model.constant.HttpStatusCode;
import com.rainng.coursesystem.model.vo.response.ParameterErrorVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//这个BindExceptionHandler类是一个全局异常处理器，
// 使用了Spring的@RestControllerAdvice注解，用于捕获并处理特定的异常。
// 在这个例子中，它特别处理了MethodArgumentNotValidException异常，这种异常通常会在使用Spring的@Validated注解进行参数校验时抛出。
@RestControllerAdvice
public class BindExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVO handleBindException(MethodArgumentNotValidException ex, HttpServletResponse response) {
        List<ParameterErrorVO> errorVOList = new ArrayList<>();

        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        for (ObjectError error : errorList) {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorVOList.add(new ParameterErrorVO(fieldName, message));
        }

        errorVOList.sort(Comparator.comparing(ParameterErrorVO::getFieldName));
        String message = "请求参数无效";
        if (errorVOList.size() > 0) {
            ParameterErrorVO firstError = errorVOList.get(0);
            message += ": " + firstError.getFieldName() + "-" + firstError.getMessage();
        }

        response.setStatus(HttpStatusCode.BAD_REQUEST);
        return new ResultVO(ResultVO.INVALID_PARAMETER, message, errorVOList);
    }
}
