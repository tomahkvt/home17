package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RestJspController {
	private static Logger log = Logger.getLogger(RestJspController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String home(Model model) {
		return "index";
	}

	@RequestMapping(value = "device", method = RequestMethod.GET)

	public String restDevice(Model model) {
		return "restdevices";
	}

	@RequestMapping(value = "mactable", method = RequestMethod.GET)
	public String restMacAdress(Model model) {
		return "restmactable";
	}

	@RequestMapping(value = "console", method = RequestMethod.GET)
	public String restConsole(Model model) {
		return "restconsole";
	}
}
