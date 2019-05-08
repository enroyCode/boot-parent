/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	LogFilter.java
 * 模块说明：
 * 修改历史：
 * 2019/5/8 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.filter.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
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
public class LogFilter extends OncePerRequestFilter {
  private static final String UNKNOWN = "unknown";
  private static final String SEPARATOR = ",";
  private static final String TRACE_ID = "trace_id";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    LoggerHttpServletRequestWrapper requestWrapper = new LoggerHttpServletRequestWrapper(request);
    LoggerHttpServletResponseWrapper responseWrapper = new LoggerHttpServletResponseWrapper(response);
    String ipAddress = getIpAddress(request);
    String url = request.getRequestURL().toString();
    printRequest(requestWrapper, url, ipAddress);
    filterChain.doFilter(requestWrapper, responseWrapper);
    printResponse(responseWrapper, url, ipAddress);
  }

  private static String getIpAddress(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    if (StringUtils.isNotBlank(ip) && ip.contains(SEPARATOR)) {
      return ip.split(SEPARATOR)[0];
    } else {
      return ip;
    }
  }

  private void printRequest(LoggerHttpServletRequestWrapper request, String url, String ipAddress) {
    StringBuilder sb = new StringBuilder();
    sb.append("\r\n===========================================================================\r\n");
    sb.append("request Message: " + "\r\n");
    sb.append("trace_id: ").append(MDC.get(TRACE_ID)).append("\r\n");
    sb.append("url: ").append(url).append("\r\n");
    sb.append("client_ip: ").append(ipAddress).append("\r\n");
    sb.append("headers: ").append(getHeaders(request)).append("\r\n");
    sb.append("queryString: ").append(request.getQueryString()).append("\r\n");
    if (request.getMethod().equals("POST")) {
      sb.append("body: ").append(StreamUtils.streamToString(request.getInputStream())).append("\r\n");
    }
    sb.append("===========================================================================");
    log.info(sb.toString());
  }

  private void printResponse(LoggerHttpServletResponseWrapper response, String url, String ipAddress) {
    StringBuilder sb = new StringBuilder();
    sb.append("\r\n===========================================================================\r\n");
    sb.append("response Message: " + "\r\n");
    sb.append("trace_id: ").append(response.getHeader(TRACE_ID)).append("\r\n");
    sb.append("url: ").append(url).append("\r\n");
    sb.append("client_ip: ").append(ipAddress).append("\r\n");
    sb.append("body: ").append(new String(response.getBody(), StandardCharsets.UTF_8)).append("\r\n");
    sb.append("===========================================================================");
    log.info(sb.toString());
  }

  private String getHeaders(LoggerHttpServletRequestWrapper request) {
    Enumeration<String> headerNames = request.getHeaderNames();
    StringBuilder sb = new StringBuilder();
    while (headerNames.hasMoreElements()) {
      String name = headerNames.nextElement();
      String value = request.getHeader(name);
      sb.append("'").append(name).append(":").append(request.getHeader(name)).append("';");
    }
    return sb.toString();
  }

//  private String getResponseBody(){
//
//  }
}
