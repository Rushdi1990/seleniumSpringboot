package com.spring.selenium.driverScope;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class browserScopeConfig {
    @Bean
    public static BeanFactoryPostProcessor beanFactoryPostProcessor (){

        return new browserScopePostProcessor();
    }

}
