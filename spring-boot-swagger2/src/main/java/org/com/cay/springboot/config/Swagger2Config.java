package org.com.cay.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Cay on 2018/3/13.
 */
@Configuration
@EnableSwagger2//启用Swagger2
public class Swagger2Config {

	//从配置文件中获取属性值
	@Value("${swagger2.basepackage}")
	private String basepackage;


	/**
	 * buildDocket()用于创建Docket的Bean，
	 * buildApiInfo()创建Api的基本信息，用于显示在文档页面上。
	 * select()函数返回一个ApiSelectorBuilder实例，用来控制哪些接口暴露给Swagger2来展现。
	 *      一般采用指定扫描的包路径来定义，本例中Swagger会扫描controller包下所有定义的API，并产生文档内容（除了被@ApiIgnore指定的请求）。
	 *
	 * @return
	 */
	@Bean
	public Docket buildDocket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(basepackage))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo buildApiInfo(){
		return new ApiInfoBuilder()
				.title("Spring Boot中使用Swagger2构建RESTful API文档")
				.description("构建简单优雅的Restfun风格的api")
				.termsOfServiceUrl("https://github.com/caychen")
				.contact(new Contact("Cay", "", "412425870@qq.com"))
				.version("1.0")
				.build();
	}
}
