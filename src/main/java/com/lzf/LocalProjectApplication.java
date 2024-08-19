package com.lzf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.lzf.*.mapper"})
public class LocalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocalProjectApplication.class, args);
    }

}
