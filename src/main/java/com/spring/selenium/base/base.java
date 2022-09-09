package com.spring.selenium.base;

import com.spring.selenium.config.fileProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;



@Lazy
@Component
@Scope("prototype")
public abstract class base  {
    @Autowired
    public WebDriver driver;

    @Autowired
    public fileProperties fileProperties;

    public base() {
        fileProperties = new fileProperties();
    }

    @PostConstruct
    public void init() {
       PageFactory.initElements(driver, this);
   }

    @BeforeEach
    public void setUp() {
        driver.get(fileProperties.prop.getProperty("url"));

    }

    @AfterEach
    public void close() {

        driver.close();

    }
    @AfterClass
    public void tearDown() {

        driver.quit();

    }




}
