package com.jTalks.groceryshop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title(ApplicationInfo.TITLE)
                .description(ApplicationInfo.DESCRIPTION)
                .contact(ApplicationInfo.CONTACT));
    }
    @UtilityClass
    private static class ApplicationInfo {
        public static final String TITLE = "Grocery-Shop";
        public static final String DESCRIPTION = "This app assists you to buy groceries";
        private static final String CONTACT_NAME = "Fatemeh Joolaian";
        private static final String CONTACT_EMAIL = "joolafatemeh652@gmail.com";
        public static final Contact CONTACT = new Contact().name(CONTACT_NAME).email(CONTACT_EMAIL);

    }
}
