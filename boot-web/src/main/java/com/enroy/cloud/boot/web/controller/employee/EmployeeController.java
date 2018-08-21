/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	EmployeeController.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.web.controller.employee;

import com.enroy.cloud.boot.web.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 */
@RestController
@RequestMapping("/employee")
@Api(tags = "员工服务")
public class EmployeeController extends BaseController {
}
