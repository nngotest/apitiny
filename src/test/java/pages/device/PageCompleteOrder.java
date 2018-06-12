package pages.device;

import api.Helper;
import api.model.Event;
import api.model.Promotion;
import api.model.SignInResponse;
import api.model.User;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static utils.TestReport.testReport;

public class PageCompleteOrder extends PageBase {
    private AppiumDriver appiumDriver;

    @FindBy(id = "tv_date")
    WebElement date;

    @FindBy(id = "tv_payment_name")
    WebElement event;

    @FindBy(id = "tv_ticket_info")
    WebElement ticketInfo;

    @FindBy(id = "tvTotalSum")
    WebElement ticketFee;

    @FindBy(id = "tvPromotion")
    WebElement promotion;

    @FindBy(id = "tvShippingFee")
    WebElement shippingFee;

    @FindBy(id = "tv_total")
    WebElement totalAmount;

    @FindBy(id = "tv_payment_method")
    WebElement paymentMethod;

    @FindBy(id = "tvPrice")
    WebElement totalAmountAtBottom;

    @FindBy(id = "rlt_pay")
    WebElement completeBtn;

    public PageCompleteOrder(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 10);
        this.date = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tv_date")));
    }

    public Double getTotalAmountAtBottom() {
        String totalAmount = this.totalAmountAtBottom.getText().replace(",", "");
        return Double.parseDouble(totalAmount.substring(0, totalAmount.indexOf(" VND")));
    }

    public void goNext() {
        this.completeBtn.click();
    }

    public PaymentMethod getSelectedPaymentMethod() {
        String actualPaymentMethod = this.paymentMethod.getText().toLowerCase();
        if (actualPaymentMethod.equals("pick up at apitiny office"))
            return PaymentMethod.AT_APITINY_OFFICE;
        return PaymentMethod.CASH_ON_DELIVERY;
    }

    public PageConfirmation confirmOKtoBuyTicket() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='OK']"))).click();
        return new PageConfirmation(this.appiumDriver);
    }

    public int verifyPaymentMethod(PaymentMethod selectedPaymentMethod) {

        int result = 1;

        if (selectedPaymentMethod != this.getSelectedPaymentMethod()) {
            result = 0;
        }

        testReport(appiumDriver, result == 1, "Payment method is \"" + this.getSelectedPaymentMethod() + "\" as expected ", "Payment method is \"" + this.getSelectedPaymentMethod() + "\". Expect: " + selectedPaymentMethod, true);

        return result;
    }

    public Double getTicketFee() {
        String ticketFee = this.ticketFee.getText().replace(",", "");
        return Double.parseDouble(ticketFee.substring(0, ticketFee.indexOf(" VND")));
    }


    public Double getDiscountAmount() {
        String promotion = this.promotion.getText().replace(",", "");
        return Double.parseDouble(promotion.substring(0, promotion.indexOf(" VND")));
    }

    public Double getTotalAmount() {
        String totalAmount = this.totalAmountAtBottom.getText().replace(",", "");
        return Double.parseDouble(totalAmount.substring(0, totalAmount.indexOf(" VND")));
    }

    public int verifyDiscountAmount(Double expectedAmount) {
        int result = 1;
        String log = "";
        if (expectedAmount == 0.00) {
            log = "";
            boolean isDisplayed = true;
            try {
                if (this.promotion.isDisplayed()) {
                    log = "The promotion should NOT be applied";
                    testReport(appiumDriver, false, log, true);
                    result = 0;
                }
            } catch (Exception ex) {
                log = "The promotion is not applied as expected";
                testReport(appiumDriver, true, log, true);
                result = 1;
            }
        } else {
            Double actualAmount = this.getDiscountAmount();
            log = String.format("Discount amount expect: %.0f, actual %.0f", expectedAmount, actualAmount);
            int compareResult = expectedAmount.compareTo(actualAmount);
            testReport(appiumDriver, compareResult == 0, log, true);

            if (compareResult == 0) {
                result = 1;
            } else {
                result = 0;
            }
        }
        return result;
    }

    public int verifyTotalAmount(Double expectedAmount) {
        Double actualAmount = this.getTotalAmountAtBottom();
        String log = String.format("Total amount expect: %.0f, actual %.0f", expectedAmount, actualAmount);
        int compareResult = expectedAmount.compareTo(actualAmount);
        testReport(appiumDriver, compareResult == 0, log, true);

        if (compareResult == 0)
            return 1;
        return 0;
    }
}
