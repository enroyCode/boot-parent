/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	RestExceptionHandler.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.execption;

import com.enroy.cloud.boot.api.biz.ActionResult;
import com.enroy.cloud.boot.api.exception.AuthenticationException;
import com.enroy.cloud.boot.api.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 全局异常捕获处理器handler
 *
 * @author zhuchao
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler {
  public static final String SC_LOGIN_OUT = "1001";

  @ResponseBody
  @ExceptionHandler({BusinessException.class})
  @ResponseStatus(OK)
  public ActionResult BusinessExceptionHandler(HttpServletRequest req, Exception ex) {
    ActionResult response = ActionResult.fail(ex.getLocalizedMessage());
    if (ex instanceof BusinessException) {
      response.setCode(((BusinessException) ex).getCode());
    }
    log.error(format("请求%s失败", req.getRequestURI()), ex);
    return response;
  }

  @ResponseBody
  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler({AuthenticationException.class})
  public ActionResult unauthorizedExceptionHandler(HttpServletRequest req, Exception ex) {
    ActionResult response = ActionResult.fail(ex.getLocalizedMessage());
    response.setCode(SC_LOGIN_OUT);//1001定义为登录异常
    log.warn(format("请求%s失败", req.getRequestURI()), ex);
    return response;
  }

}
