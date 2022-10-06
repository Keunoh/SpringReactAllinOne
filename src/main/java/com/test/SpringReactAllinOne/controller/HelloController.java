package com.test.SpringReactAllinOne.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/user")
@RestController
public class HelloController {

    @PostMapping("/login")
    public TestDto testMessage(){
        log.info("login log check line");
        return new TestDto();
    }
}
