package com.elfz.liquibaseh2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LiquibaseH2Controller {

    @GetMapping("/test")
    public String test(){
        return "Test";
    }


}
