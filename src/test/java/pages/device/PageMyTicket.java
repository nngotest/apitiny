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

public class PageMyTicket extends PageBase {

    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(id = "rlv_container")
    List<WebElement> Tickets;

    @FindBy(id = "txt_profile_usename")
    WebElement myBox;

    @FindBy(id = "event_name")
    WebElement eventName;

    @FindBy(id = "tv_address")
    WebElement address;

    @FindBy(id = "tv_time")
    WebElement time;

    @FindBy(id = "tv_price")
    WebElement price;

    @FindBy(id = "txt_transaction_id")
    WebElement transactionCode;

    @FindBy(id = "tv_time_buy")
    WebElement timeBuy;

    public PageMyTicket(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        super.closeTips(appiumDriver);
        this.wait = new WebDriverWait(appiumDriver, 30);
        this.myBox = this.wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txt_profile_usename")));
    }

    public Double getFee(int ticketIndex) {

        String fee = Tickets.get(ticketIndex).findElement(By.id("tv_price")).getText().replace(",", "");
        return Double.parseDouble(fee.substring(0, fee.indexOf(" VND")));
    }

    public String getTransactionCode(int ticketIndex) {
        return Tickets.get(ticketIndex).findElement(By.id("txt_transaction_id")).getText();
    }

    public String getEventName(int ticketIndex) {
        return Tickets.get(ticketIndex).findElement(By.id("event_name")).getText();
    }

    public int verifyBookingInfo(int ticketIndex, String expectedEventName, Double expectedFee, String expectedtransactionCode) {
        String actualEventName = getEventName(ticketIndex);
        Double actualFee = getFee(ticketIndex);
        String actualTransactionCode = getTransactionCode(ticketIndex);

        boolean result = expectedEventName.equals(actualEventName) && expectedFee.compareTo(actualFee) == 0 && expectedtransactionCode.equals(actualTransactionCode);
        String log = String.format("Ticket info: <br>Expect event name %s, actual %s." +
                        "<br>Expect price %.0f, transaction code %s." +
                        "<br>Actual price %.0f, transaction code %s.",
                expectedEventName, actualEventName,
                expectedFee, expectedtransactionCode,
                actualFee, actualTransactionCode);
        testReport(appiumDriver, result, log, true);

        if (result == true)
            return 1;
        return 0;
    }
}
