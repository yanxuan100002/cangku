package com.rainng.coursesystem.config.aop;

import com.rainng.coursesystem.model.constant.HttpStatusCode;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
//在控制器（Controller）层的方法执行完毕并返回ResultVO类型的响应对象时，检查该对象的code属性。
@Aspect
@Component
public class ResultFailedCodeAspect {
    @Pointcut("execution(public com.rainng.coursesystem.model.vo.response.ResultVO " +
            "com.rainng.coursesystem.controller..*.*(..))")
    public void controllerResult() {
    }

    @AfterReturning(value = "controllerResult()", returning = "result")
    public Object afterReturning(ResultVO result) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return result;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return result;
        }

        if (result.getCode() == ResultVO.FAIL) {
            response.setStatus(HttpStatusCode.NOT_ACCEPTABLE);
        }

        return result;
    }
}
