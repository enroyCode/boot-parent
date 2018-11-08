/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	ActionResult.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.api.biz;

import lombok.Data;

/**
 * @author zhuchao
 */

@Data
public class ActionResult<T> {
  public static final ActionResult OK = new ActionResult();
  private boolean success = true;
  private String code;
  private String message;
  private T data;

  public ActionResult() {

  }

  public ActionResult(T data) {
    this.data = data;
  }

  public static ActionResult ok() {
    return OK;
  }

  public static ActionResult fail(String message) {
    ActionResult r = new ActionResult();
    r.setSuccess(false);
    r.setMessage(message);
    return r;
  }

  public static ActionResult fail(String code, String message) {
    ActionResult r = new ActionResult();
    r.setSuccess(false);
    r.setCode(code);
    r.setMessage(message);
    return r;
  }

}
