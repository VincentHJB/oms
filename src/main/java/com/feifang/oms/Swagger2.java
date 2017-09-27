package com.feifang.oms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置文件
 * @author Vincent
 * @date 2017/6/7
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket userApi() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder()
                .title("订单管理系统中心API")
                .description("订单管理服务中心")
                .version("1.0.0");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfoBuilder.build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.feifang.oms.web"))//去掉了-web
                .paths(PathSelectors.any())
                .build();
    }
}
