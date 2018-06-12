package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.TestReport.testReport;

public class PageMyProfile extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(id = "imgProfileAvatar")
    WebElement avatar;

    @FindBy(id = "btnMoreOption")
    WebElement plushBtn;

    @FindBy(id = "txtFollowerCount")
    WebElement followerCount;

    @FindBy(id = "txtFollowingCount")
    WebElement followingCount;

    @FindBy(id = "txtProfileUserName")
    WebElement profileFullName;

    @FindBy(id = "txtProfilePhoneNumber")
    WebElement phone;

    @FindBy(id = "txtProfileEmail")
    WebElement email;

    @FindBy(id = "btnAppBack")
    WebElement btnBack;



    public PageMyProfile (AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        super.closeTips(appiumDriver);
        this.wait = new WebDriverWait(appiumDriver, 25);
        this.avatar = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("imgProfileAvatar")));
    }

    public int verifyEmail(String expectedEmail){
        String actualEmail = this.email.getText();
        String testlog = String.format("Expected: %s, actual: %s", expectedEmail, actualEmail);
        boolean result = actualEmail.equals(expectedEmail);
        testReport(this.appiumDriver, result, String.format("User email is correct. %s", testlog), String.format("User's email is NOT correct. %s", testlog), true);

        if(result == true)
            return 1;
        return 0;
    }

    public PageMore goBack(){
        btnBack.click();
        return new PageMore(this.appiumDriver);
    }

    public PageMyProfile editInformation(String fullName, String phoneNumber) {
        plushBtn.click();
        WebElement editInfoBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Edit information']")));
        editInfoBtn.click();

        WebElement changeName = wait.until(ExpectedConditions.elementToBeClickable(By.id("edtChangeName")));
        changeName.clear();
        changeName.sendKeys(fullName);

        WebElement changePhone = appiumDriver.findElement(By.id("edtChangePhoneNumber"));
        changePhone.clear();
        changePhone.sendKeys(phoneNumber);

        WebElement OkBtn = appiumDriver.findElement(By.id("btnPopupDialogOk"));
        OkBtn.click();
        return this;
    }

    public int verifiedNameAndPhone(String fullName, String phoneNumber) {
        profileFullName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtProfileUserName")));

        String actualFullName = profileFullName.getText();
        String actualPhone = phone.getText();
        String testlog = String.format("Expect name: %s, phone %s.\nActual name: %s, phone %s.", fullName, phoneNumber, actualFullName, actualPhone);
        System.out.println(testlog);
        System.out.println(fullName.equals(actualFullName));
        System.out.println(phoneNumber.equals(actualPhone));

        boolean result = (fullName.equals(actualFullName) && phoneNumber.equals(actualPhone));
        testReport(appiumDriver, result, String.format("Info is updated as expected.\n%s", testlog), String.format("Info is NOT updated as expected.\n%s", testlog), true);

        if(result == true)
            return 1;
        return 0;
    }

    public String getProfileName(){
        return profileFullName.getText();
    }
}
