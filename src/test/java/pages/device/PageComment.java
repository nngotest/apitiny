package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static utils.TestReport.testReport;

public class PageComment extends PageBase{
    private AppiumDriver appiumDriver;

    @FindBy(id = "edtComment")
    WebElement commentTextBox;

    @FindBy(id = "btnSend")
    WebElement sendCommentBtn;

    @FindBy(id = "rpBack")
    WebElement backBtn;

    public PageComment (AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 5);
        sendCommentBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnSend")));
    }

    public void sendMessageAndTagAUser(String userName, String message){
        commentTextBox.click();
        commentTextBox.sendKeys("@");

        try {
            Thread.sleep(5000);
        } catch (Exception ex){}

        WebElement userListWrapper = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='android.widget.ListView']")));
        List<WebElement> names = userListWrapper.findElements(By.id("name"));
        for (WebElement name:names) {
            if(name.getText().equals(userName)){
                name.click();
                break;
            }
        }
        commentTextBox.sendKeys(message);
        sendCommentBtn.click();
    }

    public int verifyCommentAddedAndUserIsTagged(String userName, String message) {
        List<WebElement> comments = appiumDriver.findElements(By.id("bgCommentItem"));
        String textComment = comments.get(comments.size()).findElement(By.id("txtComment")).getText();
        String testLog = String.format("Expected %s %s. Actual: %s.",userName, message, textComment);
        boolean result = textComment.equals(userName + " " + message);
        testReport(appiumDriver, result, testLog, testLog, true);

        if(result == true)
            return 1;
        return 0;
    }

    public PageEventDetails clickBack(){
        backBtn.click();
        return new PageEventDetails(appiumDriver);
    }
}
