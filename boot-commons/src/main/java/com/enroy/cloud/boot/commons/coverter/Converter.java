/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	Converter.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.commons.coverter;

import org.springframework.core.convert.ConversionException;

/**
 * @author zhuchao
 */
public interface Converter<S, T> {

  /**
   * 将指定的源数据对象转换为目标数据对象。
   *
   * @param source
   *         源数据对象，传入nul将导致返回null。
   * @return 返回转换后的目标数据对象。
   * @throws ConversionException
   */
  T convert(S source) throws ConversionException;
}
