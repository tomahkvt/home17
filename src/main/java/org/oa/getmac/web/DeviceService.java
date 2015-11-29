package org.oa.getmac.web;

import org.apache.log4j.Logger;
import org.oa.getmac.model.Device;
import org.oa.getmac.repository.DeviceRepository;
import org.oa.getmac.repository.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping("/api/device")
public class DeviceService {
	private static Logger log = Logger.getLogger(DeviceService.class);
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private DeviceTypeRepository deviceTypeRepository;

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Device getAll(@PathVariable int idDevice) {
		log.info("View Device with id=" + idDevice);
		Device device = deviceRepository.findById(idDevice);
		return device;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Device> getAll() {
		List<Device> devices = deviceRepository.findAll();
		return devices;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody Device add(@RequestBody Device device) {
		deviceRepository.create(device);
		return device;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	public @ResponseBody Device update(@RequestBody Device device) {
		deviceRepository.update(device);
		return device;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public @ResponseBody Device delete(@PathVariable int id) {
		Device device = new Device();
		device.setId(id);
		deviceRepository.delete(device);
		return device;
	}
}
