package com.enroy.cloud.boot.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 接口安全管理
 *
 * @author zhuchao
 */
@Configuration
@EnableWebSecurity
public class BootFeignSecurity extends WebSecurityConfigurerAdapter {

  @Autowired
  private Environment env;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    String username = env.getProperty("boot-service.rest.username");
    String password = env.getProperty("boot-service.rest.password");
    auth.inMemoryAuthentication().withUser(username).password(password).roles("USER");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests().antMatchers("/*/employee/**", "/employee/**").hasRole("USER")
            .and().sessionManagement()
            .and().logout()
            .and().httpBasic();
  }
}
