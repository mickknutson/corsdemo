package com.baselogic.boot.corsdemo;

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

//    @CrossOrigin
    @GetMapping("/api")
    public String index(){
        return "{Hello World}";
    }

} // The End...
