package com.Student.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration
public class SwaggerConfig  {
	
	  
	  @Bean
	  public OpenAPI openAPI() {
	      return new OpenAPI()
	            .info(new Info()
	              .title("SpringShop API")
	              .description("SpringBoot sample application")
	              .version("v0.0.1")
	              .contact(new Contact().name("Sumit").email("sky@gmail.com"))
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("SpringShop Wiki Documentation")
	              .url("https://springshop.wiki.github.org/docs"));
	  }

}
	
   /*
	public static final String AUTHORIZATION_HEADER = "Authorization";

	
	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER,"header");
	}
	private List<SecurityContext> securityContexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(sf()).build());
	}
	private List<SecurityReference> sf(){
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverythings");
		return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {scope}));
	}

	@Bean
	Docket api() {
		
		
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.student.controller"))
				.paths(PathSelectors.any()).build();
				
	}

	private ApiInfo getInfo() {
		
		return new ApiInfo("Blogging Application : Backend Course", "sky", 
				"1.0", "T & C", new Contact("sky","https://sky.com","sky@gmail.com"), "license of Apis", "API license URL", 
				Collections.emptyList());
		

	    
	}}*/
	 
	 

