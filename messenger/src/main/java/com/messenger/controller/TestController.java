package com.messenger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/test")
	public String test(){
		System.out.println("call TestController!!");
		//test
		return "test";
	}
}
