package com.leeyaonan.controller;

import com.leeyaonan.model.Greeting;
import com.leeyaonan.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Rot
 * @date 2020/3/3 17:47
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000);
        return new Greeting("Hello," + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

}
