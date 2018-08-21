/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	AuthenticationException.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.exception;

import java.text.MessageFormat;

/**
 * 权限异常
 *
 * @author zhuchao
 */
public class AuthenticationException extends RuntimeException{
  private static final long serialVersionUID = -8296498773313651133L;
  public AuthenticationException() {
    // Do Nothing
  }

  public AuthenticationException(String pattern, Object... arguments) {
    super(MessageFormat.format(pattern, arguments));
  }

  public AuthenticationException(Throwable t) {
    super(t);
  }

  public AuthenticationException(Throwable t, String pattern, Object... arguments) {
    super(MessageFormat.format(pattern, arguments), t);
  }
}
