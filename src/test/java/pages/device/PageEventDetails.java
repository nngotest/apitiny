package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageEventDetails extends PageBase {
    private AppiumDriver appiumDriver;

    @FindBy(id = "rpByTicket")
    WebElement buyTickectBtn;

    @FindBy(id = "tvTitle")
    WebElement eventName;

    @FindBy(id = "txtEventTime")
    WebElement eventTime;

    @FindBy(id = "txtEventAddress")
    WebElement location;

    @FindBy(id = "tvNumberInterested")
    WebElement interestNum;

    @FindBy(id = "InAddCalendar")
    WebElement addCalendarBtn;

    @FindBy(id = "InShare")
    WebElement shareBtn;

    @FindBy(id = "rpInterested")
    WebElement interestedBtn;

    @FindBy(id = "rpComment")
    WebElement commentBtn;

    @FindBy(id = "text")
    WebElement tabs;  //ticket info / introduction / organizer

    public PageEventDetails(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 5);
        //super.waitForVisibilityOfElement(appiumDriver, this.SignInButon);
        eventName = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tvTitle")));
    }

    public PageChooseTicket clickBuyTicket() {
        //System.out.println("clickBuyTicket");
        buyTickectBtn.click();
        return new PageChooseTicket(appiumDriver);
    }

}
