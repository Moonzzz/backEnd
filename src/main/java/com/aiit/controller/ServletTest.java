package com.aiit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ServletTest {

    @RequestMapping(value = "servletTest.action",method = RequestMethod.GET)
    @ResponseBody
    public String test(){
      //  out.print("success！呵呵呵");
        return "呵呵呵";
    }
}
