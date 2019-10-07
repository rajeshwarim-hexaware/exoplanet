package com.symantec.exoplanet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Component
@EnableSwagger2
public class SwaggerDocumentationConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.symantec.exoplanet")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo());
	}

	@Bean
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("ExoPlanet Service").description("ExoPlanet Service")
				.termsOfServiceUrl("http://sprintfox.io").contact("ExoPlanet Team")
				.license("Apache License Version 2.0").licenseUrl("").version("2.0").build();
	}
}