package com.ind.weighing.Indo_weighing.config;


import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;



@Configuration
@EnableSpringDataWebSupport
public class DatabaseConfig {
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@Value("spring.datasource.jndi-name.jdbc.indodb.url")
	private String mainJDBCUrl;
	
	@Value("spring.datasource.jndi-name.jdbc.indodb.username")
	private String mainJDBCUsername;
	
	@Value("spring.datasource.jndi-name.jdbc.indodb.password")
	private String mainJDBCPassword;
	
	
	@Bean(name="dataSource")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(applicationProperties.getIndodb().getDriverName());
		dataSource.setUrl(applicationProperties.getIndodb().getWsurl());
		dataSource.setUsername(applicationProperties.getIndodb().getWsurlUsername());
		dataSource.setPassword(applicationProperties.getIndodb().getWsurlPassword());
		return dataSource;
	}
	
	@Bean(name="indoDataEntityManager")
	public LocalContainerEntityManagerFactoryBean indoDataEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"com.ind.weighing.Indo_weighing.domain"});
		HibernateJpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(adaptor);
		HashMap<String, Object> property  = new HashMap<>();
		property.put("hibernate.hbm2ddl.auto", "update");
		property.put("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
		em.setJpaPropertyMap(property);
		return em;
	}
	
	@Bean
	@Primary
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(indoDataEntityManager().getObject());
		return transactionManager;
	}
}
