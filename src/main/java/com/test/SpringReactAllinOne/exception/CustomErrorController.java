package com.test.SpringReactAllinOne.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/errorTest")
    public String errorTest(){
        log.info("error controller invoke1");
        return "error";
    }

    @RequestMapping("/error")
    public ModelAndView error(){
        log.info("error controller invoke2");
        ModelAndView errorView = new ModelAndView("error");
        return errorView;
    }
}
