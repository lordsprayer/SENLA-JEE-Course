package com.senla.courses;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    private static final String PROPERTY_MANE_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_MANE_HIBERNATE_USE_SQL_COMMENTS = "hibernate.use_sql_comments";
    private static final String PROPERTY_MANE_HIBERNATE_HDM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_MANE_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS = "hibernate.enable_lazy_load_no_trans";

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource ();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("jdbc.driverClassName")));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Autowired
    private Environment env;

    @Bean
    public JpaTransactionManager jpaTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(ClassUtils.getPackageName(Customer.class));
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
        return entityManagerFactoryBean;
    }

    private Properties jpaHibernateProperties() {

        Properties properties = new Properties();

        properties.put(PROPERTY_MANE_HIBERNATE_FORMAT_SQL, env.getProperty(PROPERTY_MANE_HIBERNATE_FORMAT_SQL));
        properties.put(PROPERTY_MANE_HIBERNATE_USE_SQL_COMMENTS, env.getProperty(PROPERTY_MANE_HIBERNATE_USE_SQL_COMMENTS));
        properties.put(PROPERTY_MANE_HIBERNATE_HDM2DDL_AUTO, env.getProperty(PROPERTY_MANE_HIBERNATE_HDM2DDL_AUTO));
        properties.put(PROPERTY_MANE_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS, env.getProperty(PROPERTY_MANE_HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS));

        return properties;
    }
}
