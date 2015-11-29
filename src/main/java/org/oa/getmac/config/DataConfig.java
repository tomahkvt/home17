package org.oa.getmac.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;


@Configuration
public class DataConfig {

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "org.oa.getmac.model" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/get_mac");
		dataSource.setUsername("root");
		dataSource.setPassword("usbw");
		dataSource.setMaxActive(5);

		return dataSource;
	}

	Properties hibernateProperties() {
		return new Properties() {
			{

				// setProperty("hibernate.hbm2ddl.auto", "validate");
				setProperty("hibernate.hbm2ddl.auto", "validate");
				setProperty("hibernate.connection.pool_size", "1");
				setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
				setProperty("show_sql", "true");
				setProperty("current_session_context_class", "thread");
			}
		};
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}

}