package com.baselogic.boot.corsdemo;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // Allow AJAX preflight requests via HttpMethod.OPTIONS to be made without
        // BASIC Authentication
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

        http.cors();
    }


    @Description("This CORS Filter is of type org.springframework.web.filter.CorsFilter")
    @Primary
    @Profile("customCorsFilter")
    @Bean
    public CorsFilter corsFilter(){
        return new CustomCorsFilter();
    }


    /**
     * Generic GenericFilterBean CORS Filter
     * @return
     */
    @Description("This CORS Filter is of type FilterRegistrationBean")
    @Profile("corsFilterBean")
    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registrationBean =
                new FilterRegistrationBean(new CORSFilter());
        registrationBean.setName("CORS Filter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

} // The End...
