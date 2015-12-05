package org.oa.getmac.config;

import java.util.List;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Device;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class TreadGetDataFromDevice extends Thread {
	private static Logger log = Logger.getLogger(TreadGetDataFromDevice.class);
	private boolean pause = false;
	private WebSocketSession webSocketSession;
	private List<Device> devices;

	public TreadGetDataFromDevice(List<Device> devices) {
		this.devices = devices;
	}

	public void send(String s) {

		if ((webSocketSession != null) || webSocketSession.isOpen()) {
			try {
				webSocketSession.sendMessage(new TextMessage(s));
			} catch (Exception e) {
				this.doPause();
			}
		}
	}

	public void doPause() {
		this.pause = true;
	}

	public void doResume() {
		this.pause = false;
	}

	public void pauseTread() {

		while (pause) {
			yield();
		}
	}

	public void run() {
		this.send("Connect to ssh Server");

		for (int i = 1; i < 100; i++) {

			this.send(Integer.toString(i));
			pauseTread();

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				log.info("stoped process");

				break;
			}
		}
		this.send("Disconnet from ssh Server");
	}

	public WebSocketSession getWebSocketSession() {
		return webSocketSession;
	}

	public void setWebSocketSession(WebSocketSession webSocketSession) {
		this.webSocketSession = webSocketSession;
	}

}
