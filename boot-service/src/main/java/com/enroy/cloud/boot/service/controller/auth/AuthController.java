/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	AuthController.java
 * 模块说明：
 * 修改历史：
 * 2018/8/27 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.controller.auth;

import com.enroy.cloud.boot.api.biz.employee.Employee;
import com.enroy.cloud.boot.api.exception.BusinessException;
import com.enroy.cloud.boot.api.service.auth.AuthService;
import com.enroy.cloud.boot.api.service.token.TokenService;
import com.enroy.cloud.boot.service.controller.BaseController;
import com.enroy.cloud.boot.service.service.auth.LoginForAdminRequest;
import com.enroy.cloud.boot.service.service.auth.LoginForAdminResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuchao
 */
@RestController
@Api(tags = "登录认证接口")
@RequestMapping("/auth/")
public class AuthController extends BaseController {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private AuthService authService;

  @ApiOperation(value = "后台登录", notes = "登录-后台管理")
  @PostMapping(value = "login/forAdmin")
  @ResponseBody
  public LoginForAdminResponse loginForAdmin(HttpServletRequest request, HttpServletResponse response,
                                             @ApiParam("登录参数") @RequestBody LoginForAdminRequest loginRequest) throws Exception {
    LoginForAdminResponse result = new LoginForAdminResponse();
    // 登录认证
    Employee employee;
    try {
      employee = authService.authenticate(loginRequest.getPrinciple(),
                                          loginRequest.getPassword());
      result.setEmployee(employee);
    } catch (Exception e) {
      throw new BusinessException("用户名或密码错误");
    }
    // 签发token
    setAuthToken(request, response, employee);
    return result;
  }

  // 签发token
  private void setAuthToken(HttpServletRequest request, HttpServletResponse response, Employee employee) {
    authToken.setEmployee(employee.getUuid());
    authToken.setEmployCode(employee.getCode());
    authToken.setEmployName(employee.getName());

    //签发token
    tokenService.issue(request, response);
  }
}
