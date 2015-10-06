package com.github.teamcalendar.comfigurations;

import java.util.Properties;

import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;

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

/**
 * Using for configure Hibernate.
 * Using Spring’s Environment for configure Session Factory.
 * 
 * @author storgovt
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.github.teamcalendar.configurations" })
@PropertySource({ "classpath:hibernate.properties", "classpath:liquibase/liquibase.properties" })
public class HibernateConfiguration
{
    @Autowired
    private Environment environment;

    /**
     * Using for create a {@link LocalSessionFactoryBean}
     * 
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(createDataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.github.teamcalendar.backend.entities" });
        sessionFactory.setHibernateProperties(buildHibernateProperties());

        return sessionFactory;
    }

    /**
     * Using for create a {@link DataSource}
     * 
     * @return
     */
    @Bean
    public DataSource createDataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));

        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager createTransactionManager(SessionFactory sessionFactory)
    {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    @Bean
    @Autowired
    public SpringLiquibase createLiquibaseBean()
    {
        SpringLiquibase liquibase = new SpringLiquibase();

        liquibase.setDataSource(createDataSource());
        liquibase.setChangeLog("classpath:liquibase/master.xml");
        liquibase.setContexts("dev");

        return liquibase;
    }

    /**
     * Build Hibernate properties
     * 
     * @return
     */
    private Properties buildHibernateProperties()
    {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

        return properties;
    }
}
