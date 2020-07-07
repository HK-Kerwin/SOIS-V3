package com.tedu.sois;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.MultipartConfigElement;

@EnableAsync
@EnableCaching
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan({"com.tedu.sois.*.dao"})
public class SoisApplication {

    @Value("${sois.profile}")
    private String uploadFolder;


    public static void main(String[] args) {
        SpringApplication.run(SoisApplication.class, args);
        System.out.println("学员管理系统v3版本启动成功");
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        return factory.createMultipartConfig();
    }
}
