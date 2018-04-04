package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageLogin extends PageBase {

    private AppiumDriver appiumDriver;

    @FindBy(id = "txtEmail")
    WebElement user;

    @FindBy(id = "txtPassword")
    WebElement password;

    @FindBy(xpath = "//*[@text='SIGN IN']")
    WebElement signIn;

    public PageLogin(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 5);
        this.user = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtEmail")));
    }

    public PageHome login(String userName, String password) {
        WebElement user = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtEmail")));
        user.sendKeys(userName);

        WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtPassword")));
        pwd.sendKeys(password);

        try {
            appiumDriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Virtual keyboard is not shown.");
        }
        signIn.click();
        return new PageHome(appiumDriver);
    }
}
