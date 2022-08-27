package com.spring.selenium.testcases.testcases;

import com.spring.selenium.base.base;
import com.spring.selenium.config.fileProperties;
import com.spring.selenium.config.webDriverConfig;
import com.spring.selenium.objectRepository.homePage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;


@SpringBootTest()
public class regressiontests extends base {
    @Autowired
    public homePage homePage ;


    @Test
    public void navigateToPayeePage() throws InterruptedException {

        homePage.WaitUntilHomePageText();
        homePage.clickOnMenuButton();

    }
}
