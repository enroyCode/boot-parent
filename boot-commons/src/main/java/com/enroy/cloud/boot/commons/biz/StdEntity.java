/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	PStdEntity.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.commons.biz;

/**
 * @author zhuchao
 */
public class StdEntity extends BaseEntity {
  private OperateInfo createInfo;
  private OperateInfo lastModifyInfo;

  public OperateInfo getCreateInfo() {
    return createInfo;
  }

  public void setCreateInfo(OperateInfo createInfo) {
    this.createInfo = createInfo;
  }

  public OperateInfo getLastModifyInfo() {
    return lastModifyInfo;
  }

  public void setLastModifyInfo(OperateInfo lastModifyInfo) {
    this.lastModifyInfo = lastModifyInfo;
  }
}
