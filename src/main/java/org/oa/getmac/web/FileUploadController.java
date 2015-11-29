package org.oa.getmac.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletResponse;
import org.oa.getmac.model.DhcpTable;
import org.oa.getmac.repository.DhcpTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {
	private DhcpTableRepository dhcpTableRepository;
	final static String FIRST_STRING = "IP-адрес клиента,Имя,Истечение срока аренды,Тип,Уникальный код,Описание,Защита доступа к сети,Срок действия надзора,Профиль фильтра,Политика";

	@Autowired
	FileUploadController(DhcpTableRepository dhcpTableRepository) {
		this.dhcpTableRepository = dhcpTableRepository;
	}

	@Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
	@RequestMapping(value = "uploadpage", method = RequestMethod.GET)
	String showUploadPage() {

		return "uploadpage";
	}

	@Secured({ "ROLE_OPERATOR", "ROLE_ADMIN" })
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file,
			HttpServletResponse response) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				String line = "";
				String cvsSplitBy = ",";
				BufferedWriter brw = new BufferedWriter(new FileWriter(dir.getAbsolutePath() + File.separator + "asd"));
				BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(serverFile.getAbsolutePath()), "Cp1251"));

				if ((line = br.readLine()) != null) {
					line = line.replace("\"", "");
					if (!line.equals(FIRST_STRING)) {
						System.out.println("Error:" + line);
						throw new Exception();
					}
				}
				DhcpTable dhcpTable;
				while ((line = br.readLine()) != null) {
					line = line.replace("\"", "");
					brw.write(line);
					brw.newLine();
					String[] field = line.split(cvsSplitBy, 10);

					dhcpTable = new DhcpTable(field[0], field[1], field[2], field[3], field[4], field[5], field[6],
							field[7], field[8], field[9]);

					System.out.println(dhcpTable);

					dhcpTableRepository.create(dhcpTable);
				}
				br.close();
				brw.close();
				System.out.println("Server File Location=" + serverFile.getAbsolutePath());
				return "You successfully uploaded file=" + file.getOriginalFilename();
			} catch (Exception e) {
				return "You failed to upload " + file.getOriginalFilename() + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + file.getOriginalFilename() + " because the file was empty.";
		}
	}

}
