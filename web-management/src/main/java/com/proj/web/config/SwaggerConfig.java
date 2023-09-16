package com.proj.web.config;

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
 * swagger配置类
 * <p>
 * 需要通过注解，才可以在swagger调试页面看到自定义的描述。否则将是默认名称
 * <p>
 * <p></p>
 * 注解：
 * <p>
 * <b>1、@Api</b>
 * <p>
 * <i>参数：tags = 描述</i>
 * <p>
 * 意义：在Controller的class定义，表示是“此类”接口
 * <p>
 * <b>2、@ApiOperation</b>
 * <p>
 * <i>参数：value = 描述</i>
 * <p>
 * 意义：在Controller下面的各个方法（action）定义，表示是某个具体接口
 *
 * @author dong.ning
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 创建rest-api的swagger文档对象
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.getApiInfoConfig())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建api信息配置对象
     *
     * @return
     */
    private ApiInfo getApiInfoConfig() {
        return new ApiInfoBuilder()
                .title("项目标题")
                .description("项目描述")
                .contact(new Contact("董凝", null, null))
                .version("1.0")
                .build();
    }

}
