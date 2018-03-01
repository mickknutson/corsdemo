package com.baselogic.boot.corsdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class TestController {

    @RequestMapping(method = RequestMethod.GET)
    @GetMapping

//    @PostMapping
    public String fooBar(){
        return "HELLO";
    }

}
