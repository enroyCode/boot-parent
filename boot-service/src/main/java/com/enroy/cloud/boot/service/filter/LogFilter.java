/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	LogFilter.java
 * 模块说明：
 * 修改历史：
 * 2019/5/8 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.filter;

import com.enroy.cloud.boot.api.service.checker.FilterUtils;
import com.enroy.cloud.boot.service.filter.log.LogFilterChecker;
import com.enroy.cloud.boot.service.filter.log.LoggerHttpServletRequestWrapper;
import com.enroy.cloud.boot.service.filter.log.LoggerHttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * @author zhuchao
 */
@Slf4j
@Order(2)
@WebFilter(filterName = "logFilter", urlPatterns = "/*")
@Component
public class LogFilter extends OncePerRequestFilter implements ApplicationContextAware {
  private ApplicationContext applicationContext;

  private static final String UNKNOWN = "unknown";
  private static final String SEPARATOR = ",";
  private static final String TRACE_ID = "trace_id";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    LoggerHttpServletRequestWrapper requestWrapper = new LoggerHttpServletRequestWrapper(request);
    LoggerHttpServletResponseWrapper responseWrapper = new LoggerHttpServletResponseWrapper(response);
    String pathInfo;
    String contextPath = applicationContext.getEnvironment().getProperty("server.contextPath");//不使用@value注解的原因在于server.contextPath若未配置会报错。
    if (StringUtils.isNotBlank(contextPath)) {
      pathInfo = requestWrapper.getRequestURI().replaceAll("//", "/").replace(contextPath, "");
    } else {
      pathInfo = requestWrapper.getRequestURI().replaceAll("//", "/");
    }
    String url = request.getRequestURL().toString();
    LogFilterChecker checker = applicationContext.getBean(LogFilterChecker.BEAN_ID, LogFilterChecker.class);
    if (checker != null && checker.ignorePath(pathInfo)) {
      filterChain.doFilter(request, response);
      return;
    }
    printRequest(requestWrapper, url);
    filterChain.doFilter(requestWrapper, responseWrapper);
    printResponse(responseWrapper, url);
  }

  private void printRequest(LoggerHttpServletRequestWrapper request, String url) {
    StringBuilder sb = new StringBuilder();
    sb.append("\r\n===========================================================================\r\n");
    sb.append("spms-dly-transfer request Message: " + "\r\n");
    sb.append("trace_id: ").append(MDC.get(FilterUtils.TRACE_ID)).append("\r\n");
    sb.append("url: ").append(url).append("\r\n");
    sb.append("headers: ").append(getHeaders(request)).append("\r\n");
    sb.append("queryString: ").append(request.getQueryString()).append("\r\n");
    if (request.getMethod().equals("POST")) {
      sb.append("body: ").append(FilterUtils.streamToString(request.getInputStream())).append("\r\n");
    }
    sb.append("===========================================================================");
    log.info(sb.toString());
  }

  private void printResponse(LoggerHttpServletResponseWrapper response, String url) {
    StringBuilder sb = new StringBuilder();
    sb.append("\r\n===========================================================================\r\n");
    sb.append("spms-dly-transfer response Message: " + "\r\n");
    sb.append("trace_id: ").append(response.getHeader(FilterUtils.TRACE_ID)).append("\r\n");
    sb.append("url: ").append(url).append("\r\n");
    sb.append("body: ").append(new String(response.getBody(), StandardCharsets.UTF_8)).append("\r\n");
    sb.append("===========================================================================");
    log.info(sb.toString());
  }

  private String getHeaders(LoggerHttpServletRequestWrapper request) {
    Enumeration<String> headerNames = request.getHeaderNames();
    StringBuilder sb = new StringBuilder("[");
    while (headerNames.hasMoreElements()) {
      String name = headerNames.nextElement();
      String value = request.getHeader(name);
      sb.append(" '").append(name).append(":").append(request.getHeader(name)).append("'\r\n");
    }
    sb.append("]\r\n");
    return sb.toString();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }

}
