package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.DeviceType;
import org.oa.getmac.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller
@RequestMapping("/api/deviceType")
public class DeviceTypeService {
	private static Logger log = Logger.getLogger(DeviceTypeService.class);

	@Autowired
	private DeviceTypeRepository deviceTypeRepository;
	
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<DeviceType> getAll() {
		
		List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
		return deviceTypes;
	}
}
