package com.lzf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

/**
 * @author leizefeng
 */
@EnableScheduling
@MapperScan({"com.lzf.**.mapper"})
@SpringBootApplication
@EnableJdbcHttpSession
public class LocalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalProjectApplication.class, args);
    }

}
