package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageMore extends PageBase {

    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(id = "img_avatar")
    WebElement avatar;

    @FindBy(xpath = "//*[@text='My profile']")
    WebElement myProfile;

    @FindBy(xpath = "//*[@text='Promotions']")
    WebElement promotion;

    @FindBy(xpath = "//*[@text='Invite friends']")
    WebElement inviteFriends;

    @FindBy(xpath = "//*[@text='Help & Support']")
    WebElement helpAndSupport;

    @FindBy(xpath = "//*[@text='Rate app']")
    WebElement rateApp;

    @FindBy(xpath = "//*[@text='Change language']")
    WebElement changeLanguage;

    @FindBy(xpath = "//*[@text='Change password']")
    WebElement changePassword;

    @FindBy(xpath = "//*[@text='Sign out']")
    WebElement signOut;

    public PageMore (AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        super.closeTips(appiumDriver);
        this.wait = new WebDriverWait(appiumDriver, 25);
        this.promotion = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Promotions']")));
    }

    public PageHome signOut(){
        signOut.click();
        WebElement agree = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='android:id/button1']")));
        agree.click();
        return new PageHome(appiumDriver);
    }

    public PageMyProfile tapMyProfile(){
        myProfile.click();
        closeTips(appiumDriver);
        return new PageMyProfile(appiumDriver);
    }

    public PageChangePassword tapChangePassword(){
        changePassword.click();
        return new PageChangePassword(appiumDriver);
    }
}
