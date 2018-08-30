/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	AuthServiceImpl.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.service.auth;

import com.enroy.cloud.boot.api.biz.employee.Employee;
import com.enroy.cloud.boot.api.exception.BusinessException;
import com.enroy.cloud.boot.api.service.auth.AuthService;
import com.enroy.cloud.boot.core.dao.auth.AuthDao;
import com.enroy.cloud.boot.service.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author zhuchao
 */
@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private AuthDao authDao;

  @Override
  public Employee authenticate(String principle, String password) {
    Assert.hasLength(principle, "principle");
    Assert.hasLength(password, "password");
    String encodePwd = PasswordUtils.encryptPassword(password);
    Employee employee = authDao.authenticate(principle, encodePwd);
    if (employee == null) {
      throw new BusinessException("账户或密码不正确");
    }
    return employee;
  }
}
