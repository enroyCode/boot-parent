/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	EmployeeDao.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.core.dao.employee;

import com.enroy.cloud.boot.api.biz.employee.Employee;
import com.enroy.cloud.boot.core.dao.base.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @author zhuchao
 */
@Repository
public class EmployeeDao extends BaseDao {
  public Employee get(String mart, String uuid) {
    return null;
  }
}
