package com.spring.selenium.testcases.testcases;

import com.spring.selenium.base.base;
import com.spring.selenium.objectRepository.homePage;
import com.spring.selenium.objectRepository.payeePage;
import com.spring.selenium.objectRepository.paymentPage;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class webTests extends base {

    @Autowired
    homePage homePage;

    @Autowired
    payeePage payeePage;

    @Autowired
    paymentPage paymentPage;

    @Test
    @DisplayName("TC1: Navigate to Payee page")
    public void navigateToPayeePage() throws InterruptedException {
        Assertions.assertEquals(homePage.verifyMenuLabel(), "Menu");
        homePage.clickOnMenuButton();
        homePage.ClickMenuButtonPayee();
        Assertions.assertTrue(payeePage.verifyPayeeSearchText());

    }
    @Test
    @DisplayName("TC2: Add new Payee")
    public void addNewPayee() throws InterruptedException {

        navigateToPayeePage();
        payeePage.clickOnAddPayeeButton();
        payeePage.enterPayeeName();
        payeePage.clickOnPayeeTycoCylinder();
        payeePage.clickOnAddSavePayee();
        Assertions.assertTrue(payeePage.verifyPayeeAddedMessage());

    }

    @Test
    @DisplayName("TC3: Verify payee name is a required field")
    public void newPayeeFieldVerify() throws InterruptedException {

        navigateToPayeePage();
        payeePage.clickOnAddPayeeButton();
        payeePage.clickOnAddSavePayee();
        Assertions.assertTrue(payeePage.VerifyValidationMessageWhenPayeeNameEmpty());
        payeePage.enterPayeeName();
        payeePage.clickOnPayeeTycoCylinder();
        Assertions.assertTrue(payeePage.VerifyValidationMessagePayeeNameAddedVerified());

    }

    @Test
    @DisplayName("TC4: Verify that payees can be sorted by name")
    public void payeeCanbeSorted() throws InterruptedException {

        navigateToPayeePage();
        payeePage.clickOnAddPayeeButton();
        payeePage.enterPayeeName();
        payeePage.clickOnPayeeTycoCylinder();
        payeePage.clickOnAddSavePayee();
        Assertions.assertTrue(payeePage.verifyPayeeAddedMessage());
        payeePage.checkListAscendingOrder();
        payeePage.clickOnPayeeListNameHeading();
        payeePage.checkListDecendingOrder();

    }

    // Navigate to the payment page and transfer $500 from everyday account to bill acount
    @Test
    @DisplayName("TC5: Navigate to Payments page")
    public void navigatePayementPage() throws InterruptedException {

        Assertions.assertEquals(homePage.verifyMenuLabel(), "Menu");
        homePage.clickOnMenuButton();
        homePage.ClickOnPayOrTransferMenuButton();
        paymentPage.clickOnAccountChooser();
        paymentPage.ClickOnEveryDayAccount();
        paymentPage.clickOnToAccountChooser();
        paymentPage.clickOntoAccountChooserAccountTab();
        paymentPage.ClickOnbillAccount();
        paymentPage.clickOnTransferAmountTexField();
        paymentPage.clickOnTransferButton();
        homePage.WaitUntilTransferSuccessfulMessage();
        homePage.calculateRemainingBillAccountBalance();
        homePage.calculateRemainingEveryDayAccountBalance();

    }

}
