package com.hisi;

import javax.servlet.Filter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hisi.common.util.PasswordUtil;
import com.hisi.common.util.SessionFilter;

@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
@SpringBootApplication
@MapperScan("com.hisi.mapper")
@ComponentScan("com.hisi")
@ServletComponentScan
@EnableScheduling
public class Application {
	// 显示声明CommonsMultipartResolver为mutipartResolver
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		// resolver.setDefaultEncoding("UTF-8");
		// resolver.setResolveLazily(true);//
	    // resolveLazily属性启用是为了推迟文件解析，以在UploadAction中捕获文件大小异常
		// resolver.setMaxInMemorySize(40960);
		// resolver.setMaxUploadSize(10 * 1024 * 1024);// 上传文件大小 5M 5*1024*1024
		return resolver;
	}

	/**
	 * 配置过滤器
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(sessionFilter());
		registration.setName("sessionFilter");
		return registration;
	}

	/**
	 * 创建一个bean
	 * 
	 * @return
	 */
	@Bean(name = "sessionFilter")
	public Filter sessionFilter() {
		return new SessionFilter();
	}

	// @Bean
	// public HttpMessageConverters fastJsonHttpMessageConverters() {
	// FastJsonHttpMessageConverter fastConverter = new
	// FastJsonHttpMessageConverter();
	// FastJsonConfig fastJsonConfig = new FastJsonConfig();
	// fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
	// fastConverter.setFastJsonConfig(fastJsonConfig);
	// HttpMessageConverter<?> converter = fastConverter;
	// return new HttpMessageConverters(converter);
	// }

	@Bean(name = "passwordUtil")
	public PasswordUtil getPasswordUtil() {
		return new PasswordUtil();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
