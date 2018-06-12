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

public class PageConversations extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(id = "form_contain")
    List<WebElement> conversations;

    @FindBy(id = "imgSearchAndRefresh")
    WebElement searchBtn;

    @FindBy(id = "edtSearchUser")
    WebElement searchTxtBox;

    @FindBy(id = "edtChatMesage")
    WebElement chatTxtBox;

    @FindBy(id = "formChatSendImg")
    WebElement sendChatBtn;

    @FindBy(id = "txtChatContent")
    List<WebElement> chatTxtContents;

    @FindBy(id = "btnChooseImg")
    WebElement chooseImageBtn;

    public PageConversations(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        this.wait = new WebDriverWait(appiumDriver, 30);
        this.searchBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("imgSearchAndRefresh")));
    }

    public List<WebElement> searchConversations(String keyword) {
        searchBtn.click();
        searchTxtBox.sendKeys(keyword);
        try {
            appiumDriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Virtual keyboard is not shown.");
        }
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("form_contain")));
    }

    public void selectConverstation(String friendName) {
        conversations = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.id("form_contain"))));
        for (WebElement con : conversations) {
            if (con.findElement(By.id("txtReceiverName")).getText().toLowerCase().equals(friendName.toLowerCase())) {
                con.click();
                break;
            }
        }
    }

    public void sendImage() {
        this.chooseImageBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnChooseImg")));
        this.chooseImageBtn.click();
        //WebElement img = appiumDriver.findElement(By.id("imagePreview"));
        WebElement img =  wait.until(ExpectedConditions.elementToBeClickable(By.id("imagePreview")));
        img.click();
        WebElement sentPhotoBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSendPhoto")));
        sentPhotoBtn.click();
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }
    }

    public int verifyConversationsInfo() {
        boolean result = true;
        String testlog = null;
        String name = "";
        String lastMsg = "";
        String lastTime = "";
        conversations = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.id("form_contain"))));
        for (WebElement con : conversations) {
            name = con.findElement(By.id("txtReceiverName")).getText();
            lastMsg = con.findElement(By.id("txtLastMessage")).getText();
            lastTime = con.findElement(By.id("txtLastTime")).getText();

            if (name.equals("") || lastMsg.equals("") || lastTime.equals("")) {
                result = false;
                testlog += name + ", ";
            }
        }

        testReport(this.appiumDriver, result, "ConversationTest info includes name, last time and message as expected.", "ConversationTest missing info : " + testlog, true);

        if (result == true)
            return 1;
        return 0;
    }

    public int verifyTextMessageSentSuccessfully(String expectedMessage) {
        try {
            Thread.sleep(5000);
        } catch (Exception ex) {
        }
        String latestMessage = chatTxtContents.get(chatTxtContents.size() - 1).getText();
        boolean result = latestMessage.equals(expectedMessage);
        testReport(this.appiumDriver, result, "Text message is sent successfully.", String.format("Text message is sent incorrectly. Expected: %s, actual: %s", latestMessage, expectedMessage), true);

        if (result == true)
            return 1;
        return 0;
    }

    public int verifyTextMessageReceivedSuccessfully(String expectedMessage) {
        chatTxtContents = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("txtChatContent")));
        String latestMessage = chatTxtContents.get(chatTxtContents.size() - 1).getText();
        boolean result = latestMessage.equals(expectedMessage);
        testReport(this.appiumDriver, result, "Text message is received successfully.", String.format("Text message is received incorrectly. Expected: %s, actual: %s", latestMessage, expectedMessage), true);

        if (result == true)
            return 1;
        return 0;
    }

    public int verifyImageSentSuccessfully() {
        List<WebElement> sentPhotos = appiumDriver.findElements(By.id("imgSentPhoto"));
        boolean result = sentPhotos.size() > 0;
        testReport(this.appiumDriver, result, "Photo is sent successfully.", "Photo is sent incorrectly", true);

        if (result == true)
            return 1;
        return 0;
    }

    public int verifyImageReceivedSuccessfully() {
        List<WebElement> sentPhotos = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("imgSentPhoto")));
        boolean result = sentPhotos.size() > 0;
        testReport(this.appiumDriver, result, "Photo is received successfully.", "Photo is received incorrectly", true);

        if (result == true)
            return 1;
        return 0;
    }

    public void goBack() {
        WebElement backBtn = appiumDriver.findElement(By.id("imgChatBack"));
        backBtn.click();
    }

    public int verifySearchedConversation(String keyword) {
        keyword = keyword.toLowerCase();

        List<WebElement> cons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.id("form_contain"))));
        String testlog = "Search keyword: \"" + keyword + "\", conversations returned incorrect for user: \n";
        String name = null;
        String lastMessage = null;

        boolean result = true;
        for (int i = 0; i < cons.size(); i++) {
            name = cons.get(i).findElement(By.id("txtReceiverName")).getText().toLowerCase();
            lastMessage = cons.get(i).findElement(By.id("txtLastMessage")).getText().toLowerCase();

            if ((name.contains(keyword) == false) && (lastMessage.contains(keyword)) == false) {
                result = false;
                testlog += name + "\n";
            }

        }
        testReport(this.appiumDriver, result, "Search keyword: \"" + keyword + "\", result returned correctly.", testlog, true);

        if (result == true)
            return 1;
        return 0;
    }

    public void clearSeachTextBox() {
        WebElement delBtn = appiumDriver.findElement(By.id("rpDelSearchUser"));
        delBtn.click();
    }

    public void sendTextMessage(String message) {
        this.chatTxtBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edtChatMesage")));
        this.chatTxtBox.sendKeys(message);
        sendChatBtn.click();
    }
}