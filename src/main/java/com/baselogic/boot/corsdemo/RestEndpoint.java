package com.baselogic.boot.corsdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8080/api
 *
 * http://localhost:9999/index.html
 */
@RestController
public class RestEndpoint {

    @GetMapping("/api")
    public String index(){
        return "{Hello World}";
    }

}
