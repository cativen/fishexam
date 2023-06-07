package com.fishexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * @program: FishExam
 * @description: 在线聊天
 * @author Zhuyu 1766722033@qq.com
 * @since 2020-05-13 21:23
 **/
@Controller
@RequestMapping("socket")
public class SocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String chatWebSocket(String name) {
        System.out.println(name);
//        System.out.println(content);
        Message message = new Message();
        message.setName(name);
//        message.setContent(content);
        return name;
    }

    @MessageMapping("/chat")
    public void chat(Principal principal,String chat){

        String name1 = principal.getName();
        System.out.println(name1);
        System.out.println(chat);
        simpMessagingTemplate.convertAndSendToUser("sang","/queue/chat",chat);
    }

    public class Chat {
        private String to;
        private String from;
        private String content;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public class Message {
        private String name;
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Message{" +
                    "name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        public Message() {
            super();
        }

        public Message(String name, String content) {
            this.name = name;
            this.content = content;
        }
    }
}
