/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	TokenFilter.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.filter;

import com.enroy.cloud.boot.api.service.token.TokenFilterChecker;
import com.enroy.cloud.boot.api.service.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author zhuchao
 */
@Slf4j
@Component
public class TokenFilter implements Filter, ApplicationContextAware {
  private ApplicationContext applicationContext;
  @Autowired
  private TokenService tokenService;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    String pathInfo;
    String contextPath = applicationContext.getEnvironment().getProperty("server.contextPath");
    if (StringUtils.isNotBlank(contextPath)) {
      pathInfo = httpRequest.getRequestURI().replaceAll("//", "/").replace(contextPath, "");
    } else {
      pathInfo = httpRequest.getRequestURI().replaceAll("//", "/").replace(contextPath, "");
    }
    Map<String, TokenFilterChecker> checkers = applicationContext.getBeansOfType(TokenFilterChecker.class);
    for (String key : checkers.keySet()) {
      TokenFilterChecker checker = checkers.get(key);
      if (checker.ignorePath(pathInfo)) {
        chain.doFilter(request, response);
        return;
      }
    }

    if (!httpRequest.getMethod().equals("OPTIONS")) {
      try {
        // 验证jwt
        tokenService.verify(httpRequest);

        // 刷新jwt
        tokenService.refresh(httpRequest, httpResponse);
      } catch (AuthenticationException e) {
        log.error("TokenFilter Error", e);
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        return;
      }

      for (String key : checkers.keySet()) {
        TokenFilterChecker checker = checkers.get(key);
        checker.check();
      }
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }

  public ApplicationContext getApplicationContext() {
    return applicationContext;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
