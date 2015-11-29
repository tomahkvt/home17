package org.oa.getmac.modelVirtual;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.oa.getmac.model.ArpTableStatic;
import org.oa.getmac.model.DhcpTable;
import org.oa.getmac.model.MacTable;

@Entity
@XmlRootElement(name = "mac_table")
public class MacTableSummary {

	@XmlElement(name = "MacTable")
	private MacTable macTable;

	@XmlElement(name = "ArpTableStatic")
	private ArpTableStatic arpTableStatic;

	@XmlElement(name = "DhcpTable")
	private DhcpTable dhcpTable;

	public MacTableSummary() {

	}

	public MacTableSummary(MacTable macTable, ArpTableStatic arpTableStatic, DhcpTable dhcpTable) {

		this.macTable = macTable;
		this.arpTableStatic = arpTableStatic;
		this.dhcpTable = dhcpTable;
	}

	public MacTable getMacTable() {
		return macTable;
	}

	public void setMacTable(MacTable macTable) {
		this.macTable = macTable;
	}

	public ArpTableStatic getArpTableStatic() {
		return arpTableStatic;
	}

	public void setArpTableStatic(ArpTableStatic arpTableStatic) {
		this.arpTableStatic = arpTableStatic;
	}

	public DhcpTable getDhcpTable() {
		return dhcpTable;
	}

	public void setDhcpTable(DhcpTable dhcpTable) {
		this.dhcpTable = dhcpTable;
	}
}
