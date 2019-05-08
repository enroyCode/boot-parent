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

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author zhuchao
 */
public class LoggerHttpServletResponseWrapper extends HttpServletResponseWrapper {
  private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
  private HttpServletResponse response;

  /**
   * Constructs a response adaptor wrapping the given response.
   *
   * @param response
   *         The response to be wrapped
   * @throws IllegalArgumentException
   *         if the response is null
   */
  public LoggerHttpServletResponseWrapper(HttpServletResponse response) {
    super(response);
    this.response = response;
  }

  public byte[] getBody() {
    return byteArrayOutputStream.toByteArray();
  }

  @Override
  public ServletOutputStream getOutputStream() {
    return new ServletOutputStreamWrapper(this.byteArrayOutputStream, this.response);
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    return new PrintWriter(new OutputStreamWriter(this.byteArrayOutputStream, this.response.getCharacterEncoding()));
  }

  @Data
  @AllArgsConstructor
  private static class ServletOutputStreamWrapper extends ServletOutputStream {

    private ByteArrayOutputStream outputStream;
    private HttpServletResponse response;

    @Override
    public boolean isReady() {
      return true;
    }

    @Override
    public void setWriteListener(WriteListener listener) {

    }

    @Override
    public void write(int b) throws IOException {
      this.outputStream.write(b);
    }

    @Override
    public void flush() throws IOException {
      if (!this.response.isCommitted()) {
        byte[] body = this.outputStream.toByteArray();
        ServletOutputStream outputStream = this.response.getOutputStream();
        outputStream.write(body);
        outputStream.flush();
      }
    }
  }
}
