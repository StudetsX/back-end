package com.acheron.campusx.security.config;

import com.acheron.campusx.security.entity.Role;
import com.acheron.campusx.security.jwt.JwtFilter;
import com.acheron.campusx.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final JwtFilter jwtFilter;

    @Value("${spring.allowed-cors}")
    private String cors;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:5173" ,cors).allowedOriginPatterns("*");
            }
        };
    }

    @SneakyThrows
    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity http) {
        return http.
                csrf(AbstractHttpConfigurer::disable).
                cors(Customizer.withDefaults()).
                authorizeHttpRequests(request->
                        request.requestMatchers("/api/v2/login","/api/v2/registration","/asd1","/api/v2/saveImage","/api/v2/findAllGroups","api/v2/findAllChairs","/api/v2/users","/api/v2/findAllSubjects").
                                permitAll().
                                requestMatchers("/api/v2/createTest").
                                hasAuthority(Role.TEACHER.getAuthority()).
                                anyRequest().
                                authenticated()).
                userDetailsService(userService).
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).
                build();
    }

//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(List.of("https://localhost:5173"));
//        corsConfiguration.setAllowedMethods(List.of("GET", "PUT", "PATCH", "POST", "DELETE"));
////        corsConfiguration.setAllowedHeaders(List.of(
////                HttpHeaders.AUTHORIZATION,
////                HttpHeaders.CONTENT_TYPE,
////                HttpHeaders.ACCEPT,
////                "X-XSRF-TOKEN"));
//        corsConfiguration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }
}
