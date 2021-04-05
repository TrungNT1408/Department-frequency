package vn.osp.adfilex.Departmentfrequency.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import vn.osp.adfilex.Departmentfrequency.utils.StringUtils;


/**
 * 
 * vn.osp.adfilex.config 
 * @author LuongTN : 9:25:26 AM
 * 
 * 
 * RestApiConfiguration.java
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class RestApiConfiguration {

  private static final long MAX_AGE_SECS = 3600;

  @Value("${allowedOrigins}")
  private String allowedOrigins;

  @Bean
  public RestTemplate createRestTemplate() {
    return new RestTemplate();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(
                (null == allowedOrigins || StringUtils.EMPTY.equals(allowedOrigins)) ? "*"
                    : allowedOrigins)
            .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
            .allowCredentials(true).maxAge(MAX_AGE_SECS);
      }
    };
  }

  @Bean
  public Docket api() {

    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("vn.osp.adfilex.Departmentfrequency.controller"))
        .paths(PathSelectors.any()).build().securitySchemes(Arrays.asList(apiKey()))
        //.paths(PathSelectors.ant("/swagger/")).build().securitySchemes(Arrays.asList(apiKey()))
        .securityContexts(Arrays.asList(securityContext())).apiInfo(getApiInformation());

  }

  private ApiKey apiKey() {
    return new ApiKey(StringUtils.API_KEY, StringUtils.AUTHORIZATION, StringUtils.HEADER);
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any())
        .build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[] {authorizationScope};
    return Arrays.asList(new SecurityReference(StringUtils.API_KEY, authorizationScopes));
  }

  private ApiInfo getApiInformation() {

    return new ApiInfoBuilder().title("Swagger Cục Tần số REST API")
        .description("This is document Department Frequency API created using Spring Boot v2.1.9")
        .contact(new Contact("Developer Trung : .......", "https://swagger.io/",
            "tranngocthanhluong@gmail.com"))
        .license("Apache 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
        .version("1.0.0").build();
  }
}
