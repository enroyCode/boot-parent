/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	HealthCheckController.java
 * 模块说明：
 * 修改历史：
 * 2018/8/27 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.controller.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhuchao
 */
public class HealthCheckController {
  @Component
  public static class Version implements HealthIndicator {

    @Override
    public Health health() {
      try {
        Health.Builder builder = Health.up();
        DateFormat df = new SimpleDateFormat();
        builder.withDetail("date", df.format(new Date()));
        return builder.build();
      } catch (Exception e) {
        e.printStackTrace();
        return Health.down(e).build();
      }
    }
  }
}
