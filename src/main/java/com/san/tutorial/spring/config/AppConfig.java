package com.san.tutorial.spring.config;

import java.util.Properties;

// import javax.activation.DataSource;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.san.tutorial.spring.entity.User;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans( value = {
		@ComponentScan("com.san.tutorial.spring.dao"),
		@ComponentScan("com.san.tutorial.spring.service")
})
public class AppConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource getDataSource( ) {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		return (DataSource) dataSource;  /// cannot convert from BasicDataSource to DataSource
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource((javax.sql.DataSource) getDataSource()); // DataSource?

		Properties props = new Properties();
		props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		factoryBean.setHibernateProperties(props);
		factoryBean.setAnnotatedClasses(User.class);
		return factoryBean;

	}
	

	  @Bean
	  public HibernateTransactionManager transactionManager(SessionFactory factoryBean) {
	    return new HibernateTransactionManager(factoryBean);
	  }
}

