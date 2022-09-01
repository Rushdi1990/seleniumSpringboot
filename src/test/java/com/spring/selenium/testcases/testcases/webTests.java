package com.spring.selenium.testcases.testcases;

import com.spring.selenium.base.base;
import com.spring.selenium.objectRepository.homePage;
import com.spring.selenium.objectRepository.payeePage;
import com.spring.selenium.objectRepository.paymentPage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest
public class webTests extends base {

    @Autowired
    homePage homePage;

    @Autowired
    payeePage payeePage;

    @Autowired
    paymentPage paymentPage;

    @Test
    public void navigateToPayeePage() throws InterruptedException {
       homePage.WaitUntilHomePageText();
        Assert.assertEquals(homePage.verifyMenuLabel(), "Menu");
        homePage.clickOnMenuButton();
        Thread.sleep(5000);
       homePage.ClickMenuButtonPayee();
        Thread.sleep(5000);
       Assert.assertTrue(payeePage.verifyPayeeSearchText());

    }
    @Test
    public void addNewPayee() throws InterruptedException {

        navigateToPayeePage();
        Thread.sleep(5000);
        payeePage.clickOnAddPayeeButton();
        Thread.sleep(5000);
        payeePage.enterPayeeName();
        payeePage.clickOnPayeeTycoCylinder();
        payeePage.clickOnAddSavePayee();
        payeePage.waitUntilPayeeAddedMessage();
        Assert.assertTrue(payeePage.verifyPayeeAddedMessage());

    }
}
