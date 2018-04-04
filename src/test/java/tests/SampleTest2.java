package tests;

import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Random;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.device.*;

public class SampleTest2 extends TestBase{
    AndroidDriver androidDriver;
    DesiredCapabilities dc = new DesiredCapabilities();



    @Test
    public void buyTicket_Payment_GoToOffice() {
        WebElement allow;
        String userName = "apitiny@clearstep.33mail.com";
        String password = "P@ssword123";

        WebDriverWait wait = new WebDriverWait(androidDriver, 5);

        PageHome pageHome = new PageHome(androidDriver);
        pageHome.closeYoutubeVideo();
        pageHome.closeTips(androidDriver);

        PageLogin pageLogin = pageHome.clickABtn();
        pageLogin.closeTips(androidDriver);

        pageHome = pageLogin.login(userName, password);
//
//        String path = "/Users/nhanngo/Desktop/Screen Shot 2018-03-19 at 2.33.55 PM.png";
//        extentTest.log(LogStatus.FAIL, "testetset");
//        extentTest.log(LogStatus.PASS,path);
//        extentTest.log(LogStatus.PASS,"test " + path);
//        String tmp = TestBase.extentTest.addScreenCapture(path);
//        extentTest.log(LogStatus.PASS, tmp);
//        extentTest.log(LogStatus.PASS, "testinfo" + tmp);

        try {
            Thread.sleep(2000);
        }catch (Exception e){}


        pageHome.getName();
        pageHome.closeTips(androidDriver);
        //PageEventDetails pageEventDetails = pageHome.selectALiveEvent("advance 2018");
        pageHome.getName();

        PageEventDetails pageEventDetails = pageHome.selectALiveEvent(0);

        PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();
        pageChooseTicket.getTicketInfo(1);

        Random rd = new Random();
        int pressTime = rd.nextInt(pageChooseTicket.getAllTickectType()) + 1;
        pageChooseTicket.pressPlusOrMinusBtn(1, "+", pressTime);
        pageChooseTicket.getTotalAmountOnFooter();
        pageChooseTicket.isTicketSoldOut(0);

        pageChooseTicket.goNext();

        PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
        pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");

        PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.AT_APITINY_OFFICE;
        pagePaymentInfo.selectPaymentMethod(paymentMethod);
        pagePaymentInfo.getTotal();
        pagePaymentInfo.goNext();

        PageCompleteOrder pageCompleteOrder = new PageCompleteOrder(androidDriver);
        pageCompleteOrder.getTotal();
        if(pageCompleteOrder.verifyPaymentMethod(paymentMethod))
            extentTest.log(LogStatus.PASS, "Payment method is \"At Apitiny Office\"");
        else
            extentTest.log(LogStatus.FAIL, "Payment method is not \"At Apitiny Office\"");
        pageCompleteOrder.goNext();
        PageConfirmation pageConfirmation = pageCompleteOrder.confirmOKtoBuyTicket();
        if(pageConfirmation.verifyTicketIsBuySuccessfully())
            extentTest.log(LogStatus.PASS, "User can buy ticket with payment \"At Apitiny Office\"");
        else
            extentTest.log(LogStatus.FAIL, "User can NOT buy ticket with payment \"At Apitiny Office\"");

        //androidDriver.findElement(By.xpath("//*[@text='User is not found']")).click();
    }

    @Test
    public void testSample2() {
        WebElement allow;
        String userName = "apitiny@clearstep.33mail.com";
        String password = "P@ssword123";

        WebDriverWait wait = new WebDriverWait(androidDriver, 5);

        PageHome pageHome = new PageHome(androidDriver);
        pageHome.allowAccess();

        pageHome.closeYoutubeVideo();
        pageHome.closeTips(androidDriver);

        PageLogin pageLogin = pageHome.clickABtn();
        pageLogin.closeTips(androidDriver);
        pageLogin.login(userName, password);

        //androidDriver.findElement(By.xpath("//*[@text='User is not found']")).click();
    }



}
