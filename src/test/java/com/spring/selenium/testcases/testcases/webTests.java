package com.spring.selenium.testcases.testcases;

import com.spring.selenium.base.base;
import com.spring.selenium.objectRepository.homePage;
import com.spring.selenium.objectRepository.payeePage;
import com.spring.selenium.objectRepository.paymentPage;

import org.junit.jupiter.api.DisplayName;
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
        Assert.assertEquals(homePage.verifyMenuLabel(), "Menu");
        homePage.clickOnMenuButton();
        homePage.ClickMenuButtonPayee();
        Assert.assertTrue(payeePage.verifyPayeeSearchText());

    }
    @Test
    public void addNewPayee() throws InterruptedException {

        navigateToPayeePage();
        payeePage.clickOnAddPayeeButton();
        payeePage.enterPayeeName();
        payeePage.clickOnPayeeTycoCylinder();
        payeePage.clickOnAddSavePayee();
        Assert.assertTrue(payeePage.verifyPayeeAddedMessage());

    }

    @Test
    @DisplayName("TC3: Verify payee name is a required field")
    public void newPayeeFieldVerify() throws InterruptedException {

        navigateToPayeePage();
        payeePage.clickOnAddPayeeButton();
        payeePage.clickOnAddSavePayee();
        Assert.assertTrue(payeePage.VerifyValidationMessageWhenPayeeNameEmpty());
        payeePage.enterPayeeName();
        payeePage.clickOnPayeeTycoCylinder();
        Assert.assertTrue(payeePage.VerifyValidationMessagePayeeNameAddedVerified());

    }

    @Test
    @DisplayName("TC4: Verify that payees can be sorted by name")
    public void payeeCanbeSorted() throws InterruptedException {

        navigateToPayeePage();
        payeePage.clickOnAddPayeeButton();
        payeePage.enterPayeeName();
        payeePage.clickOnPayeeTycoCylinder();
        payeePage.clickOnAddSavePayee();
        Assert.assertTrue(payeePage.verifyPayeeAddedMessage());
        payeePage.checkListAscendingOrder();
        payeePage.clickOnPayeeListNameHeading();
        payeePage.checkListDecendingOrder();

    }

    // Navigate to the payment page and transfer $500 from everyday account to bill acount
   /* @Test
    @DisplayName("TC5: Navigate to Payments page")
    public void navigatePayementPage(String Amount) throws InterruptedException {

        Assert.assertEquals(homePage.verifyMenuLabel(), "Menu");
        homePage.clickOnMenuButton();
        homePage.ClickOnPayOrTransferMenuButton();
        paymentPage.clickOnAccountChooser();
        paymentPage.ClickOnEveryDayAccount();
        paymentPage.WaitUntilforYourStatementLabel();
        paymentPage.clickOnToAccountChooser();
        paymentPage.WaitUntilforYourStatementLabel();
        paymentPage.clickOntoAccountChooserAccountTab();
        paymentPage.ClickOnbillAccount();
        paymentPage.WaitUntilforYourStatementLabel();
        paymentPage.clickOnTransferAmountTexField(Amount);
        paymentPage.clickOnTransferButton();
        homePage.WaitUntilTransferSuccessfulMessage();
        homePage.calculateRemainingBillAccountBalance();
        homePage.calculateRemainingEveryDayAccountBalance();

    }
*/

}
