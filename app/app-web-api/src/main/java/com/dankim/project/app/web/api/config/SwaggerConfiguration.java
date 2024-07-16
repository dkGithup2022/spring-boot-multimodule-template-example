package com.dankim.project.app.web.api.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {
    /*
    *  [api 테스트용 swagger]
    *  / 루트 경로에서 swagger 를 조회 가능합니다.
     * */
    private final String DESCRIPTION = """
            
            [api 테스트용 swagger]
            
            1. / 루트 경로에서 swagger 를 조회 가능합니다. 
          
            2. 지금 value class 에 대한 serializer 를 등록을 안해놔서 Content,Title,Nickname 이 조회되지는 않습니다. 
            
            3. CRUD
            post    -> 생성
            get     -> 조회 
            put     -> 변경 
            """;
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Gradle Multimodule sample")
                .description(DESCRIPTION )
                .version("SAMPLE");
    }
}
