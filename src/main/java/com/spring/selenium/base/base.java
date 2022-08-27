package com.spring.selenium.base;

import com.spring.selenium.config.properties;
import com.spring.selenium.config.webDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;


import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public abstract class base  {

    @Autowired
    protected WebDriver driver;
    protected Properties prop;
    public base() {


        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            prop.load(ip);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

    }

    @PostConstruct
    private void init(){
        PageFactory.initElements(this.driver, driver);
        System.out.println("Postconstruct lunched");
    }

    @Before
    public void setUp() {

        driver.get(prop.getProperty("url"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
