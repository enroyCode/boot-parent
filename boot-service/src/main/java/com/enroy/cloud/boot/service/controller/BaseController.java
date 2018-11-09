/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	BaseController.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.controller;

import com.enroy.cloud.boot.api.biz.ActionResult;
import com.enroy.cloud.boot.api.biz.employee.Employee;
import com.enroy.cloud.boot.service.controller.auth.AuthToken;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.login.LoginException;

/**
 * @author zhuchao
 */
public abstract class BaseController {
  @ApiOperation(value = "测试", notes = "测试")
  @ResponseBody
  @GetMapping(value = "/test")
  public ActionResult test() {
    return ActionResult.OK;
  }

  @Autowired
  protected AuthToken authToken;

  protected Employee getCurrentUser() throws Exception {
    if (StringUtils.isBlank(authToken.getEmployee())) {
      throw new LoginException("用户未登录或登录超时，请重新登录！");
    }
    Employee employee = new Employee();
    employee.setUuid(authToken.getEmployee());
    employee.setCode(authToken.getEmployCode());
    employee.setName(authToken.getEmployName());
    employee.setMart(authToken.getMart());
    return employee;
  }
}
