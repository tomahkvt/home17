package org.oa.getmac.shell;

import org.apache.log4j.Logger;

public class HPMac {
	private static Logger log = Logger.getLogger(HPMac.class);
	private MySsh ssh = null;

	public void CiscoCatalystMac() {
	}

	public void setssh(MySsh ssh) {
		this.ssh = ssh;
	}

	public int telnet(String ipAddr, String userName, String userPassword)
			throws CommandTimeOut {
		try {
			
			ssh.executeToHP("telnet " + ipAddr + "\n", "Username:");
			
			
			
		} catch (CommandTimeOut cto) {
			log.error("Check host IP Address");
			ssh.backToShell();
			return -1;
		}
		
		try{
		ssh.executeToHP(userName + "\n", "Password:");
		ssh.executeToHP(userPassword + "\n", ">");
		}catch(CommandTimeOut cto){
			log.error("Check Login or Password");
			ssh.backToShellq();
			return -1;
		}
		return 0;
	}

	public String ShowMacAddressTableToTable() throws CommandTimeOut {
		return ssh.executeToHP("show mac-address\n", ">");
	}

	public void exit() throws CommandTimeOut {
		ssh.executeToHP("exit\n", "~$");
	}

}
