package com.example.multipledatasource.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.multipledatasource.repository.sqlserver",
        entityManagerFactoryRef = "sqlserverEntityManagerFactory",
        transactionManagerRef = "sqlserverTransactionManager"
)
@Slf4j
public class SQLServerConfig {

    private final DataSourcePropertiesMap dataSourcePropertiesMap;
    private static final String DATASOURCE_KEY = "sqlserver";
    private static final String[] PACKAGES_TO_SCAN = {"com.example.multipledatasource.entity.sqlserver"};

    public SQLServerConfig(DataSourcePropertiesMap dataSourcePropertiesMap) {
        this.dataSourcePropertiesMap = dataSourcePropertiesMap;
    }

    @Bean(name = "sqlserverDataSource")
    public DataSource sqlserverDataSource() {
        log.info("Configuring SQL Server data source");
        
        DataSourcePropertiesMap.DataSourceProperties properties = 
                dataSourcePropertiesMap.getDataSources().get(DATASOURCE_KEY);
        
        if (properties == null) {
            throw new IllegalStateException("SQL Server datasource properties not found");
        }
        
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setSchema(properties.getSchema());
        dataSource.setDriverClassName(properties.getDriverClassName());
        
        return dataSource;
    }

    @Bean(name = "sqlserverEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sqlserverEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("sqlserverDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages(PACKAGES_TO_SCAN)
                .persistenceUnit(DATASOURCE_KEY)
                .build();
    }

    @Bean(name = "sqlserverTransactionManager")
    public PlatformTransactionManager sqlserverTransactionManager(
            @Qualifier("sqlserverEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
