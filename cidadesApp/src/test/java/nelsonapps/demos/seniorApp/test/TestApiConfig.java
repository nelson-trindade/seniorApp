package nelsonapps.demos.seniorApp.test;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({"classpath:testDbConfig.properties"})
@EnableJpaRepositories(
		basePackages={"nelsonapps.demos.seniorApp.repository"},
		entityManagerFactoryRef="testEntityManagerFactory",
        transactionManagerRef="testTransactionManager")
@ComponentScan(basePackages={"nelsonapps.demos.seniorApp"},
               excludeFilters={@Filter(type=FilterType.ASPECTJ,pattern="nelson.demos.seniorApp.controller.*")})
public class TestApiConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean testEntityManagerFactory(){
		LocalContainerEntityManagerFactoryBean entityManagerFac = 
				new LocalContainerEntityManagerFactoryBean();
		entityManagerFac.setDataSource(testDataSource());
		entityManagerFac.setPackagesToScan(new String[]{"nelsonapps.demos.seniorApp.entities"});
		entityManagerFac.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFac.setJpaProperties(testJpaProperties());
		return entityManagerFac;
		
	}

	Properties testJpaProperties() {
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		jpaProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		return jpaProperties;
	}

	@Bean
	public DataSource testDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		return dataSource;
	}
	
	
	@Bean
	public PlatformTransactionManager testTransactionManager(){
		return new JpaTransactionManager(testEntityManagerFactory().getObject());
	}
	
}
