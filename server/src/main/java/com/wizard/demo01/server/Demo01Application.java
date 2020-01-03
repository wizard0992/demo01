package com.wizard.demo01.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author wizard_0992
 * @date 2020/01/02 15:51
 */

@SpringBootApplication
@MapperScan(basePackages = "com.wizard.demo01.model.mapper")
public class Demo01Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(Demo01Application.class);
    }

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext run = SpringApplication.run(Demo01Application.class, args);
    }
}