/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	TokenConfig.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.biz.token;

/**
 * @author zhuchao
 */
public class TokenConfig {
  public static final String BEAN_ID = "dcommons.tokenConfig";

  /** 写入浏览器端cookie时的cookie名称 */
  private String cookieName = "jwt";

  /** 签名用的私钥 */
  private String secret;

  /** JWT有效时间秒数 */
  private Integer expSeconds;

  /** JWT刷新间隔秒数 */
  private Integer refreshIntervalSeconds;

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public Integer getExpSeconds() {
    return expSeconds;
  }

  public void setExpSeconds(Integer expSeconds) {
    this.expSeconds = expSeconds;
  }

  public Integer getRefreshIntervalSeconds() {
    return refreshIntervalSeconds;
  }

  public void setRefreshIntervalSeconds(Integer refreshIntervalSeconds) {
    this.refreshIntervalSeconds = refreshIntervalSeconds;
  }

  public String getCookieName() {
    return cookieName;
  }

  public void setCookieName(String cookieName) {
    this.cookieName = cookieName;
  }
}
