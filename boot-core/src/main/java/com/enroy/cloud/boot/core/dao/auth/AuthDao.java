/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	authDao.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.core.dao.auth;

import com.enroy.cloud.boot.api.biz.employee.Employee;
import com.enroy.cloud.boot.core.dao.base.BaseDao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhuchao
 */
@Component
public class AuthDao extends BaseDao {

  public Employee authenticate(String principle, String password) {
    List<Employee> results = jdbcTemplate.query("select * from employee where code = ? and password = ?", new Object[]{principle, password}, new BeanPropertyRowMapper(Employee.class));
    return results.isEmpty() ? null : results.get(0);
  }
}
