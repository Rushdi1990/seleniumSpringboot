package com.spring.selenium.objectRepository;

import com.spring.selenium.base.base;
import com.spring.selenium.util.webUtil;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.spring.selenium.util.webUtil.WAIT;

@Lazy
@Component
@Scope("prototype")
public class payeePage extends base {

    @Autowired
    webUtil webUtil;


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
        webUtil.waitForElementToDisplay(payeeSearchText, "Payee Search Text", 15,2);
        return webUtil.elementIsDisplayed(payeeSearchText,"Payee Search Text");

    }

    public void enterPayeeName() {
        webUtil.waitForElementToDisplay(payeeNameTextBox, "Payee Name Textbox field", 15,2);
        webUtil.click(payeeNameTextBox, "Payee Name Textbox");
        webUtil.sendKeys(payeeNameTextBox,fileProperties.prop.getProperty("PayeeName"));

    }


    public void clickOnAddPayeeButton() {
        webUtil.waitForElementToDisplay(addPayeeButton, "Add Payee Button", 15,2);
        webUtil.click(addPayeeButton, "Add Payee Button");


    }

    public void clickOnPayeeTycoCylinder() {
        webUtil.waitForElementToDisplay(tycoCylinderPayee, "Tyco Cylinder Payee Record", 15,2);
        webUtil.click(tycoCylinderPayee, "Tyco Cylinder Payee");


    }

    public boolean verifyPayeeAddedMessage() {
        webUtil.waitForElementToDisplay(payeeAddedMessage, "Added Payee message", 15,2);
        return webUtil.elementIsDisplayed(payeeAddedMessage,"Payee Added message Success");

    }


    public void clickOnPayeeApnNews() {
        webUtil.waitForElementToDisplay(savePayeeButton, "APN News Payee record", 15,2);
        webUtil.click(ApnNewsPayee,"APN News Payee record");

    }

    public void clickOnAddSavePayee() {
        webUtil.waitForElementToDisplay(savePayeeButton, "Save payee button", 15,2);
        webUtil.click(savePayeeButton,"Save Payee button");

    }

    public boolean VerifyValidationMessageWhenPayeeNameEmpty() {

        return webUtil.elementIsDisplayed(validationMessageWhenPayeeNameEmpty,"Validation Message when Payee Name is empty");

    }

    public boolean VerifyValidationMessagePayeeNameAddedVerified() {

        String checkNoValidationMessage = webUtil.getText(validationMessageWhenPayeeNameEmpty,"Validation Message when Payee text field is empty");

        return checkNoValidationMessage.isEmpty();

    }


    public void clickOnPayeeListNameHeading() {
        webUtil.waitForElementToDisplay(payeeListNameHeading, "Payee list name heading", 15,2);
        webUtil.click(payeeListNameHeading, "Payee List Name Heading");

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
        Assertions.assertTrue(ActualPayeeList.equals(AscempList), "Payee list is not in the Ascending Order");

    }

    //Check whether the list is displaying in Descending order

    public void checkListDecendingOrder() {
        getPayeeNamesFromList();
        List DesctempList = new ArrayList();
        DesctempList.addAll(ActualPayeeList);
        Collections.sort(DesctempList, Collections.reverseOrder());
        Assertions.assertTrue(ActualPayeeList.equals(DesctempList), "Payee list is not in the Descending Order");

    }


/*    public void navigateToPayeePage() {
        Assertions.assertEquals(homePage.verifyMenuLabel(), "Menu");
        homePage.clickOnMenuButton();
        homePage.ClickMenuButtonPayee();
        Assertions.assertTrue(payeePage.verifyPayeeSearchText());

    }*/

}
