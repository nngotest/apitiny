package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utils.TestReport.testReport;

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

    @FindBy(id = "tvOnePay")
    WebElement paymentOnePay;

    @FindBy(id = "tvTransfer")
    WebElement paymentBankTransfer;

    @FindBy(id = "lnMothod2")
    WebElement pickupTicketNote;

    @FindBy(id = "edt_address")
    WebElement address;

    @FindBy(id = "edt_note")
    WebElement note;

    @FindBy(id = "rltNext")
    WebElement nextBtn;

    @FindBy(id = "tvTotalSum")
    WebElement ticketFee;

    @FindBy(id = "tvPromotion")
    WebElement promotion;

    @FindBy(id = "tv_total")
    WebElement total;

    @FindBy(id = "tvTransactioncode")
    WebElement transactionCode;

    @FindBy(id = "tvHelp")
    WebElement bankTransferInfo;

    @FindBy(id = "tvHelp2")
    WebElement bankTransferInstruction;

    @FindBy(id = "tvTotalFee")
    WebElement totalFeeAtBottom;

    @FindBy(id = "button1")
    WebElement okBtn;

    public PagePaymentInfo(AppiumDriver appiumDriver) {

        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 30);
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
        } else if (paymentMethod == PaymentMethod.BANK_TRANSFER) {
            System.out.println("Select payment method: Bank transfer");
            paymentBankTransfer.click();
        } else if (paymentMethod == PaymentMethod.ONE_PAY) {
            System.out.println("Select payment method: One pay");
            paymentOnePay.click();
        }
    }


    public void enterAddressForCashOnDeliveryPaymentMethod(String address, String note) {
        System.out.println("Enter addess for ticket delivery");
        this.address = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edt_address")));
        this.address.sendKeys(address);
        this.note.sendKeys(note);
    }

    public PageCompleteOrder goNext() {
        this.nextBtn.click();
        return new PageCompleteOrder(this.appiumDriver);
    }

    public void clickComplete() {
        this.nextBtn.click();
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
        String total = this.total.getText().replace(",", "");
        return Double.parseDouble(total.substring(0, total.indexOf(" VND")));
    }

    public String getTransactionCode() {
        return this.transactionCode.getText();
    }

    public Double getTotalAmountAtBottom() {
        String totalAmountAtBottom = this.totalFeeAtBottom.getText().replace(",", "");
        return Double.parseDouble(totalAmountAtBottom.substring(0, totalAmountAtBottom.indexOf(" VND")));
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
        Double actualTotalAmount = this.getTotalAmount();
        Double actualTotalAmountAtBottom = this.getTotalAmountAtBottom();
        String log = String.format("Total expect: %.0f and total at page bottom %.0f." +
                        "<br>Actual total %.0f and total at page bottom %.0f.",
                expectedAmount, expectedAmount, actualTotalAmount, actualTotalAmountAtBottom);
        int result1 = expectedAmount.compareTo(actualTotalAmount);
        int result2 = expectedAmount.compareTo(actualTotalAmountAtBottom);
        boolean result = (result1 == 0) && (result2 == 0);
        testReport(appiumDriver, result, log, true);

        if (result == true)
            return 1;
        return 0;
    }

    public int verifyBankTransferInstruction(Double expectedAmount, String transactionCode) {
        String instruction = this.bankTransferInstruction.getText();
        String actualAmountString = instruction.substring(instruction.indexOf("số tiền ") + 8, instruction.indexOf(" VND")).replace(",", "");
        String actualTransactionCode = instruction.substring(instruction.indexOf("#") + 1, instruction.length());

        Double actualAmount = Double.parseDouble(actualAmountString);

        boolean result = actualAmount.compareTo(expectedAmount) == 0 && actualTransactionCode.equals(transactionCode);
        String log = String.format("Bank transfer instruction expect amount %.0f, transaction code %s." +
                        "<br>Actual amount %.0f, transaction code %s",
                expectedAmount, transactionCode, actualAmount, actualTransactionCode);
        testReport(appiumDriver, result, log, true);

        if (result == true)
            return 1;
        return 0;
    }

    public PageMyTicket clickOK() {
        //*[@id='button1']\n//*[@text='OK']
        //*[@text='OK']
        //*[@text='OK']
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@text='OK']"))).click();
        return new PageMyTicket(this.appiumDriver);
    }
}
