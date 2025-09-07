package com.example.multipledatasource.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.springframework.stereotype.Component;

import com.example.multipledatasource.util.DataSourceContextHolder;

@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(dataSource)")
    public void switchDataSource(DataSource dataSource) {
        DataSourceContextHolder.setDataSourceKey(dataSource.value());
    }

    @After("@annotation(dataSource)")
    public void clearDataSource(DataSource dataSource) {
        DataSourceContextHolder.clear();
    }
}