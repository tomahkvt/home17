package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "dhcp_table", uniqueConstraints = @UniqueConstraint(columnNames = { "ip_Address", "mac_address" }) )

@XmlRootElement(name = "DhcpTable")
public class DhcpTable {
	@Id
	@XmlElement(name = "DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@XmlElement(name = "ipaddress")
	@Column(name = "ip_Address", length = 50)
	private String hostIP;

	@XmlElement(name = "hostName")
	@Column(name = "name", length = 50)
	private String hostName;

	@XmlElement(name = "termination")
	@Column(name = "termination", length = 50)
	private String termination;

	@XmlElement(name = "IpType")
	@Column(name = "ip_type", length = 50)
	private String ipType;

	@XmlElement(name = "hostMac")
	@Column(name = "mac_address", length = 50)
	private String hostMac;

	@XmlElement(name = "description")
	@Column(name = "description", length = 150)
	private String description;

	@XmlElement(name = "securityAccess")
	@Column(name = "securityAccess", length = 50)
	private String securityAccess;

	@XmlElement(name = "duration")
	@Column(name = "duration", length = 50)
	private String duration;

	@XmlElement(name = "profile")
	@Column(name = "profile", length = 50)
	private String profile;

	@XmlElement(name = "politics")
	@Column(name = "politics", length = 50)
	private String politics;

	public DhcpTable() {

	}

	public DhcpTable(String hostIP, String hostName, String termination, String ipType, String hostMac,
			String description, String securityAccess, String duration, String profile, String politics) {

		this.hostIP = hostIP;
		this.hostName = hostName;
		this.termination = termination;
		this.ipType = ipType;
		this.hostMac = hostMac;
		this.description = description;
		this.securityAccess = securityAccess;
		this.duration = duration;
		this.profile = profile;
		this.politics = politics;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getTermination() {
		return termination;
	}

	public void setTermination(String termination) {
		this.termination = termination;
	}

	public String getIpType() {
		return ipType;
	}

	public void setIpType(String ipType) {
		this.ipType = ipType;
	}

	public String getHostMac() {
		return hostMac;
	}

	public void setHostMac(String hostMac) {
		this.hostMac = hostMac;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSecurityAccess() {
		return securityAccess;
	}

	public void setSecurityAccess(String securityAccess) {
		this.securityAccess = securityAccess;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getPolitics() {
		return politics;
	}

	public void setPolitics(String politics) {
		this.politics = politics;
	}

	@Override
	public String toString() {
		return "DhcpTable [id=" + id + ", hostIP=" + hostIP + ", hostName=" + hostName + ", termination=" + termination
				+ ", ipType=" + ipType + ", hostMac=" + hostMac + ", description=" + description + ", securityAccess="
				+ securityAccess + ", duration=" + duration + ", profile=" + profile + ", politics=" + politics + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hostIP == null) ? 0 : hostIP.hashCode());
		result = prime * result + ((hostMac == null) ? 0 : hostMac.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DhcpTable other = (DhcpTable) obj;
		if (hostIP == null) {
			if (other.hostIP != null)
				return false;
		} else if (!hostIP.equals(other.hostIP))
			return false;
		if (hostMac == null) {
			if (other.hostMac != null)
				return false;
		} else if (!hostMac.equals(other.hostMac))
			return false;
		return true;
	}

	/*
	 * @Override public int hashCode() { final int prime = 31; int result = 1;
	 * result = prime * result + ((description == null) ? 0 :
	 * description.hashCode()); result = prime * result + ((duration == null) ?
	 * 0 : duration.hashCode()); result = prime * result + ((hostIP == null) ? 0
	 * : hostIP.hashCode()); result = prime * result + ((hostMac == null) ? 0 :
	 * hostMac.hashCode()); result = prime * result + ((hostName == null) ? 0 :
	 * hostName.hashCode()); result = prime * result + ((ipType == null) ? 0 :
	 * ipType.hashCode()); result = prime * result + ((politics == null) ? 0 :
	 * politics.hashCode()); result = prime * result + ((profile == null) ? 0 :
	 * profile.hashCode()); result = prime * result + ((securityAccess == null)
	 * ? 0 : securityAccess.hashCode()); result = prime * result + ((termination
	 * == null) ? 0 : termination.hashCode()); return result; }
	 * 
	 * 
	 * @Override public boolean equals(Object obj) { if (this == obj) return
	 * true; if (obj == null) return false; if (getClass() != obj.getClass())
	 * return false; DhcpTable other = (DhcpTable) obj; if (description == null)
	 * { if (other.description != null) return false; } else if
	 * (!description.equals(other.description)) return false; if (duration ==
	 * null) { if (other.duration != null) return false; } else if
	 * (!duration.equals(other.duration)) return false; if (hostIP == null) { if
	 * (other.hostIP != null) return false; } else if
	 * (!hostIP.equals(other.hostIP)) return false; if (hostMac == null) { if
	 * (other.hostMac != null) return false; } else if
	 * (!hostMac.equals(other.hostMac)) return false; if (hostName == null) { if
	 * (other.hostName != null) return false; } else if
	 * (!hostName.equals(other.hostName)) return false; if (ipType == null) { if
	 * (other.ipType != null) return false; } else if
	 * (!ipType.equals(other.ipType)) return false; if (politics == null) { if
	 * (other.politics != null) return false; } else if
	 * (!politics.equals(other.politics)) return false; if (profile == null) {
	 * if (other.profile != null) return false; } else if
	 * (!profile.equals(other.profile)) return false; if (securityAccess ==
	 * null) { if (other.securityAccess != null) return false; } else if
	 * (!securityAccess.equals(other.securityAccess)) return false; if
	 * (termination == null) { if (other.termination != null) return false; }
	 * else if (!termination.equals(other.termination)) return false; return
	 * true; }
	 */

}
