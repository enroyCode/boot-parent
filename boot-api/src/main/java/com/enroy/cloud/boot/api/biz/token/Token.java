/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	Token.java
 * 模块说明：
 * 修改历史：
 * 2018/8/27 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.biz.token;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储在JWT中的内容，被子类继承
 */
public class Token {
  public static final String BEAN_ID = "dcommons.token";
  /** 签发时间 */
  private static final String KEY_ISSUED_AT = "iat";

  protected Map<String, Object> map = new HashMap<String, Object>();

  public Integer getIssuedAt() {
    return (Integer) map.get(KEY_ISSUED_AT);
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public void setMap(Map<String, Object> map) {
    this.map = map;
  }
}