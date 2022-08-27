package com.spring.selenium.testcases;


import com.spring.selenium.base.base;
import com.spring.selenium.objectRepository.homePage;
import com.spring.selenium.objectRepository.payeePage;
import com.spring.selenium.objectRepository.paymentPage;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
public class regressiontests extends base {

    public regressiontests() {
        super();
    }

    @Autowired
    protected homePage homePage;
    @Autowired
    protected payeePage payeePage;
    @Autowired
    protected paymentPage paymentPage;


    @Test()
    public void navigateToPayeePage() throws InterruptedException {

        homePage.WaitUntilHomePageText();
        Assert.assertEquals(homePage.verifyMenuLabel(), "Menus");
        homePage.clickOnMenuButton();


    }

}
