package com.ecommerce.config;

import org.springframework.context.annotation.Configuration;

/**
 * General application configuration.
 *
 * <p>Intended for cross-cutting beans such as:</p>
 * <ul>
 *   <li>ModelMapper / MapStruct mapper bean</li>
 *   <li>Jackson ObjectMapper customisation</li>
 *   <li>CORS configuration</li>
 *   <li>Async executor configuration</li>
 * </ul>
 *
 * TODO: Add CORS configuration and ModelMapper bean when needed.
 */
@Configuration
public class AppConfig {

    // Example: CORS config
    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/api/**")
    //                     .allowedOrigins("http://localhost:3000")
    //                     .allowedMethods("GET","POST","PUT","DELETE","PATCH");
    //         }
    //     };
    // }
}
