/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	ApiOriginFilter.java
 * 模块说明：
 * 修改历史：
 * 2018/11/8 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.filter;

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

/**
 * 处理跨域
 */
@Order(0)
@WebFilter(filterName = "apiOriginFilter", urlPatterns = "/*")
@Component
public class ApiOriginFilter implements Filter {
  public ApiOriginFilter() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    res.addHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));//前端需求携带cookie时，后端不能匹配'*'
    res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");//允许接收请求
    res.addHeader("Access-Control-Allow-Headers", "Content-Type");//前端请求类型
    res.addHeader("Access-Control-Allow-Headers", "X-Requested-With");//仿造ajax请求
    res.addHeader("Access-Control-Allow-Headers", "trace_id");
    res.addHeader("Access-Control-Expose-Headers", "trace_id");//允许获取额外的header
    res.addHeader("Access-Control-Allow-Credentials", "true");

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }
}
