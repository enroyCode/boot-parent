/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	LoggerHttpServletRequestWrapper.java
 * 模块说明：
 * 修改历史：
 * 2019/5/8 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.filter.log;

import com.enroy.cloud.boot.api.service.checker.FilterUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhuchao
 */
public class LoggerHttpServletRequestWrapper extends HttpServletRequestWrapper {
  /**
   * Constructs a request object wrapping the given request.
   *
   * @param request
   *         The request to wrap
   * @throws IllegalArgumentException
   *         if the request is null
   */
  private final byte[] body;

  public LoggerHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);
    body = FilterUtils.readBytes(request.getInputStream());
  }

  @Override
  public BufferedReader getReader() {
    return new BufferedReader(new InputStreamReader(getInputStream()));
  }

  @Override
  public ServletInputStream getInputStream() {
    final ByteArrayInputStream bais = new ByteArrayInputStream(body);
    return new ServletInputStream() {

      @Override
      public boolean isFinished() {
        return false;
      }

      @Override
      public boolean isReady() {
        return false;
      }

      @Override
      public void setReadListener(ReadListener readListener) {

      }

      @Override
      public int read() {
        return bais.read();
      }
    };
  }
}
