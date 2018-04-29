package com.accent.configuration.library;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.accent.dao.library.ILibraryDao;
import com.accent.dao.library.LibraryDaoImpl;
import com.accent.mapper.ILibraryMapper;
import com.accent.mapper.LibraryMapper;
import com.accent.service.library.ILibraryService;
import com.accent.service.library.LibraryServiceImpl;
import com.accent.validator.BookValidator;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan("com.accent.controller.library")
public class SwaggerConfig extends WebMvcConfigurerAdapter{                                    
	@Bean
	public Docket api() {                
		return new Docket(DocumentationType.SWAGGER_2)          
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.accent.controller.library"))
				.paths(PathSelectors.any()) //ant("/book/**") is used for all method 
				.build()
				.apiInfo(apiInfo());
	}
	private ApiInfo apiInfo() {
		return new ApiInfo(
				"REST API", 
				"Some custom description of API.", 
				"API ORG", 
				"Terms of service", 
				new Contact("Tushar Gade", "www.example.com", "test@gmail.com"), 
				"License of API", "API license URL", Collections.emptyList());
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	@Bean(name = "messageSource")
	public ResourceBundleMessageSource getMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("resourceBundles/msg");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/login").setViewName("login");
	}
	@Bean(name = "libService")
	public ILibraryService getLibService() {
		return new LibraryServiceImpl();
	}
	@Bean(name = "libMapper")
	public ILibraryMapper getLibMapper() {
	return new LibraryMapper();
	}
	@Bean(name = "libDao")
	public ILibraryDao getLibDao() {
		return new LibraryDaoImpl();
	}
	@Bean(name = "bookval")
	public BookValidator getBookval() {
		return new BookValidator();
	}
}