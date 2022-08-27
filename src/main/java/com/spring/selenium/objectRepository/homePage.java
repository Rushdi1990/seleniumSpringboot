package com.spring.selenium.objectRepository;

import com.spring.selenium.base.base;
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


@Component
public class homePage extends base {



    private boolean SuccessResult;
    WebDriverWait wait;

    @FindBy(xpath = "//*[@id=\"left\"]/div[1]/div/button/span/span/span")
    private WebElement menuLabel;




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



    public void clickOnMenuButton() {

        menuLabel.click();

    }


}
