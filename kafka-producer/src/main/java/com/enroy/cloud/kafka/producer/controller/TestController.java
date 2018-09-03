/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-kafka
 * 文件名：	TestController.java
 * 模块说明：
 * 修改历史：
 * 2018/9/3 - zhuchao - 创建。
 */
package com.enroy.cloud.kafka.producer.controller;

import com.enroy.cloud.boot.api.biz.ActionResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhuchao
 */

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
  @Autowired
  private KafkaTemplate kafkaTemplate;

  @RequestMapping(value = "/send", method = RequestMethod.GET)
  public ActionResult sendKafka(HttpServletRequest request, HttpServletResponse response) {
    ActionResult result = new ActionResult();
    try {
      String message = request.getParameter("message");
      log.info("kafka的消息={}", message);
      kafkaTemplate.send("test", "key", message);
      log.info("发送kafka成功.");
      result.setMessage("发送kafka成功");
      return result;
    } catch (Exception e) {
      log.error("发送kafka失败", e);
      result.setMessage("发送kafka失败");
      return result;
    }
  }
}
