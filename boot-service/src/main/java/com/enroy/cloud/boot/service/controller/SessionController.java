/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	SessionController.java
 * 模块说明：
 * 修改历史：
 * 2018/11/9 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.controller;

import com.enroy.cloud.boot.api.biz.ActionResult;
import com.enroy.cloud.boot.api.biz.employee.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 */
@Api(tags = "获取当前session")
@RestController
@RequestMapping(value = "/*", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SessionController extends BaseController {

  @ApiOperation(value = "获取当前session", notes = "获取当前session")
  @PostMapping("session")
  public ActionResult<SessionData> getSession() throws Exception {
    Employee currentUser = getCurrentUser();
    return new ActionResult<SessionData>();
  }
}
