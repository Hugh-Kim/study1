package com.messenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.vertx.java.core.json.JsonObject;

/**
 *
 * Created by Administrator on 2014-06-02.
 */
@Controller
public class ChattingController {
    @Autowired
    private ChatServerVerticle chatServerVerticle;

    @RequestMapping({"/", "/index"})
    public String chatRoomMain() {
        return "index";
    }

    @RequestMapping("/intoChatRoom")
    public ModelAndView intoChatRoom(@RequestParam String userName){
        ModelAndView modelAndView = new ModelAndView("/chatRoom");
        modelAndView.addObject("userName", userName);
        modelAndView.addObject("port", 1234);
        return modelAndView;
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send() {
        chatServerVerticle.getIo().sockets().emit("echo", new JsonObject().putString("data", "hello Spring!"));
        return "ok";
    }
}
