package com.baselogic.boot.corsdemo;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/api
 *
 * http://localhost:9999/index.html
 *
 * @CrossOrigin can be used instead of a custom
 * CustomCorsFilter.
 */
@RestController
public class RestEndpoint {

    @GetMapping("/api")
    public String api(){
        return "{Hello API}";
    }

    @CrossOrigin
    @GetMapping("/crossOrigin")
    public String crossOrigin(){
        return "{Hello crossOrigin API}";
    }

} // The End...
