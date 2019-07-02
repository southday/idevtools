package cn.idevtools.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.AbstractPathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Swagger配置类
 * Author southday
 * Date   2019/2/25
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 自定义PathProvider，部署到远程服务器时需要去除URL中的项目名，比如：
     * https://idevtools.cn/idevtools/xxx 要改为：
     * https://idevtools.cn/xxx
     */
    private class CustomPathProvider extends AbstractPathProvider {
        @Override
        public String getOperationPath(String operationPath) {
            return operationPath;
        }

        @Override
        protected String applicationPath() {
            return "";
        }

        @Override
        protected String getDocumentationPath() {
            return "/";
        }
    }

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("Token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                /* -------- 部署到服务器的配置 -------------- */
                .protocols(new HashSet<>(Arrays.asList("https", "http")))
                .host("idevtools.cn")
                .pathProvider(new CustomPathProvider())
                /* -------- 部署到服务器的配置 -------------- */
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.idevtools.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IDevTools RESTful API")
                .contact(new Contact("IDevTools Team", "https://github.com/southday/idevtools", "lichaoxi7@qq.com"))
                .version("1.0.0")
                .build();
    }
}
