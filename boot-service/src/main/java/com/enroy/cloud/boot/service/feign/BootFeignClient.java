/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	BootFeignClient.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.feign;

import com.enroy.cloud.boot.api.biz.ActionResult;
import com.enroy.cloud.boot.service.config.BootFeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhuchao
 * FeignClient 的url属性指定访问路径，通常用作本地调试
 * 若yml配置中使用了context-path属性，则在此处需要添加path属性与之对应
 */
@FeignClient(name = "feign-service", path = "/feign-service", configuration = BootFeignConfiguration.class)
public interface BootFeignClient {
  @RequestMapping(value = "/test/about", method = RequestMethod.GET)
  ActionResult about();
}
