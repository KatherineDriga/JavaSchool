package com.tsystems.javaschool.uberbahn.webmain;

import com.tsystems.javaschool.uberbahn.entities.BaseEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.tsystems.javaschool.uberbahn.repositories")
@ComponentScan({"com.tsystems.javaschool.uberbahn"})
public class ApplicationConfiguration {

    @Bean
    @Profile("production")
    public DataSource dataSourceProd() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/uberbahn");
        dataSource.setUsername("uberbahn_webapp");
        dataSource.setPassword("123");
        return dataSource;
    }

    @Bean
    @Profile("development")
    public DataSource dataSourceDev() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.DERBY)
                .generateUniqueName(true)
                .build();
    }

    @Bean
    @Profile("production")
    public HibernateJpaVendorAdapter vendorAdapterProd() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(false);
        return vendorAdapter;
    }

    @Bean
    @Profile("development")
    public HibernateJpaVendorAdapter vendorAdapterDev() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.DERBY);
        vendorAdapter.setShowSql(true);
        vendorAdapter.setGenerateDdl(true);
        return vendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource, HibernateJpaVendorAdapter vendorAdapter) {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(BaseEntity.class.getPackage().getName());
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
