package com.test.SpringReactAllinOne.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class CustomErrorController implements ErrorController {

//    @RequestMapping(value = "/error")
//    @ResponseBody
//    public ErrorMessageDto errorMessage(HttpServletRequest request) {
//        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        Object exceptionType = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
//        Object exceptionMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
//        return new ErrorMessageDto(statusCode, exceptionType, exceptionMessage);
//    }

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
