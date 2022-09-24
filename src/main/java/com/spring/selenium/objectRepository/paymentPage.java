package com.spring.selenium.objectRepository;

import com.spring.selenium.base.base;
import com.spring.selenium.util.webUtil;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.spring.selenium.util.webUtil.WAIT;

@Lazy
@Component
@Scope("prototype")
public class paymentPage extends base {

    @Autowired
    webUtil webUtil;

    static double enteredAmount;



    @FindBy(xpath = "/html/body/div[8]/div/div/div[2]/div/div/div/span/span[1]/input")
    private WebElement accounSearchField;

    @FindBy(xpath = "//p[contains(text(),'Everyday')]")
    private WebElement EveryDayAccount;

    @FindBy(xpath = "//*[@id=\"react-tabs-3\"]/ul/li[1]/button/div/div/div[2]/p[1]")
    private WebElement billAccount;

    //Transfer from account button
    @FindBy(xpath = "//button[@data-testid='from-account-chooser']")
    private WebElement accountChooserButton;

    //Transferr to acount button
    @FindBy(xpath = "//span[contains(text(),'Choose account, payee, or someone new')]")
    private WebElement toAccountChooserButton;

    //Account Tab Button
    @FindBy(xpath = "//li[@data-testid='to-account-accounts-tab']")
    private WebElement toAccountChooserAccountTab;

    //Amount Text field
    @FindBy(id = "field-bnz-web-ui-toolkit-9")
    private WebElement transferAmountTexField;

    @FindBy(xpath = "//*[@id=\"paymentForm\"]/div[4]/div/button[1]/span/span/span")
    private WebElement TransferButton;

    @FindBy(xpath = "//span[contains(text(),'For your statement')]")
    private WebElement forYourStatementLabel;


    public void clickOnAccountChooser() throws InterruptedException {

        webUtil.waitForElementToDisplay(accountChooserButton,"Account Chooser Button",15,2);
        webUtil.click(accountChooserButton,"Account Chooser");


    }

    public void clickOnToAccountChooser() throws InterruptedException {

        webUtil.waitForElementToDisplay(forYourStatementLabel,"For your Statement Label",15,5);
        webUtil.waitForElementToDisplay(toAccountChooserButton,"To Account Chooser Button",15,2);
        webUtil.clickJavaScript(toAccountChooserButton, "To Account Chooser");
    }


    public void clickOntoAccountChooserAccountTab() throws InterruptedException {


        webUtil.waitForElementToDisplay(toAccountChooserAccountTab,"To Account Chooser Tab",15,2);
        webUtil.clickJavaScript(toAccountChooserAccountTab,"To Account Chooser Tab");


    }


    public void clickOnTransferAmountTexField() throws InterruptedException {
        webUtil.waitForElementToDisplay(transferAmountTexField,"Transfer Amount Field",15,2);
        webUtil.clickUsingAction(transferAmountTexField,"Transfer Amount Field");
        webUtil.waitForElementToDisplay(TransferButton,"Transfer Button",15,2);
        webUtil.sendKeys(transferAmountTexField,fileProperties.prop.getProperty("amount"));

        enteredAmount = Double.parseDouble(fileProperties.prop.getProperty("amount"));

    }


    public void clickOnTransferButton() {

        webUtil.waitForElementToDisplay(TransferButton,"Transfer Button",15,2);
        webUtil.clickJavaScript(TransferButton,"Transfer Button");

    }


    public void ClickOnbillAccount() throws InterruptedException {

        webUtil.waitForElementToDisplay(billAccount,"Bill Account",15,2);
        webUtil.clickUsingAction(billAccount,"Bill Account");

    }

    public void ClickOnEveryDayAccount() throws InterruptedException {

        webUtil.waitForElementToDisplay(EveryDayAccount,"Everyday Account",15,5);
        Thread.sleep(3000);
     /*   webUtil.click(accounSearchField,"Everyday Search field");
        webUtil.sendKeys(accounSearchField,"Everyday Search field");*/
        webUtil.waitForElementToDisplay(EveryDayAccount,"Everyday Account",15,5);
        webUtil.clickJavaScript(EveryDayAccount,"Everyday Account");

    }

}
