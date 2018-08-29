/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	LoginForAdminResponse.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.service.auth;

import com.enroy.cloud.boot.api.biz.employee.Employee;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhuchao
 */
@Setter
@Getter
public class LoginForAdminResponse {
  @ApiModelProperty(value = "员工")
  private Employee employee;
}
