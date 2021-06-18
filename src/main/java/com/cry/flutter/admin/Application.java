package com.cry.flutter.admin;

import com.cry.flutter.admin.common.FileProperties;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableSwagger2Doc
@SpringBootApplication
@MapperScan("com.cry.flutter.admin.mapper")
@EnableConfigurationProperties({
        FileProperties.class
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}