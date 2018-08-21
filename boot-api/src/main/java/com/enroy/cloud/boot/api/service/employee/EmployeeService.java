/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	EmployeeService.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.service.employee;

import com.enroy.cloud.boot.api.biz.employee.Employee;

/**
 * @author zhuchao
 */
public interface EmployeeService {

  /**
   * 获取员工
   *
   * @param mart
   *          商场uuid not null
   * @param uuid
   *          员工uuid not null
   * @param fetchParts
   *          匹配部分
   * @return 门店员工
   * @throws IllegalArgumentException
   *           当参数不合法时抛出
   */
  Employee get(String mart, String uuid, String... fetchParts) throws IllegalArgumentException;
}
