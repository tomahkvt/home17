package org.oa.getmac.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Device")
@XmlRootElement(name = "Device")
public class Device {
	@Id
	@XmlElement(name = "DT_RowId")
	@JsonProperty("DT_RowId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne()
	@JoinColumn(name = "id_device_type")
	@XmlElement(name = "deviceType")
	@JsonProperty("deviceType")
	private DeviceType deviceType;

	@Column(name = "device_ip", length = 50)
	private String deviceIp;
	@XmlElement
	@Column(name = "device_name", length = 50)
	private String deviceName;
	@XmlElement
	@Column(name = "device_username", length = 50)
	private String deviceUsername;
	@XmlElement
	@Column(name = "device_passw", length = 50)
	private String devicePassw;
	@XmlElement
	@Column(name = "switch_enable", length = 50)
	private boolean switchEnable;

	public Device() {

	}

	public Device(DeviceType idDeviceType, String deviceIp, String deviceName, String deviceUsername,
			String devicePassw, boolean switchEnable) {
		super();
		this.deviceType = idDeviceType;
		this.deviceIp = deviceIp;
		this.deviceName = deviceName;
		this.deviceUsername = deviceUsername;
		this.devicePassw = devicePassw;
		this.switchEnable = switchEnable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceUsername() {
		return deviceUsername;
	}

	public void setDeviceUsername(String deviceUsername) {
		this.deviceUsername = deviceUsername;
	}

	public String getDevicePassw() {
		return devicePassw;
	}

	public void setDevicePassw(String devicePassw) {
		this.devicePassw = devicePassw;
	}

	public boolean isSwitchEnable() {
		return switchEnable;
	}

	public void setSwitchEnable(boolean switchEnable) {
		this.switchEnable = switchEnable;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", idDeviceType=" + deviceType + ", deviceIp=" + deviceIp + ", deviceName="
				+ deviceName + ", deviceUsername=" + deviceUsername + ", devicePassw=" + devicePassw + ", switchEnable="
				+ switchEnable + "]";
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

}
