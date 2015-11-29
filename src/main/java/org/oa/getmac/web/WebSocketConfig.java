package org.oa.getmac.web;

import org.oa.getmac.config.ControlGetData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
	 
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(marcoHandler(), "/marco");
	}
	
	@Bean
	public MarcoHandler marcoHandler() {
		return new MarcoHandler();
	}

	@Bean
	@Scope("singleton")
	public ControlGetData controlGetData(){
		return new ControlGetData();
	}
}
