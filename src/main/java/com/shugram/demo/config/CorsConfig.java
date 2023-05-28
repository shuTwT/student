package com.shugram.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        // 允许哪个请求来源进行跨域
        //config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        // 是否允许携带cookie进行跨域
        config.setAllowCredentials(true);
        // 允许哪个方法进行跨域
        config.addAllowedMethod("*");
        // 允许哪个请求头
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }
}
