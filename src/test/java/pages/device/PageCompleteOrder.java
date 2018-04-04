package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @FindBy(id = "tvShippingFee")
    WebElement shippingFee;

    @FindBy(id = "tv_total")
    WebElement totalCost;

    @FindBy(id = "tv_payment_method")
    WebElement paymentMethod;

    @FindBy(id = "tvPrice")
    WebElement total;

    @FindBy(id = "rlt_pay")
    WebElement completeBtn;

    public PageCompleteOrder(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 10);
        this.date = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tv_date")));
    }

    public Double getTotal() {
        String totalAmount = total.getText().replace(",", "");
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

    public boolean verifyPaymentMethod(PaymentMethod selectedPaymentMethod) {
        if (selectedPaymentMethod == this.getSelectedPaymentMethod())
            return true;
        return false;
    }
}
