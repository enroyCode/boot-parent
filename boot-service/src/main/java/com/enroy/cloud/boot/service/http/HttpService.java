package com.enroy.cloud.boot.service.http;

import com.enroy.cloud.boot.commons.utils.json.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.Map;

/**
 * @author zhuchao
 */
@Service
public class HttpService {
  public static final String bean = "boot-service.httpService";
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private RestTemplate restTemplate;

  /**
   * Get请求，简单返回体
   *
   * @param url
   *         请求url
   * @param params
   *         查询参数
   * @param clazz
   *         返回体类型，简单返回体
   */
  public <T> T doGet(String url, Map<String, ?> params, Class<T> clazz) {
    try {
      return restTemplate.getForObject(url, clazz, params);
    } catch (Exception e) {
      logger.error(MessageFormat.format("http get error:url={0}，params={1},message={2}", url, params, e.getMessage()));
      throw e;
    }
  }

  /**
   * Get请求，泛型返回体
   *
   * @param url
   *         请求url
   * @param params
   *         查询参数
   * @param typeReference
   *         返回体类型，泛型返回体
   */
  public <T> T doGet(String url, Map<String, ?> params, TypeReference typeReference) {
    try {
      String json = restTemplate.getForObject(url, String.class, params);
      return JsonUtil.jsonToObject(json, typeReference);
    } catch (Exception e) {
      logger.error(MessageFormat.format("http get error:url={0}，params={1},message={2}", url, params, e.getMessage()));
      throw e;
    }
  }

  /**
   * Post请求，简单返回体
   *
   * @param url
   *         请求url
   * @param requestBody
   *         请求体
   * @param params
   *         查询参数
   * @param clazz
   *         返回体类型，简单返回体
   */
  public <T> T doPost(String url, Object requestBody, Map<String, ?> params, Class<T> clazz) {
    try {
      return restTemplate.postForObject(url, requestBody, clazz, params);
    } catch (Exception e) {
      logger.error(MessageFormat.format("http post error:url={0}，params={1},message={2}", url, params, e.getMessage()));
      throw e;
    }
  }

  /**
   * Post请求，泛型返回体
   *
   * @param url
   *         请求url
   * @param requestBody
   *         请求体
   * @param params
   *         查询参数
   * @param typeReference
   *         返回体类型，泛型返回体
   */
  public <T> T doPost(String url, Object requestBody, Map<String, ?> params, TypeReference typeReference) {
    try {

      String json = restTemplate.postForObject(url, requestBody, String.class, params);
      return JsonUtil.jsonToObject(json, typeReference);
    } catch (Exception e) {
      logger.error(MessageFormat.format("http post error:url={0}，params={1},message={2}", url, params, e.getMessage()));
      throw e;
    }
  }

  public RestTemplate getRestTemplate() {
    return restTemplate;
  }

  public void setRestTemplate(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }
}
