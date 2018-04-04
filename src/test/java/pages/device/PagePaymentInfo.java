package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PagePaymentInfo extends PageBase {
    private AppiumDriver appiumDriver;

    @FindBy(id = "edt_full_name")
    WebElement fullName;

    @FindBy(id = "edt_email")
    WebElement email;

    @FindBy(id = "edt_phone_number")
    WebElement phoneNumber;

    @FindBy(id = "tvCash")
    WebElement paymentCashOnDelivery;

    @FindBy(id = "tvGotoOffice")
    WebElement paymentAtApitinyOffice;

    @FindBy(id = "lnMothod2")
    WebElement pickupTicketNote;

    @FindBy(id = "tvFee")
    WebElement fee;

    @FindBy(id = "edt_address")
    WebElement address;

    @FindBy(id = "edt_note")
    WebElement note;

    @FindBy(id = "tvTotalFee")
    WebElement total;

    @FindBy(id = "rltNext")
    WebElement nextBtn;

    public PagePaymentInfo(AppiumDriver appiumDriver) {

        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 10);
        //super.waitForVisibilityOfElement(appiumDriver, this.SignInButon);
        fullName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edt_full_name")));
    }


    public void enterTicketReceiverInfo(String fullName, String email, String phoneNumber) {

        this.fullName.sendKeys(fullName);
        this.email.sendKeys(email);
        this.phoneNumber.sendKeys(phoneNumber);
    }


    public void selectPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == PaymentMethod.CASH_ON_DELIVERY) {
            System.out.println("Select payment method: Cash on delivery");
            paymentCashOnDelivery.click();
        } else if (paymentMethod == PaymentMethod.AT_APITINY_OFFICE) {
            System.out.println("Select payment method: At Apitiny office");
            paymentAtApitinyOffice.click();
        }
    }

    public void enterAddressForCashOnDeliveryPaymentMethod(String address, String note) {
        System.out.println("Enter addess for ticket delivery");
        this.address = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edt_address")));
        this.address.sendKeys(address);
        this.note.sendKeys(note);
    }

    public Double getTotal() {
        //System.out.println(total.getText());
        String totalAmount = total.getText().replace(",", "");
        return Double.parseDouble(totalAmount.substring(0, totalAmount.indexOf(" VND")));
    }

    public PageCompleteOrder goNext() {
        this.nextBtn.click();
        return new PageCompleteOrder(this.appiumDriver);
    }
}
