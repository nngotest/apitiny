package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageConfirmation extends PageBase {
    private AppiumDriver appiumDriver;

    @FindBy(id = "tvTranscode")
    WebElement transactionCode;

    @FindBy(id = "rlt_done")
    WebElement backToEventBtn;

    public PageConfirmation(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 10);
        this.transactionCode = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tvTranscode")));
    }

    public String getTransactionCode(){
        System.out.println(this.transactionCode.getText());
        return this.transactionCode.getText();
    }

    public void clickBackToEvent(){
        this.backToEventBtn.click();
    }

    public boolean verifyTicketIsBuySuccessfully(){
        if(!this.transactionCode.getText().equals(""))
            return true;
        return false;
    }
}
