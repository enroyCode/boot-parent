/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	BaseEntity.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.biz;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuchao
 */
@Setter
@Getter
public class BaseEntity {
  private String uuid;
  private String code;
  private String name;
  private OperateInfo createInfo;
  private OperateInfo lastModifyInfo;
}
