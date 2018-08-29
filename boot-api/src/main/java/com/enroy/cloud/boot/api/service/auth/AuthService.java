/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	AuthService.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.service.auth;

import com.enroy.cloud.boot.api.biz.employee.Employee;

/**
 * @author zhuchao
 */
public interface AuthService {
  Employee authenticate(String principle, String password);
}
