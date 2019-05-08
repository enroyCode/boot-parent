/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	AuthTokenFilterChecker.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.filter;

import com.enroy.cloud.boot.api.service.token.TokenFilterChecker;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

/**
 * 忽略路径
 *
 * @author zhuchao
 */
@Component
public class AuthTokenFilterChecker implements TokenFilterChecker {

  @Override
  public boolean ignorePath(String path) {
    return path.contains("/about") || path.contains("/auth/login") || path.contains("swagger") || path.contains("/info") || path.contains("/health")
            || path.endsWith("/v2/api-docs");
  }

  @Override
  public void check() throws ServletException {

  }
}
