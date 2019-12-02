package com.wizard.demo01.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web应用访问静态资源的配置
 * @author wizard_0992
 * @date 2019/12/2 15:31
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置一个映射
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
    }
}
