package com.messenger.controller;

import com.messenger.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vertx.java.core.json.JsonObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Created by Administrator on 2014-06-02.
 */
@Controller
public class ChattingController {
    private List<Client> userInfoList = new ArrayList<Client>();
    private List<Integer> openedPorts;
    private int latestUsingPort = 1000;

	@Autowired
	private ChatServerVerticle chatServerVerticle;

	@RequestMapping({ "/", "/index" })
	public String chatRoomMain() {
		return "index";
	}

	@RequestMapping("/makeChatRoom")
	public String intoChatRoom(@RequestParam String userName,  Model model) throws UnsupportedEncodingException {
        int port = 1234;
        userInfoList.add(new Client(userName, port));
		model.addAttribute("userName", URLDecoder.decode(userName, "UTF-8"));
		model.addAttribute("port", port);
		return "chatRoom";
	}

	@RequestMapping("/send")
	@ResponseBody
	public String send(@RequestParam final String userName, @RequestParam final String message) {
        JsonObject jsonObject = new JsonObject();
		jsonObject.putString("userName", userName);
		jsonObject.putString("message", message);
		chatServerVerticle.getIo().sockets().emit("chatMessage", jsonObject);
		return "ok";
	}
}
