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
        Thread.sleep(1000); // simulated delay

        if (message.getQuestion().contains("知道")) {
            message.setAnswer("知道");
        } else if (message.getQuestion().contains("能")) {
            message.setAnswer("能");
        } else if (message.getQuestion().contains("会")) {
            message.setAnswer("会");
        } else if (message.getQuestion().contains("吗")){
            String question = message.getQuestion();
            String answer = question.replace("吗", "").replace("?", "!");
            message.setAnswer(answer);
        } else {
            message.setAnswer("你猜");
        }

        return new Greeting("A: " + HtmlUtils.htmlEscape(message.getAnswer()) + "!");
    }

}
