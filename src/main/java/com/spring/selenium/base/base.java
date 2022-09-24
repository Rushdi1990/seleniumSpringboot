package com.spring.selenium.base;

import com.spring.selenium.config.fileProperties;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
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

    protected fileProperties fileProperties;

    public base() {
        fileProperties = new fileProperties();
    }

    @PostConstruct
    public void init() {
       PageFactory.initElements(driver, this);
   }

    @BeforeEach
    public void setUp() {
        driver.get(fileProperties.prop.getProperty("URL"));
        System.out.println("Go to: "+fileProperties.prop.getProperty("URL"));
    }

    @AfterEach
    public void close() {

        if (driver != null) {
            driver.quit();
        }

    }


}
