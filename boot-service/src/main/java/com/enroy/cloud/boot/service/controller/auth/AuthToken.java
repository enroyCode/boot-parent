package com.enroy.cloud.boot.service.controller.auth;

import com.enroy.cloud.boot.api.biz.token.Token;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 存储在JWT中的内容
 */
@Component(Token.BEAN_ID)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthToken extends Token {

  /** 员工ID */
  private static final String KEY_EM_ID = "emId";
  /** 员工编号 */
  private static final String KEY_EM_CODE = "emCo";
  /** 员工姓名 */
  private static final String KEY_EM_NAME = "emNa";

  /** 员工的权限 */
  private static final String KEY_PRMS = "prms";

  public String getEmployee() {
    return (String) map.get(KEY_EM_ID);
  }

  public void setEmployee(String employee) {
    map.put(KEY_EM_ID, employee);
  }

  public String getEmployCode() {
    return (String) map.get(KEY_EM_CODE);
  }

  public void setEmployCode(String employCode) {
    map.put(KEY_EM_CODE, employCode);
  }

  public String getEmployName() {
    return (String) map.get(KEY_EM_NAME);
  }

  public void setEmployName(String employName) {
    map.put(KEY_EM_NAME, employName);
  }

  public List<String> getPermissions() {
    return (List<String>) map.get(KEY_PRMS);
  }

  public void setPermissions(List<String> permissions) {
    map.put(KEY_PRMS, permissions);
  }

}
