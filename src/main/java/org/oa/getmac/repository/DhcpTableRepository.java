package org.oa.getmac.repository;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.getmac.model.Device;
import org.oa.getmac.model.DhcpTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class DhcpTableRepository {

	private SessionFactory sessionFactory;

	@Autowired
	public DhcpTableRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(DhcpTable item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String queryString = "insert into dhcp_table (description, duration, "
				+ "ip_Address, mac_address, name, ip_type, politics, " + " profile, securityAccess, termination) "
				+ " values (:description, :duration, " + ":ip_Address, :mac_address, :name, :ip_type, :politics, "
				+ " :profile, :securityAccess, :termination) " + " ON DUPLICATE KEY UPDATE "
				+ "description = :description, duration = :duration, ip_Address = :ip_Address,"
				+ "mac_address = :mac_address, name = :name, ip_type = :ip_type, "
				+ "politics = :politics, profile = :profile, securityAccess = :securityAccess, termination = :termination";
		SQLQuery sqlQuery = session.createSQLQuery(queryString);
		sqlQuery.setParameter("description", item.getDescription()).setParameter("duration", item.getDuration())
				.setParameter("ip_Address", item.getHostIP()).setParameter("mac_address", item.getHostMac())
				.setParameter("name", item.getHostName()).setParameter("ip_type", item.getIpType())
				.setParameter("politics", item.getPolitics()).setParameter("profile", item.getProfile())
				.setParameter("securityAccess", item.getSecurityAccess())
				.setParameter("termination", item.getTermination());
		sqlQuery.executeUpdate();

		session.getTransaction().commit();
		session.close();
	}

	public void update(DhcpTable item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
	}

	public void delete(DhcpTable item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
	}

	public List<DhcpTable> findAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Device");
		List<DhcpTable> result = query.list();

		session.getTransaction().commit();
		return result;
	}

	public Device findById(int idDevice) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Device result = (Device) session.get(Device.class, idDevice);
		System.out.println(result);
		session.getTransaction().commit();
		return result;
	}

}
