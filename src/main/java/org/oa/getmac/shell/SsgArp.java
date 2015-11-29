package org.oa.getmac.shell;

import org.apache.log4j.Logger;

public class SsgArp {
	private static Logger log = Logger.getLogger(SsgArp.class);
	private MySsh ssh = null;

	void CiscoCatalystMac() {
	}

	void setssh(MySsh ssh) {
		this.ssh = ssh;
	}

	int telnet(String ipAddr, String userName, String userPassword)
			throws CommandTimeOut {
		try {
			ssh.execute("telnet " + ipAddr + "\n", "login:");
			
		} catch (CommandTimeOut cto) {
			log.error("Check host IP Address");
			ssh.backToShell();
			return -1;
		}
		
		try{
		ssh.execute(userName + "\n", "password:");
		ssh.execute(userPassword + "\n", ">");
		}catch(CommandTimeOut cto){
			log.error("Check Login or Password");
			ssh.backToShellq();
			return -1;
		}
		return 0;
	}

	String GetArpTableToTable() throws CommandTimeOut {
		return ssh.execute("get arp\n", ">");

	}

	void exit() throws CommandTimeOut {
		ssh.execute("exit\n", "~$");
	}

}
