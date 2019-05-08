/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	StreamUtils.java
 * 模块说明：
 * 修改历史：
 * 2019/5/8 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.filter.log;

import javax.servlet.ServletInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author zhuchao
 */
public class StreamUtils {
  public static String streamToString(InputStream inputStream) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      StringBuilder builder = new StringBuilder();
      String output;
      while ((output = br.readLine()) != null) {
        builder.append(output);
      }
      return builder.toString();
    } catch (IOException e) {
      throw new RuntimeException("Http 服务调用失败", e);
    }
  }

  public static byte[] readBytes(ServletInputStream inputStream) {
    return streamToString(inputStream).getBytes(StandardCharsets.UTF_8);
  }
}
