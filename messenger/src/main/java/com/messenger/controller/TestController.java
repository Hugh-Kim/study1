package com.messenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@RequestMapping("/main")
	public String test(){
		System.out.println("call TestController!!");
		//test
		return "/test";
	}

    @RequestMapping("/say")
    @ResponseBody
    public String say(String message) {
        System.out.println(message);
        return message;
    }
}
