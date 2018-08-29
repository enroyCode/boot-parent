/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	Employee.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.biz.employee;

import com.enroy.cloud.boot.api.biz.commons.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuchao
 */
@Setter
@Getter
public class Employee extends BaseEntity {
  /** 商场 必填 */
  private String mart;
  /** 编码 */
  private String code;
  /** 名称 */
  private String name;
  /** 密码 */
  private String password;
  /** 是否是超级管理员（只读，服务不允许设置，超级管理员只能初始化） */
  private boolean superAdmin = Boolean.FALSE;
  /** 员工拥有的权限列表 */
//  private Set<String> permissions = new HashSet<>();
}
