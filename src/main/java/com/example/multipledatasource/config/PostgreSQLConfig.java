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
        basePackages = "com.example.multipledatasource.repository.postgresql",
        entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "postgresqlTransactionManager"
)
@Slf4j
public class PostgreSQLConfig {

    private final DataSourcePropertiesMap dataSourcePropertiesMap;
    private static final String DATASOURCE_KEY = "postgresql";
    private static final String[] PACKAGES_TO_SCAN = {"com.example.multipledatasource.entity.postgresql"};

    public PostgreSQLConfig(DataSourcePropertiesMap dataSourcePropertiesMap) {
        this.dataSourcePropertiesMap = dataSourcePropertiesMap;
    }

    @Bean(name = "postgresqlDataSource")
    public DataSource postgresqlDataSource() {
        log.info("Configuring PostgreSQL data source");
        
        DataSourcePropertiesMap.DataSourceProperties properties = 
                dataSourcePropertiesMap.getDataSources().get(DATASOURCE_KEY);
        
        if (properties == null) {
            throw new IllegalStateException("PostgreSQL datasource properties not found");
        }
        
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setSchema(properties.getSchema());
        // Try to get driver class name from either camelCase or kebab-case property
        String driverClassName = properties.getDriverClassName();
        dataSource.setDriverClassName(driverClassName);
        
        return dataSource;
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("postgresqlDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages(PACKAGES_TO_SCAN)
                .persistenceUnit(DATASOURCE_KEY)
                .build();
    }

    @Bean(name = "postgresqlTransactionManager")
    public PlatformTransactionManager postgresqlTransactionManager(
            @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
