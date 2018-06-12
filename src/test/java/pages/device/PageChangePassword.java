package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageChangePassword extends PageBase{
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(id = "txtCurrent")
    WebElement currentPassword;

    @FindBy(id = "txtNewPassword")
    WebElement newPassword;

    @FindBy(id = "txtNewPasswordConfirm")
    WebElement newPasswordConfirm;

    @FindBy(id = "btnChangePassword")
    WebElement changePasswordBtn;

    public PageChangePassword(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        super.closeTips(appiumDriver);
        this.wait = new WebDriverWait(appiumDriver, 25);
        this.currentPassword = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtCurrent")));
    }

    public PageMore changePassword(String oldPwd, String newPwd){
        this.currentPassword.sendKeys(oldPwd);
        this.newPassword.sendKeys(newPwd);
        this.newPasswordConfirm.sendKeys(newPwd);
        this.changePasswordBtn.click();
        return new PageMore(appiumDriver);
    }
}
