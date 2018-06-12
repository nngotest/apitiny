package pages.device;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class PageLogin extends PageBase {

    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(id = "txtEmail")
    WebElement user;

    @FindBy(id = "txtPassword")
    WebElement password;

    @FindBy(xpath = "//*[@text='SIGN IN']")
    WebElement signIn;

    @FindBy(id = "btnSignInWithFacebook")
    WebElement btnSignInWithFacebook;

    @FindBy(id = "btnSignInWithGoogle")
    WebElement btnSignInWithGoogle;

    @FindBy(id = "rpSignUp")
    WebElement btnSignUp;

    public PageLogin(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        this.wait = new WebDriverWait(appiumDriver, 25);
        this.user = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtEmail")));
    }

    public PageHome loginWithEmail(String userName, String password) {
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

    public PageHome loginWithGoogle(String gmailUsername, String gmailPassword) {
        System.out.println(String.format("Login with Google: %s, %s", gmailUsername, gmailPassword));
        btnSignInWithGoogle = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignInWithGoogle")));
        btnSignInWithGoogle.click();

        waitForLoading(5);

        // if the email account is already added to device - select the account
        List<WebElement> el  = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='android.widget.TextView']")));
        String addedAccount = "";
        for (int i = 0; i < el.size(); i++) {
            addedAccount = el.get(i).getText().toLowerCase();
            if(gmailUsername.toLowerCase().equals(addedAccount)){
                el.get(i).click();
                return new PageHome(appiumDriver);
            }
        }

        // add new account
        WebElement addAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Add account']")));
        addAccount.click();

        WebElement checkingInfo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Checking info…']")));
        wait.until(ExpectedConditions.invisibilityOf(checkingInfo));

        WebElement emailOrPhone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Email or phone']")));
        emailOrPhone.sendKeys(gmailUsername);

        WebElement btnNext = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='NEXT']")));
        btnNext.click();

        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Password']")));
        password.sendKeys(gmailPassword);

        btnNext = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='NEXT']")));
        btnNext.click();

        WebElement btnAccept = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='ACCEPT']")));
        btnAccept.click();

        try {
            WebElement btnAllow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='ALLOW']")));
            btnAllow.click();
        } catch (Exception e){}

        btnSignInWithGoogle = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignInWithGoogle")));
        btnSignInWithGoogle.click();

        return new PageHome(appiumDriver);
    }

    public PageHome loginWithFacebook(String facebookEmail, String facebookPassword) {
        System.out.println(String.format("Login with Facebook: %s, %s", facebookEmail, facebookPassword));
        btnSignInWithFacebook = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSignInWithFacebook")));
        btnSignInWithFacebook.click();
        try {
            WebElement addAccount = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Log into another account']")));
            addAccount.click();

            WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Phone or Email']")));
            WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Password']")));
            email.sendKeys(facebookEmail);
            password.sendKeys(facebookPassword);

            WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='LOG IN']")));
            login.click();

            WebElement btnContinue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='Continue']")));
            btnContinue.click();
        } catch (Exception ex) {
            System.out.println((ex.getMessage()));
        }
        return new PageHome(appiumDriver);
    }

    public PageSignUp clickSignUp(){
        this.btnSignUp.click();
        return new PageSignUp(this.appiumDriver);
    }
}
