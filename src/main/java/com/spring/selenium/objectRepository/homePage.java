package com.spring.selenium.objectRepository;

import com.spring.selenium.base.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.time.Duration;

import static com.spring.selenium.objectRepository.paymentPage.enteredAmount;


public class homePage extends base {


    private boolean SuccessResult;
    double billAccountblc;
    double everydayAccountblc;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"left\"]/div[1]/div/button/span/span/span")
    private WebElement menuLabel;

    @FindBy(xpath = "//span[contains(text(),'Transfer successful')]")
    private WebElement transferSuccessfullMessage;

    @FindBy(xpath = "//span[contains(text(),'Payees')]")
    private WebElement payeeMenuButton;

    @FindBy(xpath = "//span[contains(text(),'Pay or transfer')]")
    private WebElement payOrTransferMenuButton;

    @FindBy(xpath = "//*[@id=\"account-ACC-5\"]/div[2]/span[3]")
    private WebElement getBillAccountBalance;

    @FindBy(xpath = "//*[@id=\"account-ACC-1\"]/div[2]/span[3]")
    private WebElement getEveryDayAccountBalance;

    //Get the everyday account balance before transfer

    public double getBeforeEverydayAccountAmmount() {

        String everyDayAccBeforeBalance = getEveryDayAccountBalance.getText();
        everyDayAccBeforeBalance = everyDayAccBeforeBalance.replaceAll("[^\\d.]", "");
        double everyDayAccBalanceNumBefore = Double.parseDouble(everyDayAccBeforeBalance);
        return everyDayAccBalanceNumBefore;
    }

    //Get the bill account balance before transfer

    public double getBeforeBillAccountAmmount() {

        String billAccBeforeBalance = getBillAccountBalance.getText();
        billAccBeforeBalance = billAccBeforeBalance.replaceAll("[^\\d.]", "");
        double billAccBalanceNumBefore = Double.parseDouble(billAccBeforeBalance);
        return billAccBalanceNumBefore;
    }

    //Get the account balance after transferr

    public double getTheBillAccountBalanceAfterTransfer() {

        String billAccBalance = getBillAccountBalance.getText();
        billAccBalance = billAccBalance.replaceAll("[^\\d.]", "");
        double billAccBalanceNumAfter = Double.parseDouble(billAccBalance);

        return billAccBalanceNumAfter;

    }
    //Get the account balance after transferr

    public double getTheEveryDayAccountBalanceAfterTransfer() {

        String everyDayAccBalance = getEveryDayAccountBalance.getText();
        everyDayAccBalance = everyDayAccBalance.replaceAll("[^\\d.]", "");
        double everyDayAccBalanceNumAfter = Double.parseDouble(everyDayAccBalance);

        return everyDayAccBalanceNumAfter;

    }

    //Calculate the bill account balance
    public void calculateRemainingBillAccountBalance() {

        double totalbillaccoutnblc = billAccountblc + enteredAmount;
        if (totalbillaccoutnblc <= 0) {
            totalbillaccoutnblc = 0;

        }

        Assert.assertEquals(totalbillaccoutnblc, getTheBillAccountBalanceAfterTransfer(), "Bill account balance is incorrect");
    }

    //Calculate the everyday account balance
    public void calculateRemainingEveryDayAccountBalance() {

        double totaleverydayaccoutnblc = everydayAccountblc - enteredAmount;

        if (totaleverydayaccoutnblc <= 0) {
            totaleverydayaccoutnblc = 0;

        }
        Assert.assertEquals(totaleverydayaccoutnblc, getTheEveryDayAccountBalanceAfterTransfer(), "Everyday account balance is incorrect");

    }


    public String verifyMenuLabel() {


        String menulabel = menuLabel.getText();
        billAccountblc = getBeforeBillAccountAmmount();
        everydayAccountblc = getBeforeEverydayAccountAmmount();

        return menulabel;

    }

    public void WaitUntilHomePageText() {

        SuccessResult = false;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));


        for (int i = 1; i <= 3; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(menuLabel));
                System.out.println("Waiting until the Home page is displayed");
                SuccessResult = true;
                break;


            } catch (Exception ex) {
            }
        }

        if (!SuccessResult) {


            Assert.fail("Home page is not displayed");
        }

    }

    public void WaitUntilTransferSuccessfulMessage() {

        SuccessResult = false;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));


        for (int i = 1; i <= 3; i++) {
            try {
                wait.until(ExpectedConditions.visibilityOf(transferSuccessfullMessage));
                System.out.println("Waiting until the Transfer Successful message");
                SuccessResult = true;
                break;


            } catch (Exception ex) {
            }
        }

        if (!SuccessResult) {


            Assert.fail("Transfer Successful message is not displayed");
        }

    }

    public void clickOnMenuButton() {

        menuLabel.click();

    }


    public void ClickMenuButtonPayee() {

        payeeMenuButton.click();


    }

    public void ClickOnPayOrTransferMenuButton() {

        payOrTransferMenuButton.click();


    }


}
