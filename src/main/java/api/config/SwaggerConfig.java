package api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Enviyo API")
                .description("JavaInUse API reference for developers")
                .termsOfServiceUrl("http://javainuse.com")
                .contact("javainuse@gmail.com").
                        license("JavaInUse License")
                .licenseUrl("javainuse@gmail.com")
                .version("1.0")
                .build();
    }
}
