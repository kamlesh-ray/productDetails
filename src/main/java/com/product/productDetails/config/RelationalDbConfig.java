package com.product.productDetails.config;

import lombok.extern.slf4j.Slf4j;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(prefix = "app.database", name = "type", havingValue = "SQL", matchIfMissing = true)
@EnableJpaRepositories(basePackages = "com", entityManagerFactoryRef = "entityManagerLocalSessionFactory")
@Slf4j
public class RelationalDbConfig {

    @Value("${app.database.sql.url}")
    private String db_url;

    @Value("${app.database.sql.driverClassName}")
    private String driverClassName;

    @Value("${app.database.sql.username}")
    private String db_username;

    @Value("${app.database.sql.password}")
    private String db_password;

    @Value("${app.database.sql.database-platform}")
    private String db_dialect;

    public RelationalDbConfig() {
        log.info(">>>>>>>>>>>>>>>>>>>db type activated as : SQL<<<<<<<<<<<<<<<<<<<");
    }

    @Bean
    public LocalSessionFactoryBean entityManagerLocalSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", db_dialect);
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.hbm2ddl.auto", "update");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(db_url);
        dataSource.setUsername(db_username);
        dataSource.setPassword(db_password);
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(entityManagerLocalSessionFactory().getObject());
        return txManager;
    }

    @Bean
    ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}
