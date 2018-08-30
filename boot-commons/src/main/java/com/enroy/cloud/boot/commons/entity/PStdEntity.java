/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	PStdEntity.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.commons.entity;

/**
 * @author zhuchao
 */
public class PStdEntity extends PBaseEntity {
  private POperateInfo createInfo;
  private POperateInfo lastModifyInfo;

  public POperateInfo getCreateInfo() {
    return createInfo;
  }

  public void setCreateInfo(POperateInfo createInfo) {
    this.createInfo = createInfo;
  }

  public POperateInfo getLastModifyInfo() {
    return lastModifyInfo;
  }

  public void setLastModifyInfo(POperateInfo lastModifyInfo) {
    this.lastModifyInfo = lastModifyInfo;
  }
}
