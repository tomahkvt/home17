package org.oa.getmac.config;

import java.io.IOException;

import java.util.List;
import org.apache.log4j.Logger;
import org.oa.getmac.model.Device;
import org.oa.getmac.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class ControlGetData {
	private static Logger log = Logger.getLogger(ControlGetData.class);
	private WebSocketSession session;
	private TreadGetDataFromDevice treadGetDataFromDevice;
	private boolean started = false;
	@Autowired
	private DeviceRepository deviceRepository;

	public WebSocketSession getSession() {
		return session;
	}

	public void setSession(WebSocketSession session) {

		this.session = session;

	}

	void send(String s) {
		try {
			session.sendMessage(new TextMessage(s));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void start() {
		List<Device> devices = deviceRepository.findEnabled();
		if ((treadGetDataFromDevice == null) || (treadGetDataFromDevice.getState() == Thread.State.TERMINATED)) {
			treadGetDataFromDevice = new TreadGetDataFromDevice(devices);
			treadGetDataFromDevice.setWebSocketSession(session);
			treadGetDataFromDevice.start();
			log.info("start");
			setStarted(true);
		}
	}

	public void stop() {
		treadGetDataFromDevice.doResume();
		treadGetDataFromDevice.interrupt();
		setStarted(false);
		log.info("stop");
	}

	public void pause() {
		treadGetDataFromDevice.doPause();
		log.info("pause");
	}

	public void resume() {
		treadGetDataFromDevice.doResume();
		log.info("resume");
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

}
