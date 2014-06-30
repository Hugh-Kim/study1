package com.messenger.controller;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dispatcher-servlet.xml")
public class ChattingControllerTest {

    private MockHttpServletRequest mockHttpServletRequest;
    private MockHttpServletResponse mockHttpServletResponse;
    private HandlerAdapter handlerAdapter;
    private ChattingController chattingController;

    @Autowired
    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletResponse = new MockHttpServletResponse();
        handlerAdapter = new AnnotationMethodHandlerAdapter();
//        chattingController = new ChattingController();
        chattingController = context.getBean(ChattingController.class);
    }

    @Test
    public void testMakeChatRoom() throws Exception {
        mockHttpServletRequest.setRequestURI("/index");
        final ModelAndView mav = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, chattingController);
        ModelAndViewAssert.assertViewName(mav, "index");
    }

    @Test
    public void testIntoChatRoom() throws Exception {
        mockHttpServletRequest.setRequestURI("/intoChatRoom");
        mockHttpServletRequest.setParameter("userName", "testUser");
        final ModelAndView mav = handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, chattingController);
        ModelAndViewAssert.assertViewName(mav, "chatRoom");
        ModelAndViewAssert.assertModelAttributeValue(mav, "userName", "testUser");
        ModelAndViewAssert.assertModelAttributeValue(mav, "port", 1234);
    }

    @Test
    public void testSend() throws Exception {
        mockHttpServletRequest.setRequestURI("/send");
        mockHttpServletRequest.setParameter("userName", "testUser");
        mockHttpServletRequest.setParameter("message", "testMessage");
        handlerAdapter.handle(mockHttpServletRequest, mockHttpServletResponse, chattingController);
        Assert.assertThat(mockHttpServletResponse.getContentAsString(), Matchers.is("ok"));
    }
}
