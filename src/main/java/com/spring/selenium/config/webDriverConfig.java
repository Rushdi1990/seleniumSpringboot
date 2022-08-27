package com.spring.selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

@Configuration
@Profile("!remote")
public class webDriverConfig {

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    public WebDriver edgeDriver() {

        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    @Bean
    // @Primary // this will be the default browser
    @ConditionalOnMissingBean // to catch invalid browser values
    @Scope("browserscope") // use custom scope
    public WebDriver chromeDriver() {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
    }





}
