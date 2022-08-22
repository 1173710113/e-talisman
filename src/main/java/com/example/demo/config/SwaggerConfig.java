package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		ParameterBuilder aParameterBuilder = new ParameterBuilder();
		aParameterBuilder.parameterType("header").name("JSESSIONID").description("session值")
				.modelRef(new ModelRef("string")).required(false).build();
		List<Parameter> aParameters = new ArrayList<Parameter>();
		aParameters.add(aParameterBuilder.build());
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				// 是否开启 (true 开启 false隐藏。生产环境建议隐藏)
				// .enable(false)
				.select()
				// 扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
				// 指定路径处理PathSelectors.any()代表所有的路径
				.paths(PathSelectors.any()).build().globalOperationParameters(aParameters);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 设置文档标题(API名称)
				.title("接口规范")
				// 文档描述
				.description("接口说明")
				// 版本号
				.version("1.0.0")
				.build();
	}
}
