package com.spring.selenium.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.*;

import java.util.Collections;

@Configuration
public class webDriverConfig {


    @Bean
    @Scope("browserscope")
    @ConditionalOnProperty (name = "browser", havingValue = "chrome")
    public WebDriver chromeDriver() {

            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(false);
            options.addArguments("disable-infobars");
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("force-device-scale-factor=1");
            return new ChromeDriver(options);

    }

    @Bean
    @Scope("browserscope")
    @ConditionalOnProperty (name = "browser", havingValue = "firefox")
    @Primary
    public WebDriver firefoxDriver(){

            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("allow-insecure-localhost");
            options.addArguments("--window-size=1920,1080");
            options.addPreference("xpinstall.signatures.required", false);
            options.setHeadless(true);
            return new FirefoxDriver(options);

    }

    @Bean
    @Scope("browserscope")
    @ConditionalOnProperty (name = "browser", havingValue = "edge")
    public WebDriver edgeDriver(){

        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("allow-insecure-localhost");
        options.setHeadless(false);
        return new EdgeDriver(options);

    }




}
