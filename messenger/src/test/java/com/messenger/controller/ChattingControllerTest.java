package com.messenger.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

@RunWith(MockitoJUnitRunner.class)
public class ChattingControllerTest {
    private ModelAndView modelAndView;

    @Before
    public void setUp() throws Exception {
        modelAndView = new ModelAndView();
    }

    @Test
    public void testMakeChatRoom() throws Exception {

    }
}
