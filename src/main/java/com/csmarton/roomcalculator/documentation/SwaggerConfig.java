package com.csmarton.roomcalculator.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Room Wallpaper size Calculator")
                        .description("Basic exercise")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Csaba Marton")
                                .url("https://github.com/csabamarton")
                                .email("martoncsab@gmail.com"))
                );
    }
}