package com.gaodashang.demo.endpoint;

import com.gaodashang.demo.TextMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Bean
    public TextMessageHandler textMessageHandler() {
        return new TextMessageHandler();
    }

    @RequestMapping
    public String view() {
        return "message";
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send(HttpServletRequest request, @RequestParam("username") String username) {
        TextMessage message = new TextMessage(request.getParameter("message"));
        textMessageHandler().sendMessageToUser(username, message);
        return "true";
    }
}
