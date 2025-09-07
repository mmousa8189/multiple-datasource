package com.example.multipledatasource.config;

import com.example.multipledatasource.util.MultiRoutingDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@Slf4j
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource routingDataSource(
            @Qualifier("postgresqlDataSource") DataSource postgresqlDataSource,
            @Qualifier("sqlserverDataSource") DataSource sqlserverDataSource) {
        
        MultiRoutingDataSource routingDataSource = new MultiRoutingDataSource();
        
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("postgresql", postgresqlDataSource);
        targetDataSources.put("sqlserver", sqlserverDataSource);
        
        routingDataSource.setTargetDataSources(targetDataSources);
        
        // Set PostgreSQL as the default data source
        routingDataSource.setDefaultTargetDataSource(postgresqlDataSource);
        
        routingDataSource.afterPropertiesSet();
        
        log.info("Configured routing data source with targets: {}", targetDataSources.keySet());
        
        return routingDataSource;
    }
}
    