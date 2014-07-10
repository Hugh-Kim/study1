package com.messenger.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dispatcher-servlet.xml")
@WebAppConfiguration
public class ChattingControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    public static final String TEST_USER_NAME = "Hugh";

    @Before
	public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

    @Test
    public void testIndexMain() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("WEB-INF/jsp/index.jsp"));
    }

    @Test
    public void testMakeChatRoom() throws Exception {
        mockMvc.perform(get("/makeChatRoom").param("userName", TEST_USER_NAME))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("WEB-INF/jsp/chatRoom.jsp"))
                .andExpect(model().attribute("userName", is(TEST_USER_NAME)))
                .andExpect(model().attribute("port", is(1234)));
    }

    @Test
	public void testSend() throws Exception {
        mockMvc.perform(post("/send").param("userName", TEST_USER_NAME).param("message", "This is test message."))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
                ;

	}
}
