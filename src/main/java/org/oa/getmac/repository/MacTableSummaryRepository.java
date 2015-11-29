package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.getmac.model.ArpTableStatic;
import org.oa.getmac.model.DhcpTable;
import org.oa.getmac.model.MacTable;
import org.oa.getmac.modelVirtual.MacTableSummary;
import org.oa.getmac.web.MacTableSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class MacTableSummaryRepository {

	private static Logger log = Logger.getLogger(MacTableSummaryService.class);

	private final SessionFactory sessionFactory;

	@Autowired
	public MacTableSummaryRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<MacTableSummary> findAll(String checkArpStatic, String checkDhcpTable) {
		List<MacTableSummary> macTables = new ArrayList<MacTableSummary>();
		Session session = sessionFactory.openSession();
		int numberMatchedOption = 0;
		session.beginTransaction();

		String selectSQLQuery = "Select {mac.*}";

		if (checkArpStatic.equals("yes")) {
			selectSQLQuery += ", {arp.*}";
		}

		if (checkDhcpTable.equals("yes")) {
			selectSQLQuery += ", {dhcp.*}";
		}

		selectSQLQuery += " from mac_table mac";

		if (checkArpStatic.equals("yes")) {
			selectSQLQuery += " left join arp_table_static arp on mac.Mac_Address = arp.host_mac";
		}

		if (checkDhcpTable.equals("yes")) {
			selectSQLQuery += " left join dhcp_table dhcp on mac.Mac_Address = dhcp.mac_address";
		}
		// String fromSQLQuery = " limit 1,10";
		String fromSQLQuery = " ";
		log.info("checkArpStatic =" + checkArpStatic);
		log.info("checkDhcpTable =" + checkDhcpTable);

		log.info(selectSQLQuery + fromSQLQuery);

		SQLQuery query = session.createSQLQuery(selectSQLQuery + fromSQLQuery).addEntity("mac", MacTable.class);

		if (checkArpStatic.equals("yes")) {
			query.addEntity("arp", ArpTableStatic.class);
			numberMatchedOption++;
		}

		if (checkDhcpTable.equals("yes")) {
			query.addEntity("dhcp", DhcpTable.class);
			numberMatchedOption++;
		}

		if ((!checkArpStatic.equals("yes")) && (!checkDhcpTable.equals("yes"))) {

			List<MacTable> rows = query.list();
			session.getTransaction().commit();
			session.close();
			for (MacTable row : rows) {

				macTables.add(new MacTableSummary(row, null, null));
			}
		} else {
			List<Object[]> rows = query.list();
			session.getTransaction().commit();
			session.close();
			int number = 0;
			for (Object[] row : rows) {
				number = 0;
				MacTableSummary macTableSummary = new MacTableSummary();
				macTableSummary.setMacTable((MacTable) row[0]);
				if (checkArpStatic.equals("yes")) {
					++number;
					if ((ArpTableStatic) row[number] == null) {
						macTableSummary.setArpTableStatic(new ArpTableStatic("", "", "", ""));
					} else {
						macTableSummary.setArpTableStatic((ArpTableStatic) row[number]);
					}

				}

				if (checkDhcpTable.equals("yes")) {
					++number;
					if ((DhcpTable) row[number] == null) {
						macTableSummary.setDhcpTable(new DhcpTable("", "", "", "", "", "", "", "", "", ""));
					} else {
						macTableSummary.setDhcpTable((DhcpTable) row[number]);
					}

				}
				macTables.add(macTableSummary);
			}
		}
		return macTables;
	}
}
