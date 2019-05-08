/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	spms
 * 文件名：	LogFilterChecker.java
 * 模块说明：
 * 修改历史：
 * 2019/5/8 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.filter.log;

import com.enroy.cloud.boot.api.service.checker.FilterChecker;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;

/**
 * @author zhuchao
 */
@Component(LogFilterChecker.BEAN_ID)
public class LogFilterChecker implements FilterChecker {
  public static final String BEAN_ID = "logFilterChecker";

  @Override
  public boolean ignorePath(String path) {
    return path.contains("/about") || path.contains("/auth/login") || path.contains("swagger") || path.contains("/info") || path.contains("/health")
            || path.endsWith("/v2/api-docs");
  }

  @Override
  public void check() throws ServletException {

  }
}
