/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	TokenService.java
 * 模块说明：
 * 修改历史：
 * 2018/8/28 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.service.token;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuchao
 */
public interface TokenService {
  public static final String BEAN_ID = "dcommons.tokenService";

  /**
   * 在当前请求中，签发一个token
   *
   * @param request
   * @param response
   * @throws AuthenticationException
   */
  void issue(HttpServletRequest request, HttpServletResponse response)
          throws AuthenticationException;

  /**
   * 在当前请求中，签发一个token
   *
   * @param request
   * @param response
   * @throws AuthenticationException
   */
  void issue(HttpServletRequest request, HttpServletResponse response, int expSeconds)
          throws AuthenticationException;

  /**
   * 清除当前请求中的token信息
   *
   * @param request
   * @param response
   */
  void erase(HttpServletRequest request, HttpServletResponse response);

  /**
   * 根据{@TokenConfig} 配置，刷新token
   *
   * @param request
   * @param response
   * @throws AuthenticationException
   */
  void refresh(HttpServletRequest request, HttpServletResponse response)
          throws AuthenticationException;

  /**
   * 验证当前请求中的token信息
   *
   * @param request
   * @throws AuthenticationException
   */
  void verify(HttpServletRequest request) throws AuthenticationException;
}
