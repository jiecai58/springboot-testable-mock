package com.spring.testable.mock;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import java.util.ArrayList;

/**
 * @author caiie
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder signParam = new ParameterBuilder();
        ParameterBuilder timeParam = new ParameterBuilder();
        ParameterBuilder tokenParam = new ParameterBuilder();
        ArrayList<Parameter> pars = new ArrayList<>();
        signParam.name("sign").description("user sign").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
                //header中的sign参数非必填，传空也可以
        timeParam.name("timestamp").description("request timestamp mills").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        tokenParam.name("token").description("login token").modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(signParam.build());
        pars.add(timeParam.build());
        pars.add(tokenParam.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("testable mock demo")
                .description("testable mock demo")
                .termsOfServiceUrl("http://xx.io/")
                .version("1.0")
                .build();
    }

}