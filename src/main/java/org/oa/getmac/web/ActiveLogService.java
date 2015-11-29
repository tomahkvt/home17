package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.config.ControlGetData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/log")
public class ActiveLogService {
	private static Logger log = Logger.getLogger(ActiveLogService.class);
	@Autowired
	private ControlGetData controlGetData;

	private boolean pause = false;

	@RequestMapping(value = "start", method = RequestMethod.GET)
	public @ResponseBody void start() {
		controlGetData.start();
	}

	@RequestMapping(value = "stop", method = RequestMethod.GET)
	public @ResponseBody void stop() {
		log.info("Stop process");
		controlGetData.stop();

	}

	@RequestMapping(value = "pause", method = RequestMethod.GET)
	public @ResponseBody String pause() {
		pause = !pause;
		if (pause == true) {
			controlGetData.pause();
			log.info("pause process");
			return "resume";
		} else {
			controlGetData.resume();
			log.info("resume process");
			return "pause";
		}
	}
}
