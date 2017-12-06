package net.starchl.aabap.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("net.starchl.aabap.config")
@EnableTransactionManagement
@PropertySource(value = { "classpath:hibernate_config.properties" })
public class HibernateConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		ds.setUrl(environment.getRequiredProperty("jdbc.url"));
		ds.setUsername(environment.getRequiredProperty("jdbc.username"));
		ds.setPassword(environment.getRequiredProperty("jdbc.password"));
		return ds;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sb = new LocalSessionFactoryBean();
		sb.setDataSource(dataSource());
		sb.setPackagesToScan(new String[] { "net.starchl.aabap.model" });
		sb.setHibernateProperties(hibernateProperties());
		return sb;
	}

	private Properties hibernateProperties() {
		Properties hp = new Properties();
		hp.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		hp.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		hp.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		return hp;
	}

	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager tm = new HibernateTransactionManager(s);
		return tm;
	}
}
