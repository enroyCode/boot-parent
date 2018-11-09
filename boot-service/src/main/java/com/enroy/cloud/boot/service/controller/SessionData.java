/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	SessionData.java
 * 模块说明：
 * 修改历史：
 * 2018/11/9 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.controller;

import com.enroy.cloud.boot.api.biz.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhuchao
 */
@Data
@AllArgsConstructor
public class SessionData {
  private Employee user;
}
