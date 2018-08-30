/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	POperateInfo.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.commons.entity;

import com.enroy.cloud.boot.commons.biz.HasOperateInfo;

import java.util.Date;

/**
 * @author zhuchao
 */
public class POperateInfo implements HasOperateInfo {
  private Date time;
  private String operator;

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
}
