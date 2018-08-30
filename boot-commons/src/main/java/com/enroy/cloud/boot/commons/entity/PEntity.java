/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	PEntity.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.commons.entity;

import com.enroy.cloud.boot.commons.biz.HasUuid;

/**
 * @author zhuchao
 */
public class PEntity implements HasUuid {
  private String uuid;

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
}
