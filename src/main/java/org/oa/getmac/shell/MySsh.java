package org.oa.getmac.shell;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.*;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.jcraft.jsch.*;

public class MySsh {
	private Channel channel;
	private InputStream inssh;
	private OutputStream outssh;
	private StringBuilder sb = new StringBuilder();
	private Session session;
	private static final String SSH_SERVER = "10.1.1.254";
	private static final String SSH_SERVER_USER = "login";
	private static final String SSH_SERVER_PASSWORD = "password";
	private static final int TIMEOUT_EXECUTE = 5000;//5000 ms
	public static Logger log = Logger.getLogger(MySsh.class.getName());
	
	String convertToHP(String s1){
		return  s1.getBytes(Charset.forName("ISO-8859-1"))+"";
	}
	
	String convertFromHP(String s1){
		String s2 = s1.replaceAll("\u001b\\[24;1H","\n"); //Esc[Line;ColumnH
		s2 = s2.replaceAll("\\[24;1H","\n"); //Esc[Line;ColumnH
		s2 = s2.replaceAll("\u001b\\[\\d{1,2};\\d{1,2}H",""); //Esc[Line;ColumnH
		s2 = s2.replaceAll("\u001b\\[\\?\\d{1,2}h",""); //Esc[?25h
		s2 = s2.replaceAll("\u001b\\[2J",""); //Esc[2J
		s2 = s2.replaceAll("\u001b\\[\\?7l",""); //Esc[7l
		s2 = s2.replaceAll("\u001b\\[\\d{1,2};\\d{1,2}r",""); //Esc[3;23r
		s2 = s2.replaceAll("\u001b\\[\\?6l",""); //Esc[?6l
		s2 = s2.replaceAll("\u001b\\[2K",""); //Esc[2K
		s2 = s2.replaceAll("\\[\\d{1,2};\\d{1,2}H",""); //[24;11
		s2 = s2.replaceAll("\\[\\?\\d{1,2}h",""); //[?25h
		s2 = s2.replaceAll("\\[\\?\\d{1,2}l",""); //[?25l
		s2 = s2.replaceAll("\\[2J\\[1;24r",""); //[2J[1;24r
		s2 = s2.replaceAll("\\[1;24r",""); //[1;24r
		return s2;
	}
	
	public void backToShell() throws CommandTimeOut{
		char ctrlBreak = (char)3;
		execute(ctrlBreak+"","~$");
	}
	
	public void backToShellq() throws CommandTimeOut{
		char ctrlBreak = (char)29;
		execute(ctrlBreak+"","telnet>");
		execute("q\n","~$");
	}
	
	
	
	public String executeToHP(String command, String wait_for) throws CommandTimeOut {
		int ch;
		long timeStart = 0;
	
		try {
			String convertedCommand = convertFromHP(command);
			log.debug(convertedCommand);
			outssh.write(convertedCommand.getBytes());
			outssh.flush();
			timeStart = System.currentTimeMillis();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (sb.length() != 0)
			sb.delete(0, sb.length());
		try {
			int index;
			int lastByteBuffer = 0;
			byte[] buffer=new byte[1024];
			while (true) {
				if(inssh.available() > 0){
					lastByteBuffer = inssh.read(buffer);
					
					String temp = new String(buffer,0,lastByteBuffer);
					temp = convertFromHP(temp);
					log.info(temp);
					sb.append(temp);
				 
				if (-1 != (index = sb.indexOf("--More--"))) {
					sb.delete(index, index + 8);
					outssh.write(" ".getBytes());
					outssh.flush();
					timeStart = System.currentTimeMillis();
				}

				if (-1 != (index = sb.indexOf("-- MORE --"))) {
					sb.delete(index, index + 10);
					outssh.write(" ".getBytes());
					outssh.flush();
					timeStart = System.currentTimeMillis();
				}
				
				if (-1 != (index = sb.indexOf("Do you want to log out [y/n]?"))) {
					sb.delete(index, index + "Do you want to log out [y/n]?".length());
					outssh.write("y".getBytes());
					outssh.flush();
				}
				
				if (-1 != (index = sb.indexOf("Press any key to continue"))) {
					sb.delete(index, index + "Press any key to continue".length());
					outssh.write(" ".getBytes());
					outssh.flush();
					timeStart = System.currentTimeMillis();
				}
				
				if (-1 != (index = sb.indexOf("--- more ---"))) {
					sb.delete(index, index + "--- more ---".length());
					outssh.write(" ".getBytes());
					outssh.flush();
					timeStart = System.currentTimeMillis();
				}


				if (-1 != sb.indexOf(wait_for))
					break;
				}
				if ((System.currentTimeMillis() - timeStart) > TIMEOUT_EXECUTE){
					//System.out.println(sb);
					throw new CommandTimeOut();
				}
			}
			String stemp1 = sb.toString().replace(
					"          ", "");
			
			//System.out.println(sb);
			
			sb.append(stemp1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
	public String execute(String command, String wait_for) throws CommandTimeOut {
		int ch;
		long timeStart = 0;
		
		try {
			log.debug(command);
			outssh.write(command.getBytes());
			outssh.flush();
			timeStart = System.currentTimeMillis();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (sb.length() != 0)
			sb.delete(0, sb.length());
		try {
			int index;
			int lastByteBuffer = 0;
			byte[] buffer=new byte[1024];
			while (true) {
				if(inssh.available() > 0){
					lastByteBuffer = inssh.read(buffer);
					String temp = new String(buffer,0,lastByteBuffer);
					log.info(temp);
					sb.append(temp);
					if (-1 != (index = sb.indexOf("--More--"))) {
						sb.delete(index, index + 8);
						outssh.write(" ".getBytes());
						outssh.flush();
						timeStart = System.currentTimeMillis();
					}

					if (-1 != (index = sb.indexOf("-- MORE --"))) {
						sb.delete(index, index + 10);
						outssh.write(" ".getBytes());
						outssh.flush();
						timeStart = System.currentTimeMillis();
					}
					
					if (-1 != (index = sb.indexOf("Do you want to log out [y/n]?"))) {
						sb.delete(index, index + "Do you want to log out [y/n]?".length());
						outssh.write("y".getBytes());
						outssh.flush();
					}
					
					if (-1 != (index = sb.indexOf("Press any key to continue"))) {
						sb.delete(index, index + "Press any key to continue".length());
						outssh.write(" ".getBytes());
						outssh.flush();
						timeStart = System.currentTimeMillis();
					}
					
					if (-1 != (index = sb.indexOf("--- more ---"))) {
						sb.delete(index, index + "--- more ---".length());
						outssh.write(" ".getBytes());
						outssh.flush();
						timeStart = System.currentTimeMillis();
					}

				if (-1 != sb.indexOf(wait_for))
					break;
				}
				if ((System.currentTimeMillis() - timeStart) > TIMEOUT_EXECUTE){
					//System.out.println(sb);
					throw new CommandTimeOut();
				}
			}
			String stemp1 = sb.toString().replace(
					"          ", "");
			
			//System.out.println(sb);
			
			sb.append(stemp1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public int connect(String wait_for) {
		try {

			JSch jsch = new JSch();
			session = jsch.getSession(SSH_SERVER_USER, SSH_SERVER, 22);
			session.setPassword(SSH_SERVER_PASSWORD);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect(20000);// timeoutconnect 20c
			
			
			channel = session.openChannel("shell");
			
			
			inssh = new BufferedInputStream(channel.getInputStream());
			outssh = channel.getOutputStream();
			
			
			channel.connect();
			StringBuilder sb = new StringBuilder();
			int ch;
			if (sb.length() != 0)
				sb.delete(0, sb.length());
			while ((ch = inssh.read()) != -1) {
				//log.info((char) ch);
				sb.append((char) ch);
				
				if (-1 != sb.indexOf(wait_for))
					break;
			}
			// System.out.println(sb);
		} catch (JSchException e2) {
			switch (e2.getMessage()) {
			case "timeout: socket is not established":
				log.error(e2.getMessage());
				log.error("SSH Server not answer");
				break;
			case "Auth fail":
				log.error(e2.getMessage());
				log.error("Check login or password SSH Server");
				break;
			default:
				log.error(e2.getMessage());
				break;
			}
			session.disconnect();
			return -1;
		} catch (Exception e) {
			log.error(e);
		}
		return 0;
	}

	public int disconnect() {
		try {
			inssh.close();
			outssh.close();
			channel.disconnect();
			session.disconnect();

		} catch (IOException e) {
			log.error(e);
		}
		return -1;
	}
	
	
}
