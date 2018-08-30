/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	LoginForAdminRequest.java
 * 模块说明：
 * 修改历史：
 * 2018/8/30 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.utils;

/**
 * 密码加密工具类
 *
 * @author zhuchao
 **/
public class PasswordUtils {

  public static String encryptPassword(String plain) {
    String word = plain;
    if (word == null || "".equals(word)) {
      word = "###############################";
    }
    while (word.length() <= 30) {
      word = word + "$" + word;
    }
    byte[] bytes = word.getBytes();
    int len = bytes.length;
    for (int i = 1; i <= len - 30; i++) {
      for (int j = 1; j <= len - i; j++) {
        if (i % 2 == 1) {
          bytes[j - 1] = (byte) (((bytes[j - 1] + bytes[j]) * 2 + 1) % 95 + 32);
        } else {
          bytes[j - 1] = (byte) ((Math.abs((bytes[j - 1] - bytes[j])) * 2 + 1) % 95 + 32);
        }
      }
    }
    word = new String(bytes, 0, 30);
    word = word.trim();
    return word;
  }

  public static void main(String[] args) throws Exception {
    String password = "123456";
    String encode = PasswordUtils.encryptPassword(password);
    System.out.println(encode);
  }

}
