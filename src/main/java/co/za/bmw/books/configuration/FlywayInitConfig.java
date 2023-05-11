package co.za.bmw.books.configuration;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class FlywayInitConfig {

    @Resource(lookup = "jdbc/derby")
    private DataSource dataSource;

    @PostConstruct
    public void startUp(){
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }
}
