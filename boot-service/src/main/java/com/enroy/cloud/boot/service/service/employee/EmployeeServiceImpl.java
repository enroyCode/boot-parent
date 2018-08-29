/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	EmployeeImpl.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.service.employee;

import com.enroy.cloud.boot.api.biz.employee.Employee;
import com.enroy.cloud.boot.api.service.employee.EmployeeService;
import com.enroy.cloud.boot.core.dao.employee.EmployeeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuchao
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  private EmployeeDao employeeDao;

  @Override
  public Employee get(String mart, String uuid, String... fetchParts) throws IllegalArgumentException {
    return employeeDao.get(mart,uuid);
  }
}
