package com.kevin.secret;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(scanBasePackages = "com.kevin.secret")
@ServletComponentScan
@MapperScan("com.kevin.secret.mapper")
public class SecretApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecretApplication.class, args);
    }

}
