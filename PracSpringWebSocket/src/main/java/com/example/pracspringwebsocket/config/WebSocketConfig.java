package com.example.pracspringwebsocket.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// 최초 접속에서만 http프로토콜 위에서 handshacking을 하기 때문에 http header를 사용한다.
// webSocket을 위한 별도의 포트는 없으며, 기존 포트(http:80, https:443)를 사용한다.
// WebSocket 프로토콜 ws : http /  wss : https
// 메시지(Upgrade:WebSocket, Connection:Upgrade)
// 메시지에 포함될 수 있는 교환 가능한 메세지 text, binary
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    // 웹소켓은 특정 브라우저의 특정 버전에서만 작동을 지원하는데, SockJS, socket.io 라이브러리를 사용하면 웹소켓을 지원하지 않는 브라우저에서 사용가능
    // 스프링은 SockJS를 지원

    @Autowired
    private ReplyEchoHandler replyEchoHandler;

    @Override
    // 스프링에서 웹소켓을 사용하기 위해서 클라이언트가 보내는 통신을 처리할 핸들러가 필요
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 핸들러를 추가 , path : 웹소켓이 연결될 때, Handshake할 주소
        registry.addHandler(replyEchoHandler, "/chatroom")
                .setAllowedOrigins("*");
                // SockJS 라이브러리를 사용하도록 설정
//                .withSockJS();
    }
}
