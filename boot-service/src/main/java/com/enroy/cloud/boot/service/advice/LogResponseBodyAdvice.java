/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	LogResponseBodyAdvice.java
 * 模块说明：
 * 修改历史：
 * 2019/5/7 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * controller增强器，针对@ResponseBody有效
 *
 * @author zhuchao
 */
@Slf4j
@ControllerAdvice
public class LogResponseBodyAdvice implements ResponseBodyAdvice {
  private static final String TRACE_ID = "trace_id";

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  @Override
  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
    Method method = returnType.getMethod();
    String url = request.getURI().toASCIIString();
    String sb = "\r\n===========================================================================\r\n" +
            "response Message: " + "\r\n" +
            "trace_id: " + MDC.get(TRACE_ID) + "\r\n" +
            "url: " + request.getURI().toASCIIString() + "\r\n" +
            "headers: " + response.getHeaders().toString() + "\r\n" +
            "body: " + JSON.toJSONString(body) + "\r\n" +
            "===========================================================================";
    log.info(sb);
    return body;
  }
}
