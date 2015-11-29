package org.oa.getmac.repository;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.getmac.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DeviceRepository {
	private static Logger log = Logger.getLogger(DeviceRepository.class);
	private SessionFactory sessionFactory;

	@Autowired
	public DeviceRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(Device item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(item);
		session.getTransaction().commit();
		session.close();
	}

	public void update(Device item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.update(item);
		session.getTransaction().commit();
		session.close();
	}

	public void delete(Device item) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(item);
		session.getTransaction().commit();
		log.info("delete");
		session.close();
	}

	public List<Device> findAll() {
		Session session = sessionFactory.openSession();
		// session.beginTransaction();

		Query query = session.createQuery("FROM Device");
		List<Device> result = query.list();

		// session.getTransaction().commit();
		session.close();
		return result;
	}

	public Device findById(int idDevice) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Device result = (Device) session.get(Device.class, idDevice);
		System.out.println(result);
		session.getTransaction().commit();
		session.close();
		return result;
	}

	public List<Device> findEnabled() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("FROM Device where switch_enable = 1");
		List<Device> result = query.list();

		session.getTransaction().commit();
		session.close();
		return result;

	}

}
