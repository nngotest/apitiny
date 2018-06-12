package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static utils.TestReport.testReport;

public class PageFollowingList {

    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(id = "recycler_view")
    WebElement userList;

    public PageFollowingList(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("recycler_view")));
    }

    public int getNumberOfInterestedUsers() {
        return userList.findElements(By.xpath("//*[@class='android.widget.LinearLayout']")).size();
    }

    public void verifyThatUserIsInTheList(String username) {
        List<WebElement> users = userList.findElements(By.xpath("//*[@class='android.widget.LinearLayout']"));
        List<String> currentUsersName = new ArrayList<String>();
        for (WebElement user:users) {
            currentUsersName.add(user.findElement(By.id("txtUserName")).getText());
        }

        testReport(appiumDriver, currentUsersName.contains(username), String.format("User %s is in the list.", username), String.format("User %s is NOT in the list.", username), true);
    }
}
