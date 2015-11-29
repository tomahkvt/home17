package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "arp_table_static")
@XmlRootElement(name = "ArpTableStatic")
public class ArpTableStatic {
	@Id
	@XmlElement(name = "DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@XmlElement(name = "hostMac")
	@Column(name = "host_mac", length = 50)
	private String hostMac;
	@XmlElement(name = "hostIp")
	@Column(name = "host_ip", length = 50)
	private String hostIp;
	@XmlElement(name = "hostName")
	@Column(name = "host_name", length = 50)
	private String hostName;
	@XmlElement(name = "commentary")
	@Column(name = "commentary", length = 50)
	private String commentary;

	public ArpTableStatic() {

	}

	public ArpTableStatic(String hostMac, String hostIp, String hostName, String commentary) {
		super();
		this.hostMac = hostMac;
		this.hostIp = hostIp;
		this.hostName = hostName;
		this.commentary = commentary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHostMac() {
		return hostMac;
	}

	public void setHostMac(String hostMac) {
		this.hostMac = hostMac;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	@Override
	public String toString() {
		return "ArpTableStatic [id=" + id + ", hostMac=" + hostMac + ", hostIp=" + hostIp + ", hostName=" + hostName
				+ ", commentary=" + commentary + "]";
	}

}
