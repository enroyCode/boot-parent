/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	LogRequestBodyAdvice.java
 * 模块说明：
 * 修改历史：
 * 2019/5/7 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.text.MessageFormat;

/**
 * controller增强器，针对@RequestBody有效
 *
 * @author zhuchao
 */
@Slf4j
@ControllerAdvice
public class LogRequestBodyAdvice implements RequestBodyAdvice {

  @Override
  public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    return true;
  }

  @Override
  public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    Method method = parameter.getMethod();
    log.info("{}.{}", method.getDeclaringClass().getSimpleName(), method.getName());
    return body;
  }

  @Override
  public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
    return inputMessage;
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
    Method method = parameter.getMethod();
    HttpHeaders headers = inputMessage.getHeaders();
    StringBuilder sb = new StringBuilder();
    sb.append("\r\n===========================================================================\r\n");
    sb.append("request Message: " + "\r\n");
    sb.append(MessageFormat.format("method:{0}.{1} ", method.getDeclaringClass().getSimpleName(), method.getName())).append("\r\n");
    sb.append("headers: ").append(inputMessage.getHeaders()).append("\r\n");
    sb.append("body: ").append(JSON.toJSONString(body)).append("\r\n");
    sb.append("===========================================================================");
    log.info(sb.toString());
    log.info("{}.{}:{}", method.getDeclaringClass().getSimpleName(), method.getName(), JSON.toJSONString(body));
    return body;
  }
}
