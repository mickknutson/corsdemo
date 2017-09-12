package com.baselogic.boot.corsdemo;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

public class CustomCorsFilter extends CorsFilter {

    public CustomCorsFilter() {
        super(corsConfigurationSource());
    }

    public static final List<String> ALLOWABLE_HEADERS = Arrays.asList("Origin",
            "X-Requested-With", "Content-Type", "Accept", "X-Auth-Token",
            "X-Csrf-Token", "Authorization");

    private static UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(false);
        config.addAllowedOrigin("*");

        // XMLHttpRequest cannot load http://localhost:8080/api.
        // Request header field Authorization is not allowed by Access-Control-Allow-Headers in preflight response.
//        configuration.addAllowedHeader("*");
        config.setAllowedHeaders(ALLOWABLE_HEADERS);

        config.setMaxAge(36_000L);
        config.setAllowedMethods(
                Arrays.asList("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
        );
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

} // The End...
