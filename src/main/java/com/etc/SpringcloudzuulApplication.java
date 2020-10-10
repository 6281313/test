package com.etc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableRedisHttpSession
public class SpringcloudzuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudzuulApplication.class, args);
	}

	//创建Filters
	@Bean
	public LoginFilter getLoginFilter(){
		return new LoginFilter();
	}
/*	@Bean
	public TokenFilter getTokenFilter(){
		return new TokenFilter();
	}
	@Bean
	public PasswordFilter getPasswordFilter(){
		return new PasswordFilter();
	}*/

}
