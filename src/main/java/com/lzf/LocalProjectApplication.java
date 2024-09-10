package com.lzf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author leizefeng
 */
@EnableScheduling
@MapperScan({"com.lzf.*.mapper"})
@SpringBootApplication
public class LocalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalProjectApplication.class, args);
    }

}
