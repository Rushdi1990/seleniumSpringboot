package com.spring.selenium.objectRepository;

import com.spring.selenium.base.base;
import com.spring.selenium.util.testUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.spring.selenium.util.testUtil.WAIT;

@Component
public class payeePage extends base {

    @Autowired
    testUtil testUtil;

    private boolean SuccessResult;
    WebDriverWait wait;


    @FindBy(xpath = "//input[@placeholder='Search payees']")
    private WebElement payeeSearchText;

    @FindBy(xpath = "//header/div[1]/div[3]/button[1]")
    private WebElement addPayeeButton;

    @FindBy(id = "ComboboxInput-apm-name")
    private WebElement payeeNameTextBox;

    @FindBy(xpath = "//span[contains(text(),'APN NEWS & MEDIA')]")
    private WebElement ApnNewsPayee;

    @FindBy(xpath = "//span[contains(text(),'TYCO CYLINDER TESTING')]")
    private WebElement tycoCylinderPayee;

    @FindBy(xpath = "//span[contains(text(),'Payee added')]")
    private WebElement payeeAddedMessage;

    @FindBy(xpath = "//p[contains(text(),'Payee Name is a required field. Please complete to')]")
    private WebElement validationMessageWhenPayeeNameEmpty;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[1]/div/div/div[4]/div/form/div[1]/div[1]/div[2]/div/div[1]/span[2]")
    private WebElement validationMessagePayeeNameAddedVerified;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/form[1]/div[2]/button[3]")
    private WebElement savePayeeButton;

    @FindBy(xpath = "//span[contains(text(),'Name')]")
    private WebElement payeeListNameHeading;


    public boolean verifyPayeeSearchText() {

        return payeeSearchText.isDisplayed();

    }

    public void enterPayeeName() {
        testUtil.click(payeeNameTextBox, "Payee Name Textbox");
        testUtil.sendKeys(payeeNameTextBox,fileProperties.prop.getProperty("PayeeName"));

    }


    public void clickOnAddPayeeButton() {

        testUtil.click(addPayeeButton, "Add Payee Button");


    }

    public void clickOnPayeeTycoCylinder() {

        testUtil.click(tycoCylinderPayee, "Tyco Cylinder Payee");


    }

    public boolean verifyPayeeAddedMessage() {

        return payeeAddedMessage.isDisplayed();

    }

    public void waitUntilPayeeAddedMessage() {

        SuccessResult = false;
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));

        for (int i = 1; i <= 4; i++) {
            try {

                wait.until(ExpectedConditions.visibilityOf(payeeAddedMessage));
                System.out.println("Waiting until the Payee added message is displayed");
                SuccessResult = true;
                break;

            } catch (Exception ex) {
            }
        }

        if (!SuccessResult) {

            Assert.fail("Payee added message is not displayed");
        }

    }


    public void WaitUntilPayeeApnNews() {

        SuccessResult = false;
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT));

        for (int i = 1; i <= 3; i++) {
            try {

                wait.until(ExpectedConditions.visibilityOf(ApnNewsPayee));
                System.out.println("Waiting until the Payee is displayed");
                SuccessResult = true;
                break;

            } catch (Exception ex) {
            }
        }

        if (!SuccessResult) {

            Assert.fail("Payee is not displayed");
        }

    }

    public void clickOnPayeeApnNews() {

        ApnNewsPayee.click();

    }

    public void clickOnAddSavePayee() {

        savePayeeButton.click();

    }

    public boolean VerifyValidationMessageWhenPayeeNameEmpty() {

        return validationMessageWhenPayeeNameEmpty.isDisplayed();

    }

    public boolean VerifyValidationMessagePayeeNameAddedVerified() {

        String checkNoValidationMessage = validationMessageWhenPayeeNameEmpty.getText();

        return checkNoValidationMessage.isEmpty();

    }


    public void clickOnPayeeListNameHeading() {

        payeeListNameHeading.click();
    }

    //Calculate the number of payees in the payee list

    public int calculateNumberofRows() {

        List<WebElement> rows = driver.findElements(By.xpath("/html/body/div[2]/div/div/div[3]/section/section/div/ul/li"));
        int rowcount = rows.size();
        return rowcount + 1;
    }

    //Get all the Payees' from the Payee list

    List<String> ActualPayeeList;

    public void getPayeeNamesFromList() {

        ActualPayeeList = new ArrayList<String>();

        int totalRows = calculateNumberofRows();
        String beforeXpath = "/html/body/div[2]/div/div/div[3]/section/section/div/ul/li[";
        String afterXpath = "]/div/div/div[1]/div/p[1]/span[1]";

        for (int i = 1; i < totalRows; i++) {
            String actualXpath = beforeXpath + (i) + afterXpath;
            String element = driver.findElement(By.xpath(actualXpath)).getText();
            ActualPayeeList.add(element);

        }

    }

    //Check whether the list is displaying in Ascending order

    public void checkListAscendingOrder() {
        getPayeeNamesFromList();
        List AscempList = new ArrayList();
        AscempList.addAll(ActualPayeeList);
        Collections.sort(AscempList);
        Assert.assertTrue(ActualPayeeList.equals(AscempList), "Payee list is not in the Ascending Order");

    }

    //Check whether the list is displaying in Descending order

    public void checkListDecendingOrder() {
        getPayeeNamesFromList();
        List DesctempList = new ArrayList();
        DesctempList.addAll(ActualPayeeList);
        Collections.sort(DesctempList, Collections.reverseOrder());
        Assert.assertTrue(ActualPayeeList.equals(DesctempList), "Payee list is not in the Descending Order");

    }

}
