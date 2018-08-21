/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	BizException.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.exception;

import java.text.MessageFormat;

/**
 * 业务异常
 *
 * @author zhuchao
 */
public class BusinessException extends RuntimeException {
  private static final long serialVersionUID = 6002605632920657041L;

  public BusinessException() {
    // Do Nothing
  }

  public BusinessException(String pattern, Object... arguments) {
    super(MessageFormat.format(pattern, arguments));
  }

  public BusinessException(Throwable t) {
    super(t);
  }

  public BusinessException(Throwable t, String pattern, Object... arguments) {
    super(MessageFormat.format(pattern, arguments), t);
  }
}
