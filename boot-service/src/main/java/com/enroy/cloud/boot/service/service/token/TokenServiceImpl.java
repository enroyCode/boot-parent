/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	boot-parent
 * 文件名：	TokenServiceImpl.java
 * 模块说明：
 * 修改历史：
 * 2018/8/29 - zhuchao - 创建。
 */
package com.enroy.cloud.boot.service.service.token;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.enroy.cloud.boot.api.biz.token.Token;
import com.enroy.cloud.boot.api.biz.token.TokenConfig;
import com.enroy.cloud.boot.api.exception.AuthenticationException;
import com.enroy.cloud.boot.api.service.token.TokenService;
import com.google.common.net.InternetDomainName;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuchao
 */
@Component
public class TokenServiceImpl implements TokenService {
  @Resource(name = Token.BEAN_ID)
  private Token token;

  @Resource(name = TokenConfig.BEAN_ID)
  private TokenConfig tokenConfig;

  /**
   * 根据当前AuthToken签发jwt到cookie中
   *
   * @param request
   * @param response
   */
  public void issue(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    // 签发JWT
    String jwt = issue(tokenConfig.getExpSeconds());

    // 把JWT写入cookie
    Cookie cookie = createCookie(request, jwt);
    response.addCookie(cookie);
  }

  /**
   * 签发TOKEN
   *
   * @param request
   * @param response
   * @param expSeconds
   *          过期时长
   * @throws AuthenticationException
   */
  @Override
  public void issue(HttpServletRequest request, HttpServletResponse response, int expSeconds)
          throws AuthenticationException {
    // 签发JWT
    String jwt = issue(expSeconds);

    // 把JWT写入cookie
    Cookie cookie = createCookie(request, jwt);
    response.addCookie(cookie);
  }

  /**
   * 清除当前请求中的token信息
   *
   * @param request
   * @param response
   */
  public void erase(HttpServletRequest request, HttpServletResponse response) {
    // 清除token信息
    token.setMap(new HashMap<String, Object>());

    // 清除COOKIE
    removeCookie(request, response);
  }

  /**
   * 刷新当前请求的token
   *
   * @param request
   * @param response
   * @throws AuthenticationException
   */
  public void refresh(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    long now = System.currentTimeMillis() / 1000L;
    int issuedAt = token.getIssuedAt();
    if (now - issuedAt > tokenConfig.getRefreshIntervalSeconds()) {
      issue(request, response);
    }
  }

  /**
   * 用给定的jwt创建一个cookie
   *
   * @param request
   * @param jwt
   */
  private Cookie createCookie(HttpServletRequest request, String jwt) {
    Cookie cookie = new Cookie(tokenConfig.getCookieName(), jwt);
    cookie.setDomain(getCookieDomain(request));
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(-1);
    return cookie;
  }

  /**
   * 删除存储jwt的cookie
   *
   * @param request
   * @param response
   */
  private void removeCookie(HttpServletRequest request, HttpServletResponse response) {
    if (request.getCookies() == null)
      return;

    // 删除cookie
    for (Cookie cookie : request.getCookies()) {
      if (cookie.getName().equals(tokenConfig.getCookieName())) {
        cookie.setValue(null);
        cookie.setDomain(getCookieDomain(request));
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        break;
      }
    }
  }

  /**
   * 验证JWT，如果通过则刷新AuthToken
   *
   * @param request
   * @throws AuthenticationException
   */
  public void verify(HttpServletRequest request) throws AuthenticationException {
    String jwt = null;
    if (request.getCookies() == null) {
      throw new AuthenticationCredentialsNotFoundException("当前用户未登录");
    }
    for (Cookie cookie : request.getCookies()) {
      if (cookie.getName().equals(tokenConfig.getCookieName()))
        jwt = cookie.getValue();
    }
    if (jwt == null) {
      throw new AuthenticationCredentialsNotFoundException("当前用户未登录");
    }

    JWTVerifier jwtVerifier = new JWTVerifier(tokenConfig.getSecret());
    Map<String, Object> claims;
    try {
      claims = jwtVerifier.verify(jwt);
    } catch (Exception e) {
      throw new BadCredentialsException("用户身份验证失败");
    }
    token.setMap(claims);
  }

  /**
   * 根据当前AuthToken签发一个jwt
   */
  private String issue(int expSeconds) throws AuthenticationException {
    // JWT签名设置
    JWTSigner.Options options = new JWTSigner.Options().setAlgorithm(Algorithm.HS256);
    options.setExpirySeconds(expSeconds);
    options.setIssuedAt(true);

    // 签名
    if (tokenConfig.getSecret() == null) {
      throw new AuthenticationCredentialsNotFoundException("JWT签名密钥不存在");
    }
    JWTSigner jwtSigner = new JWTSigner(tokenConfig.getSecret());
    return jwtSigner.sign(token.getMap(), options);
  }

  /**
   * 计算cookie的域名
   *
   * @param request
   */
  private static String getCookieDomain(HttpServletRequest request) {
    String result = request.getServerName();
    try {
      result = InternetDomainName.from(request.getServerName()).topPrivateDomain().toString();
    } catch (Exception e) {
    }
    return result;
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }

  public TokenConfig getTokenConfig() {
    return tokenConfig;
  }

  public void setTokenConfig(TokenConfig tokenConfig) {
    this.tokenConfig = tokenConfig;
  }
}
