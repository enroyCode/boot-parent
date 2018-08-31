/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	BootFeignConfiguration.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.config;

import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.netflix.feign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.nio.charset.Charset;

/**
 * 调用feign配置类
 *
 * @author zhuchao
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class BootFeignConfiguration {
  @Autowired
  private Environment env;

  /**
   * connectionTimeout为60s,readTimeout为60s
   */
  @Bean
  Request.Options options() {
    return new Request.Options(60000, 60000);
  }

  /**
   * 重试次数
   */
  @Bean
  Retryer retryer() {
    return Retryer.NEVER_RETRY;
  }

  /**
   * 调用接口BasicAuth拦截器
   *
   * @return
   */
  @Bean
  public BasicAuthRequestInterceptor basicAuthorizationInterceptor() {
    String user = env.getProperty("feign-service.auth.username");
    String password = env.getProperty("feign-service.auth.password");
    return new BasicAuthRequestInterceptor(user, password, Charset.forName("UTF-8"));
  }

  /**
   * 设置日志
   *
   * @return
   */
  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}
