//package com.test.SpringReactAllinOne.exception;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Slf4j
//@Controller
//public class CustomErrorController implements ErrorController {
//    /**
//     * 에러 컨트롤러 사용해주려면 매핑을 permitall로 풀어주어야 할 것 같다.
//     */
//
//    @RequestMapping("/errorTest")
//    public String errorTest(){
//        log.info("error controller invoke1");
//        return "error";
//    }
//
//    @RequestMapping("/error")
//    public ModelAndView error(){
//        log.info("error controller invoke2");
//        ModelAndView errorView = new ModelAndView("error");
//        return errorView;
//    }
//}
