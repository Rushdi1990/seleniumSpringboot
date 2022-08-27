package com.spring.selenium.base;

import com.spring.selenium.config.fileProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public abstract class base  {
    @Autowired
    public WebDriver driver;


    @Autowired
    public fileProperties fileProperties;


   @PostConstruct
    public void init(WebDriver driver) {
       //this.driver = driver;
       PageFactory.initElements(driver, this);
       System.out.println("Postconstruct lunched");
       System.out.println("Start driver");
   }


    @Before
    public void setUp() {

        fileProperties = new fileProperties();
        //WebDriverManager.chromedriver().setup();
       // driver = new ChromeDriver();
        driver.get(fileProperties.prop.getProperty("url"));

    }

    @After
    public void tearDown() {

        driver.quit();
        System.out.println("Quiting driver");
    }
}
