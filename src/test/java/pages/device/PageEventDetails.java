package pages.device;

import io.appium.java_client.AppiumDriver;
import okhttp3.internal.Internal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.TestReport.testReport;

public class PageEventDetails extends PageBase {
    private AppiumDriver appiumDriver;


    @FindBy(id = "rpBack")
    WebElement backBtn;

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

    public String getEventName(){
        return eventName.getText();
    }
    public void clickInterested() {
        interestedBtn.click();
    }

    public PageComment clickComment(){
        commentBtn.click();
        return new PageComment(appiumDriver);
    }
    public Integer getNumberOfInterestedUsers(){
        String interestNumber = interestNum.getText();
        interestNumber = interestNumber.substring(0, interestNumber.indexOf(" "));
        return Integer.parseInt(interestNumber);
    }

    public boolean isThisUserInterestedInCurrentEvent(){
        if(interestedBtn.getAttribute("selected").equals("true"))
            return true;
        return false;
    }

    public int verifyNumberOfIntersetedUsers(int expectedNumber) {
        int acutalNumber = getNumberOfInterestedUsers();
        String testLog = String.format("User interested: Expected %d. Actual: %d", expectedNumber, acutalNumber);
        boolean result = acutalNumber == expectedNumber;
        testReport(appiumDriver, result, testLog, testLog, true);

        if(result == true)
            return 1;
        return 0;
    }

    public PageHome clickBack(){
        backBtn.click();
        return new PageHome(appiumDriver);
    }
}
