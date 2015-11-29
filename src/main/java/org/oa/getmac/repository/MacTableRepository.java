package org.oa.getmac.repository;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.getmac.model.MacTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MacTableRepository {

	private final SessionFactory sessionFactory;

	@Autowired
	public MacTableRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(MacTable item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "insert into mac_table (Switch_Address, Switch_Name, "
				+ "Switch_Port, Mac_Address, Time_collection)"
				+ " values (:Switch_Address, :Switch_Name, :Switch_Port, :Mac_Address, :Time_collection) "
				+ " ON DUPLICATE KEY UPDATE " + "Switch_Address = :Switch_Address, Switch_Name = :Switch_Name, "
				+ "Switch_Port = :Switch_Port, Mac_Address = :Mac_Address, Time_collection = :Time_collection";
		System.out.println(queryString);
		SQLQuery sqlQuery = session.createSQLQuery(queryString);
		sqlQuery.setParameter("Switch_Address", item.getSwitchAddress())
				.setParameter("Switch_Name", item.getSwitchName()).setParameter("Switch_Port", item.getSwitchPort())
				.setParameter("Mac_Address", item.getMacAddress())
				.setParameter("Time_collection", item.getTimeCollection());
		sqlQuery.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	public List<MacTable> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM MacTable");
		List<MacTable> result = query.list();

		session.getTransaction().commit();
		for (MacTable mac : result) {
			System.out.println(mac);
		}

		return result;
	}

}
