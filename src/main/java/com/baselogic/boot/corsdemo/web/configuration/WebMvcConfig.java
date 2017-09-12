package com.baselogic.boot.corsdemo.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Add CORS filter to entire site
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter
{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Origin",
                        "X-Requested-With", "Content-Type", "Accept", "X-Auth-Token",
                        "X-Csrf-Token", "Authorization")
                .exposedHeaders("token_header1", "token_header2")
                .allowCredentials(false)
                .maxAge(3_600);
    }

} // The End...
