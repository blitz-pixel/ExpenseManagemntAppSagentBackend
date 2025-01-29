package com.example.ExpenseManagementApp.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    // CORS Configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:8080") // Allow requests from this origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow credentials (e.g., cookies)
            }
        };
    }

    // Security Configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/**").permitAll() // Allow all requests
                        .anyRequest().permitAll()) // Allow all other requests as well
                .csrf(csrf -> csrf.disable()); // Disable CSRF protection

        return http.build();
    }
}


//package com.example.SampleLoginApplication.Configurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class SecurityConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // Apply CORS to all endpoints
//                .allowedOrigins("http://localhost:5173") // Allow requests from your React app
//                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these HTTP methods
//                .allowedHeaders("*"); // Allow all headers
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/**")
//                        .permitAll()
//                        .anyRequest() // All other requests need authentication
//                        .authenticated())
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers("/**"));
//
//        return http.build();
//    }
//}
//
