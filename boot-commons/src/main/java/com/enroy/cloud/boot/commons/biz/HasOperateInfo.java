/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	HasOperateInfo.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.commons.biz;

import java.util.Date;

/**
 * @author zhuchao
 */
public interface HasOperateInfo {
  /**
   * 操作发生时间，通常来说是用户（人）通过界面发起操作请求的时刻。默认取值为对象创建时间。
   */
  Date getTime();

  /**
   * @see #getTime()
   * @throws UnsupportedOperationException
   *           如果具体类不支持修改操作。
   */
  void setTime(Date time) throws UnsupportedOperationException;

  /**
   * 操作人信息。
   */
  String getOperator();

  /**
   * @see #getOperator()
   * @throws UnsupportedOperationException
   *           如果具体类不支持修改操作。
   */
  void setOperator(String operator) throws UnsupportedOperationException;
}
