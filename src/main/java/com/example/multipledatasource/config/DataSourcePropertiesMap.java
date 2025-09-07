package com.example.multipledatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.datasource")
@Getter
@Setter
public class DataSourcePropertiesMap {

    private Map<String, DataSourceProperties> dataSources = new HashMap<>();

    @Getter
    @Setter
    public static class DataSourceProperties {
        private String url;
        private String username;
        private String password;
        private String schema;
        private String driverClassName;
        
        // This single method handles both camelCase and kebab-case property binding
        public String getDriverClassName() {
            return driverClassName;
        }
        
        // This method handles kebab-case property binding (driver-class-name)
        public void setDriverClassname(String driverClassName) {
            this.driverClassName = driverClassName;
        }
    }

}
