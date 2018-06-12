package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static utils.TestReport.testReport;

public class PageNotifications extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(id = "formItemNotifications")
    List<WebElement> notificationItems;


    public PageNotifications(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        this.wait = new WebDriverWait(appiumDriver, 30);
        this.notificationItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("formItemNotifications")));
    }

    public PageComment selectNotifications(int index) {
        notificationItems.get(index).click();
        return new PageComment(appiumDriver);
    }

    public int verifyNotificationInfo(int notificationIndex, String whoMentionUs, String eventName) {
        String notiContent = notificationItems.get(notificationIndex).findElement(By.xpath("txtNotiContent")).getText();
        String notiName = notificationItems.get(notificationIndex).findElement(By.xpath("txtNotiName")).getText();
        String testLog = String.format("Expected: notification from: %s, event: %s. Actual: notification from: %s, event: %s.", whoMentionUs, eventName, notiName, notiContent);
        boolean result = (whoMentionUs.equals(notiName) && notiContent.contains(eventName));
        testReport(this.appiumDriver, result, testLog, testLog, true);

        if(result == true)
            return 1;
        return 0;
    }

}
