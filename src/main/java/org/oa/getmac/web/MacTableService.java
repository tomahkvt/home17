package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.MacTable;
import org.oa.getmac.repository.MacTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/mactable")
public class MacTableService {
	private static Logger log = Logger.getLogger(MacTableService.class);
	@Autowired
	private MacTableRepository macTableRepository;
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MacTable> getAll() {
		List<MacTable> macs = macTableRepository.findAll();
		return macs;
	}
}
