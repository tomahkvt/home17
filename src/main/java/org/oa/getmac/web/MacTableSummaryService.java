package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.modelVirtual.MacTableSummary;
import org.oa.getmac.repository.MacTableSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api/mactablesummary")
public class MacTableSummaryService {
	private static Logger log = Logger.getLogger(MacTableSummaryService.class);
	@Autowired
	private MacTableSummaryRepository macTableSummaryRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<MacTableSummary> getAll(
			@RequestParam(value = "check_arp_static", defaultValue = "no") String checkArpStatic,
			@RequestParam(value = "check_dhcp_table", defaultValue = "no") String checkDhcpTable
			) {
		
		List<MacTableSummary> macs = macTableSummaryRepository.findAll(checkArpStatic, checkDhcpTable);
		return macs;
	}
	

	
	
}
