package com.spring.selenium.objectRepository;

import com.spring.selenium.base.base;
import com.spring.selenium.util.webUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.time.Duration;

import static com.spring.selenium.objectRepository.paymentPage.enteredAmount;


@Component
public class homePage extends base {

@Autowired
webUtil webUtil;

    private boolean SuccessResult;
    double billAccountblc;
    double everydayAccountblc;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"account-ACC-6\"]/div[2]/span[1]/h3")
    public WebElement homePageText;

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

        String everyDayAccBeforeBalance = webUtil.getText(getEveryDayAccountBalance,"Everyday Account Balance");
        everyDayAccBeforeBalance = everyDayAccBeforeBalance.replaceAll("[^\\d.]", "");
        double everyDayAccBalanceNumBefore = Double.parseDouble(everyDayAccBeforeBalance);
        return everyDayAccBalanceNumBefore;
    }

    //Get the bill account balance before transfer

    public double getBeforeBillAccountAmmount() {

        String billAccBeforeBalance = webUtil.getText(getBillAccountBalance,"Bill Account Balance");
        billAccBeforeBalance = billAccBeforeBalance.replaceAll("[^\\d.]", "");
        double billAccBalanceNumBefore = Double.parseDouble(billAccBeforeBalance);
        return billAccBalanceNumBefore;
    }

    //Get the account balance after transferr

    public double getTheBillAccountBalanceAfterTransfer() {

        String billAccBalance = webUtil.getText(getBillAccountBalance,"Bill Account Balance");
        billAccBalance = billAccBalance.replaceAll("[^\\d.]", "");
        double billAccBalanceNumAfter = Double.parseDouble(billAccBalance);

        return billAccBalanceNumAfter;

    }
    //Get the account balance after transferr

    public double getTheEveryDayAccountBalanceAfterTransfer() {

        String everyDayAccBalance = webUtil.getText(getEveryDayAccountBalance,"Everyday Account Balance");
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

        webUtil.waitForElementToDisplay(menuLabel, "Menu Label", 15,8);
        String menulabel = webUtil.getText(menuLabel,"Menu Label");
        billAccountblc = getBeforeBillAccountAmmount();
        everydayAccountblc = getBeforeEverydayAccountAmmount();
        return menulabel;

    }

    public void WaitUntilTransferSuccessfulMessage() {

        webUtil.waitForElementToDisplay(transferSuccessfullMessage, "transferSuccessfullMessage", 15,2);

    }


    public void clickOnMenuButton() {
        webUtil.waitForElementToDisplay(menuLabel, "Menu Label", 15,8);
        webUtil.click(menuLabel,"Menu button");

    }


    public payeePage ClickMenuButtonPayee() {
        webUtil.waitForElementToDisplay(payeeMenuButton, "Payee Menu button", 15,2);
        webUtil.click(payeeMenuButton,"Payee Menu button");
        return new payeePage();

    }

    public paymentPage ClickOnPayOrTransferMenuButton() {
        webUtil.waitForElementToDisplay(payOrTransferMenuButton, "Pay or transfer menu button", 15,2);
        webUtil.click(payOrTransferMenuButton, "Pay or transfer menu button");
        return new paymentPage();

    }

}
