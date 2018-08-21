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

import com.alibaba.fastjson.JSON;
import com.enroy.cloud.boot.api.biz.ActionResult;
import com.enroy.cloud.boot.api.exception.AuthenticationException;
import com.enroy.cloud.boot.api.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
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

  @ResponseBody
  @ExceptionHandler({BusinessException.class})
  @ResponseStatus(OK)
  public ActionResult BusinessExceptionHandler(HttpServletRequest req, Exception ex) {
    final ActionResult response = logFailed(req, Level.WARN, ex);
    print(response, Level.WARN);
    return response;
  }

  @ResponseBody
  @ResponseStatus(UNAUTHORIZED)
  @ExceptionHandler({
          AuthenticationException.class})
  public ActionResult unauthorizedExceptionHandler(HttpServletRequest req, Exception ex) {
    final ActionResult response = logFailed(req, Level.WARN, ex);
    print(response, Level.WARN);
    return response;
  }

  private ActionResult logFailed(HttpServletRequest req, Level level, Throwable tb) {
    final ActionResult response = ActionResult.fail(tb.getLocalizedMessage());

    if (Level.ERROR.equals(level)) {
      log.error(format("请求%s失败", req.getRequestURI()), tb);
    } else if (Level.WARN.equals(level)) {
      log.warn(format("请求%s失败", req.getRequestURI()), tb);
    } else {
      throw new IllegalArgumentException("参数level只支持ERROR和WARN");
    }
    return response;
  }

  private void print(ActionResult response, Level level) {
    if (Level.ERROR.equals(level)) {
      log.error(JSON.toJSONString(response));
    } else if (Level.WARN.equals(level)) {
      log.warn(JSON.toJSONString(response));
    } else {
      throw new IllegalArgumentException("参数level只支持ERROR和WARN");
    }
  }

}
