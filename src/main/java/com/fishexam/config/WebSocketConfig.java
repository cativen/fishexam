package com.fishexam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @program: FishExam
 * @description: 在线实时聊天websocket
 * @author Zhuyu 1766722033@qq.com
 * 
 * @since 2020-05-13 22:07
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //表示定义一个前缀为”/chat“的endPoint 并开启withSockJS支持 SockJS可以解决浏览器对WebSocket兼容的问题，客户端可以
        //通过这里配置的URL来建立WebSocket连接
        registry.addEndpoint("/chat").withSockJS();
//        registry.addEndpoint("/p2p").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //表示设置消息代理的前缀 即如果消息的前缀是“/topic”，就会把消息转发给消息代理人(broker),再有消息代理人将信息广播给当前连接的客户端
        registry.enableSimpleBroker("/topic","/queue");
        //表示配置一个或多个前缀 通过这些前缀过滤出需要被注解方法处理的消息。例如前缀“/app”的destination可以通过@MessageMapping注解的使用方法
        //处理，而其他的destination(例如”/topic“ ”/queue“)将直接交给broker处理
        registry.setApplicationDestinationPrefixes("/app");
    }
}
