package com.spring.selenium.util;

import com.spring.selenium.base.base;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

@Component
public class webUtil extends base {

    private WebDriverWait wait;
    private JavascriptExecutor jse;

    public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir") + "/src/main/java/testData/testData.xlsx";

    static Workbook book;
    static Sheet sheet;


    public static Object[][] getTestData(String sheetName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(TESTDATA_SHEET_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = book.getSheet(sheetName);
        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
                data[i][k] = sheet.getRow(i + 1).getCell(k).toString();

            }
        }
        return data;
    }


    //Take Screenshot

    public void takeScreenshotAtEndOfTest() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
    }


    public static long WAIT = 20;

    public void waitForElementToDisplay(WebElement element, String elementName, int timeOut, int attempts) {
        Boolean SuccessResult = false;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        for (int i = 1; i <= attempts; i++) {
            try {
                System.out.println("Waiting for Element " + elementName + " to display for " + timeOut + " :Attempt " + i);
                wait.until(ExpectedConditions.visibilityOf(element));
                SuccessResult = true;
                break;

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (!SuccessResult) {
            Assert.fail("Couldn't wait for element: " + element);
        }
    }


    public void click(WebElement element, String text) {
        try {

            element.click();
            System.out.println("Clicked on the: " + text);

        } catch (Exception ex) {

            Assert.fail("Couldn't click on element: " + text);

        }
    }


    public void clickUsingAction(WebElement element, String text) {
        try {

            Actions action = new Actions(driver);
            action.moveToElement(element).click().perform();
            System.out.println("Clicked on the: " + text);

        } catch (Exception ex) {

            Assert.fail("Couldn't click on element: " + text);

        }
    }

    public void sendKeys(WebElement element, String text) {
        try {

            element.sendKeys(text);
            System.out.println("Send Text: " + text);

        } catch (Exception ex) {

            Assert.fail("Couldn't Send Text: " + text);

        }

    }


    public String getText(WebElement element, String text) {
        String gettext = null;
        try {

            gettext = element.getText();
            System.out.println("get Text of: " + text);

        } catch (Exception ex) {

            Assert.fail("Couldn't get Text of: " + text);

        }

        return gettext;
    }

    public void clickJavaScript(WebElement element, String text) {
        try {

            jse.executeScript("arguments[0].click()", element);
            System.out.println("Clicked on the: " + text + " using java script");

        } catch (Exception ex) {

            Assert.fail("Couldn't Click on the: " + text + " using java script");

        }

    }


    public boolean elementIsDisplayed(WebElement element, String text) {


        if (element.isDisplayed()) {
            System.out.println("Element is displayed: " + text);
            return true;
        } else {
            System.out.println(text +" is not displayed: ");
            return false;
        }


    }

}
