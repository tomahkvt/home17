package org.oa.getmac.shell;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.MacTable;
import org.oa.getmac.repository.MacTableRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CiscoCatalystMac {
	private static Logger log = Logger.getLogger(CiscoCatalystMac.class);
	private MySsh ssh = null;
	private Device device;
	private String showMacAddress;
	
	@Autowired
	private MacTableRepository macTableRepository;
	

	public void CiscoCatalystMac() {
	}

	public void setssh(MySsh ssh) {
		this.ssh = ssh;
	}

	public int telnet(String ipAddr, String userName, String userPassword)
			throws CommandTimeOut {
		try {
			ssh.execute("telnet " + ipAddr + "\n", "Username:");
			
		} catch (CommandTimeOut cto) {
			log.error("Check host IP Address");
			ssh.backToShell();
			return -1;
		}
		
		try{
		ssh.execute(userName + "\n", "Password:");
		ssh.execute(userPassword + "\n", ">");
		}catch(CommandTimeOut cto){
			log.error("Check Login or Password");
			ssh.backToShellq();
			return -1;
		}
		return 0;
	}

	public int telnet(Device device)
	
			throws CommandTimeOut {
		this.device = device;
		try {
			ssh.execute("telnet " + device.getDeviceIp() + "\n", "Username:");
			
		} catch (CommandTimeOut cto) {
			log.error("Check host IP Address");
			ssh.backToShell();
			return -1;
		}
		
		try{
		ssh.execute(device.getDeviceUsername() + "\n", "Password:");
		ssh.execute(device.getDevicePassw() + "\n", ">");
		}catch(CommandTimeOut cto){
			log.error("Check Login or Password");
			ssh.backToShellq();
			return -1;
		}
		return 0;
	}
	
	public void showMacAddressTable() throws CommandTimeOut {
		this.showMacAddress =  ssh.execute("show mac address-table\n", ">");

	}
	
	
	public void putDataToDB()
	{
		log.info(showMacAddress);
		Pattern pattern2900 = Pattern.compile("(\\d{1,4}) +([0-9a-f]{4}).([0-9a-f]{4}).([0-9a-f]{4}) +DYNAMIC +(Gi|Fa)(\\d{1})/(\\d{1,2})");
		Matcher matcher = pattern2900.matcher(this.showMacAddress);

		
		String macAddress;
		String switchPort;
		String vlan;
		while (matcher.find()) {
			vlan = matcher.group(1);
			System.out.println(vlan);
			macAddress = matcher.group(2) + matcher.group(3) + matcher.group(4);
			switchPort = matcher.group(5) + matcher.group(6) + "/";
			switchPort +=String.format("%02d",Integer.parseInt(matcher.group(7)));
			Date timeCollection = new Date();
			MacTable macTable = new MacTable(device.getDeviceIp(), device.getDeviceName(), switchPort, macAddress, timeCollection);
			System.out.println(macTable);
			macTableRepository.create(macTable);
			
		}
	}

	public void exit() throws CommandTimeOut {
		ssh.execute("exit\n", "~$");
	}

}
