/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	LoginForAdminRequest.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.service.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhuchao
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)//忽略位置属性
public class LoginForAdminRequest {
  @ApiModelProperty(value = "用户身份表示，如编码或手机号")
  private String principle;
  @ApiModelProperty(value = "密码")
  private String password;
}
