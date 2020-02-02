package algorik5.websocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import util.Log;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

//	@Override
//	public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
//		registration.setSendTimeLimit(15 * 1000).setSendBufferSizeLimit(512 * 1024);
//	registration.setMessageSizeLimit(128 * 1024);
//	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		Log.log("------------------- configureMessageBroker start # "+ config);
		config.enableSimpleBroker("/client");//, "/queue/");//client 송신
		config.setApplicationDestinationPrefixes("/server");//server 송신
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		Log.log("------------------- registerWebSocketHandlers start # "+ registry);
		registry.addEndpoint("/websocket")//클라이언트에서 연결시 ws://localhost/websocket으로 연결해야 함
				.setAllowedOrigins("*");
				//.withSockJS();//곽-있으면 연결안됨 (브라우져가 push가 안되면 poll방식 사용하는 건가?) <<< js도 sockjs를 사용해야 할 듯
	}
}

