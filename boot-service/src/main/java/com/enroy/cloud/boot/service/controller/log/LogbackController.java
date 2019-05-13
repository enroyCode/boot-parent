/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	service-center
 * 文件名：	LogbackController.java
 * 模块说明：
 * 修改历史：
 * 2019/5/13 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.controller.log;

import ch.qos.logback.classic.Level;
import com.enroy.cloud.boot.api.biz.ActionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhuchao
 */
@Slf4j
@Api(tags = "日志服务")
@RestController
@RequestMapping(value = "v1/service/logback", produces = "application/json;charset=utf-8")
public class LogbackController {
  @RequestMapping(value = "/level/info", method = RequestMethod.GET)
  @ApiOperation(value = "查询当前日志级别")
  public ActionResult<String> logLevel(@RequestParam(value = "packageName", defaultValue = "-1") String packageName) {
    ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
    Level level;
    if ("-1".equals(packageName)) {
      level = loggerContext.getLogger("root").getLevel();
    } else {
      level = loggerContext.getLogger(packageName).getLevel();
    }
    ActionResult<String> response = new ActionResult();
    response.setData("指定的包名：" + ("-1".equals(packageName) ? "root" : packageName) + "的当前日志级别为：" + level.toString());
    return response;
  }

  /**
   * logback动态修改包名的日志级别
   *
   * @param level
   *         日志级别
   * @param packageName
   *         包名
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/level/update", method = RequestMethod.GET)
  @ApiOperation(value = "修改日志级别")
  public ActionResult<String> updateLogbackLevel(@RequestParam(value = "level") String level,
                                                @RequestParam(value = "packageName", defaultValue = "-1") String packageName) throws Exception {
    ch.qos.logback.classic.LoggerContext loggerContext = (ch.qos.logback.classic.LoggerContext) LoggerFactory.getILoggerFactory();
    Level preLevel;
    if ("-1".equals(packageName)) {
      // 默认值-1，更改全局日志级别；否则按传递的包名或类名修改日志级别。
      preLevel = loggerContext.getLogger("root").getLevel();
      loggerContext.getLogger("root").setLevel(Level.toLevel(level));
    } else {
      preLevel = loggerContext.getLogger(packageName).getLevel();
      loggerContext.getLogger(packageName).setLevel(Level.valueOf(level));
    }
    ActionResult<String> response = new ActionResult();
    response.setData("指定的包名：" + ("-1".equals(packageName) ? "root" : packageName) + "的日志级别由:" + preLevel.toString() + "变更为：" + level);
    return response;
  }
}
