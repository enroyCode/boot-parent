/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	BaseEntity.java
 * 模块说明：
 * 修改历史：
 * 2018/8/28 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.commons.biz;

/**
 * @author zhuchao
 */
public class BaseEntity extends Entity implements HasUuid {
  private String code;
  private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
