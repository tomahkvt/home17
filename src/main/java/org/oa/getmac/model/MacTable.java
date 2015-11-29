package org.oa.getmac.model;

import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "mac_table", uniqueConstraints = @UniqueConstraint(columnNames = { "Switch_Address", "Switch_Port",
		"Mac_Address" }) )
@XmlRootElement(name = "MacTable")
public class MacTable {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@XmlElement(name = "SwitchAddress")
	@Column(name = "Switch_Address", length = 50)
	private String switchAddress;
	@XmlElement(name = "SwitchName")
	@Column(name = "Switch_Name", length = 50)
	private String switchName;
	@XmlElement(name = "SwitchPort")
	@Column(name = "Switch_Port", length = 50)
	private String switchPort;
	@XmlElement(name = "MacAddress")
	@Column(name = "Mac_Address", length = 50)
	private String macAddress;
	@XmlElement(name = "TimeCollection")
	@Column(name = "Time_collection", length = 50)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SS", timezone = "GMT+2")
	private Date timeCollection;

	public MacTable() {

	}

	public MacTable(String switchAddress, String switchName, String switchPort, String macAddress,
			Date timeCollection) {
		super();
		this.switchAddress = switchAddress;
		this.switchName = switchName;
		this.switchPort = switchPort;
		this.macAddress = macAddress;
		this.timeCollection = timeCollection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSwitchAddress() {
		return switchAddress;
	}

	public void setSwitchAdress(String switchAddress) {
		this.switchAddress = switchAddress;
	}

	public String getSwitchName() {
		return switchName;
	}

	public void setSwitchName(String switchName) {
		this.switchName = switchName;
	}

	public String getSwitchPort() {
		return switchPort;
	}

	public void setSwitchPort(String switchPort) {
		this.switchPort = switchPort;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Date getTimeCollection() {
		return timeCollection;
	}

	public void setTimeCollection(Date date) {
		this.timeCollection = date;
	}

	@Override
	public String toString() {
		return "MacTable [id=" + id + ", switchAddress=" + switchAddress + ", switchName=" + switchName
				+ ", switchPort=" + switchPort + ", macAddress=" + macAddress + ", timeCollection=" + timeCollection
				+ "]";
	}

}
