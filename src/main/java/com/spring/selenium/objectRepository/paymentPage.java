package com.spring.selenium.objectRepository;

import com.spring.selenium.base.base;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.time.Duration;

import static com.spring.selenium.util.testUtil.WAIT;

@Component
public class paymentPage extends base {

    private boolean SuccessResult;
    static double enteredAmount;
    JavascriptExecutor jse;
    WebDriverWait wait;


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
    @FindBy(id = "react-tabs-2")
    private WebElement toAccountChooserAccountTab;

    //Amount Text field
    @FindBy(id = "field-bnz-web-ui-toolkit-9")
    private WebElement transferAmountTexField;

    @FindBy(xpath = "//*[@id=\"paymentForm\"]/div[4]/div/button[1]/span/span/span")
    private WebElement TransferButton;

    @FindBy(xpath = "//span[contains(text(),'For your statement')]")
    private WebElement forYourStatementLabel;


    public void clickOnAccountChooser() {

        accountChooserButton.click();

    }

    public void clickOnToAccountChooser() {

        WebElement ele = toAccountChooserButton;
        jse.executeScript("arguments[0].click()", ele);

    }

    public void WaitUntilforYourStatementLabel() throws InterruptedException {

        SuccessResult = false;
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));

        for (int i = 1; i <= 3; i++) {
            try {

                wait.until(ExpectedConditions.visibilityOf(forYourStatementLabel));
                System.out.println("Waiting until the for your statement Label is displayed");
                SuccessResult = true;
                break;


            } catch (Exception ex) {
            }
        }

        if (!SuccessResult) {


            Assert.fail("for your statement Label is not displayed");
        }

    }


    public void clickOntoAccountChooserAccountTab() {


        WebElement ele = toAccountChooserAccountTab;
        jse.executeScript("arguments[0].click()", ele);

    }


    public void clickOnTransferAmountTexField(String amount) {

        WebElement ele = transferAmountTexField;
        jse.executeScript("arguments[0].click()", ele);
        transferAmountTexField.sendKeys(amount);
        enteredAmount = Double.parseDouble(amount);

    }


    public void clickOnTransferButton() {

        WebElement ele = TransferButton;
        jse.executeScript("arguments[0].click()", ele);

    }


    public void ClickOnbillAccount() {

        WebElement ele = billAccount;
        jse.executeScript("arguments[0].click()", ele);

    }

    public void ClickOnEveryDayAccount() {

        WebElement ele = EveryDayAccount;
        jse.executeScript("arguments[0].click()", ele);

    }

}
