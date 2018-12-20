package com.aiit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.PrintWriter;

@Controller
public class ServletTest {

    @RequestMapping(value = "servletTest.action",method = RequestMethod.GET)
    public void test(PrintWriter out){
        out.print("success!");
    }
}
