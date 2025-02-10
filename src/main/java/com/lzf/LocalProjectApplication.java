package com.lzf;

import java.io.PrintStream;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author leizefeng
 */
@EnableScheduling
@MapperScan({"com.lzf.**.mapper"})
@SpringBootApplication
public class LocalProjectApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LocalProjectApplication.class);
        // 关闭banner
//        application.setBannerMode(Mode.OFF);
        application.run(args);
    }

}
