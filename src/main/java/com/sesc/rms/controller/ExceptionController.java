package com.sesc.rms.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String error(Exception e){
        System.out.println(e.getMessage());
        return "redirect:/error/500";
    }
}
