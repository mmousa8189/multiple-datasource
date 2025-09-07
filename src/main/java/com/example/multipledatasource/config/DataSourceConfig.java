package com.example.multipledatasource.config;

import com.example.multipledatasource.util.MultiRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableJpaRepositories(basePackages = {
    "com.example.multipledatasource.repository.postgresql",
    "com.example.multipledatasource.repository.sqlserver"
})
@EnableTransactionManagement
@Slf4j
public class DataSourceConfig {

    private final DataSourcePropertiesMap dataSourcePropertiesMap;

    private static final String[] PACKAGES_TO_SCAN = {
        "com.example.multipledatasource.entity.postgresql",
        "com.example.multipledatasource.entity.sqlserver"
    };
    private static final String PERSISTENCE_UNIT = "default";

    public DataSourceConfig(DataSourcePropertiesMap dataSourcePropertiesMap) {
        this.dataSourcePropertiesMap = dataSourcePropertiesMap;
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        MultiRoutingDataSource routingDataSource = new MultiRoutingDataSource();

        Map<Object, Object> targetDataSources = new HashMap<>();

        log.info("Loaded data sources: " + dataSourcePropertiesMap.getDataSources().keySet());

        dataSourcePropertiesMap.getDataSources().forEach((key, properties) -> {
            log.info("Configuring data source: " + key);

            HikariDataSource dataSource = new HikariDataSource();
            dataSource.setJdbcUrl(properties.getUrl());
            dataSource.setUsername(properties.getUsername());
            dataSource.setPassword(properties.getPassword());
            dataSource.setSchema(properties.getSchema());
            dataSource.setDriverClassName(properties.getDriverClassName());
            targetDataSources.put(key, dataSource);
        });

        routingDataSource.setTargetDataSources(targetDataSources);

        // Optionally set a default data source
        routingDataSource.setDefaultTargetDataSource(
                targetDataSources.values().iterator().next()
        );

        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages(PACKAGES_TO_SCAN)
                .persistenceUnit(PERSISTENCE_UNIT)
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
    