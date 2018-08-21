/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	EmployeeController.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.controller.employee;

import com.enroy.cloud.boot.api.biz.employee.Employee;
import com.enroy.cloud.boot.api.service.employee.EmployeeService;
import com.enroy.cloud.boot.service.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 */
@RestController
@RequestMapping(value = "/employee", produces = "application/json;charset=utf-8")
@Api(tags = "员工服务")
public class EmployeeController extends BaseController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping(value = "get")
  public Employee get(@ApiParam("员工所属商场") @RequestParam("mart") String mart,
                      @ApiParam("员工id") @RequestParam("id") String id) {
    return employeeService.get(mart, id);
  }
}
