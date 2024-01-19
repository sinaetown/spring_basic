package com.encore.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.file.Paths;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
//    Docket은 Swagger 구성의 핵심 기능 클래스
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select() //어떤 컨트롤러 또는 어떤 api를 Swagger 문서에 포함시킬지 선택
//                모든 RequestHandler들을 문서와 대상으로 선택한다는 설정
                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any()) : 모든 path의 컨트롤러에 swagger 적용
                .paths(PathSelectors.ant("/rest/**")) //url 패턴 정의, *이 1개면 직계, 2개면 자손 단위
                .build();
    }

//    swagger에 authorize 자물쇠 버튼 활성화를 위해서는 jwt, session 등의 별도의 설정 필요

}
