/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	TestController.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.feign.controller;

import com.enroy.cloud.boot.api.biz.ActionResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 */
@RestController
@RequestMapping("/test")
public class TestController {
  @GetMapping("/about")
  public ActionResult about() {
    return ActionResult.OK;
  }
}
