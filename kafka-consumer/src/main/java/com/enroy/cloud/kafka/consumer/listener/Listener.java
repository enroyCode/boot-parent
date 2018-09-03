/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	Listener.java
 * 模块说明：
 * 修改历史：
 * 2018/9/3 - zhuchao - 创建。
 */
package com.enroy.cloud.kafka.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author zhuchao
 */
@Slf4j
public class Listener {
  @KafkaListener(topics = {"test"})
  public void listen(ConsumerRecord<?, ?> record) {
    log.info("kafka的key: " + record.key());
    log.info("kafka的value: " + record.value().toString());
  }
}
