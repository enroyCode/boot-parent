package com.enroy.cloud.boot.service.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * trace过滤器
 *
 * @author zhuchao
 */
@Order(1)
@WebFilter(filterName = "traceFilter", urlPatterns = "/*")
@Component
public class TraceFilter implements Filter {

  private static final String UNKNOWN = "unknown";
  private static final String SEPARATOR = ",";
  private static final String TRACE_ID = "trace_id";
  private static final String CLIENT_IP = "client_ip";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String traceId = request.getHeader(TRACE_ID);
    if (StringUtils.isBlank(traceId)) {
      traceId = UUID.randomUUID().toString();
    }
    MDC.put(CLIENT_IP, getIpAddress(request));
    MDC.put(TRACE_ID, traceId);
    response.setHeader(TRACE_ID, traceId);

    filterChain.doFilter(request, servletResponse);
  }

  @Override
  public void destroy() {

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

}
