/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	OperateInfo.java
 * 模块说明：
 * 修改历史：
 * 2018/8/21 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.commons.biz;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author zhuchao
 */
@Setter
@Getter
public class OperateInfo implements HasOperateInfo {
  private Date time;
  private String operator;
}
