package org.oa.getmac.onlinelog;

import java.io.IOException;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorCode;
import org.apache.log4j.spi.LoggingEvent;
import org.oa.getmac.config.ControlGetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;

public class WebConsoleAppender extends AppenderSkeleton {
	@Autowired
	private ControlGetData controlGetData;
	private static Logger log = Logger.getLogger(WebConsoleAppender.class);

	@Override
	public void append(LoggingEvent event) {

		if (this.layout == null) {
			errorHandler.error("No layout for appender " + name, null, ErrorCode.MISSING_LAYOUT);
			return;
		}

		if (controlGetData.getSession().isOpen()) {
			try {
				controlGetData.getSession().sendMessage(new TextMessage(this.layout.format(event)));
			} catch (IOException e) {
				log.info("WebSoccket in close");

			}
		}

		if (controlGetData == null) {
			System.out.println("controlGetData.getSession() null");
		}
		System.out.println(this.layout.format(event));
	}

	@Override
	public void close() {

	}

	public boolean requiresLayout() {
		return true;
	}

}