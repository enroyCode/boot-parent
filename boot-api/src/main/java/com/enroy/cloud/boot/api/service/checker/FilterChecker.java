/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	TokenFilterChecker.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.service.checker;

import javax.servlet.ServletException;

/**
 * @author zhuchao
 */
public interface FilterChecker {
  /**
   * 是否忽略指定路径
   *
   * @param path
   * @return
   */
  boolean ignorePath(String path);

  void check() throws ServletException;
}
