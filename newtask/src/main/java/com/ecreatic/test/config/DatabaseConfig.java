package com.ecreatic.test.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class DatabaseConfig {
    @Bean
    @Primary
    public DataSource dataSource() {
        final SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:~/users;DB_CLOSE_ON_EXIT=TRUE");
        dataSource.setUsername("sa");
        dataSource.setPassword(" ");
        return dataSource;
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        return new ShaPasswordEncoder();
    }
   }


