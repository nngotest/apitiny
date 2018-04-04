package tests;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.device.*;

import static org.testng.Assert.fail;

public class SampleTest extends TestBase {
    //AndroidDriver androidDriver;
    //DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


    //@Test
    public void buyTicket_Payment_GoToOffice() {

        try {

            WebDriverWait wait = new WebDriverWait(androidDriver, 5);

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.login(userName, password);

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }


            ///pageHome.getName();
            //PageEventDetails pageEventDetails = pageHome.selectALiveEvent("advance 2018");
            PageEventDetails pageEventDetails = pageHome.selectALiveEvent(0);
            pageHome.closeTips(androidDriver);

            PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();

            Random rd = new Random();
            int pressTime = rd.nextInt(pageChooseTicket.getAllTickectType()) + 1;
            pageChooseTicket.pressPlusOrMinusBtn(3, "+", pressTime);

            Double expectedFee = pageChooseTicket.calculateTicketFee();
            Double actualFee = pageChooseTicket.getTotalAmountOnFooter();
            testReport(pageChooseTicket.verifyTotal(expectedFee, actualFee), "Ticket fee is correct", "Expected ticket fee " + expectedFee + ". Actual: " + actualFee, true);

            pageChooseTicket.goNext();

            PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
            pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");

            PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.AT_APITINY_OFFICE;
            pagePaymentInfo.selectPaymentMethod(paymentMethod);
            pagePaymentInfo.getTotal();
            pagePaymentInfo.goNext();

            PageCompleteOrder pageCompleteOrder = new PageCompleteOrder(androidDriver);
            pageCompleteOrder.getTotal();
            testReport(pageCompleteOrder.verifyPaymentMethod(paymentMethod), "Payment method is \"At Apitiny office\" as expected ", "payment method is not \"At Apitiny office\" as expected", true);

//            pageCompleteOrder.goNext();
//            PageConfirmation pageConfirmation = pageCompleteOrder.confirmOKtoBuyTicket();
//            testReport(pageConfirmation.verifyTicketIsBuySuccessfully(), "payment method1", "payment method12", true);
        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String sStackTrace = sw.toString(); // stack trace as a string
            System.out.println(sStackTrace);

            testReport(LogStatus.FAIL, ex.getMessage() + "\nStackTrace: " + sStackTrace, true);
            System.out.println("Exception: " + ex.getMessage());
            fail();
        }
    }

    //@Test
    public void buyTicket_Payment_CashOnDelivery() {
        try {

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.login(userName, password);

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }


            ///pageHome.getName();
            PageEventDetails pageEventDetails = pageHome.selectALiveEvent("advance 2018");
            //PageEventDetails pageEventDetails = pageHome.selectALiveEvent(0);
            pageHome.closeTips(androidDriver);

            PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();

            Random rd = new Random();
            int pressTime = rd.nextInt(pageChooseTicket.getAllTickectType()) + 1;
            pageChooseTicket.pressPlusOrMinusBtn(1, "+", pressTime);

            Double expectedFee = pageChooseTicket.calculateTicketFee();
            Double actualFee = pageChooseTicket.getTotalAmountOnFooter();
            testReport(pageChooseTicket.verifyTotal(expectedFee, actualFee), "Ticket fee is correct", "Expected ticket fee " + expectedFee + ". Actual: " + actualFee, true);

            pageChooseTicket.goNext();

            PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
            pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");
            PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.CASH_ON_DELIVERY;
            pagePaymentInfo.selectPaymentMethod(paymentMethod);
            pagePaymentInfo.enterAddressForCashOnDeliveryPaymentMethod("Miss Ao Dai buiding, 21 Nguyen Trung Ngan", "pls deliver the ticket at mid night");
            pagePaymentInfo.goNext();

            PageCompleteOrder pageCompleteOrder = new PageCompleteOrder(androidDriver);
            testReport(pageCompleteOrder.verifyPaymentMethod(paymentMethod), "Payment method is \"Cash on delevery\" as expected ", "payment method is not \"Cash on delevery\" as expected", true);

            pageCompleteOrder.goNext();
            //PageConfirmation pageConfirmation = pageCompleteOrder.confirmOKtoBuyTicket();
            //testReport(pageConfirmation.verifyTicketIsBuySuccessfully(), "payment method2", "payment method12", true);
        } catch (Exception ex) {
            handleExceptionAndMarkFailResut(ex);
        }
    }

    //@Test
    public void buyTicket_Payment_CashOnDelivery_BK() {
        try {

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.login(userName, password);

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }


            ///pageHome.getName();
            PageEventDetails pageEventDetails = pageHome.selectALiveEvent("advance 2018");
            //PageEventDetails pageEventDetails = pageHome.selectALiveEvent(0);
            pageHome.closeTips(androidDriver);
            //PageEventDetails pageEventDetails = pageHome.selectALiveEvent("advance 2018");

            PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();

            //            Random rd = new Random();
//            int pressTime = rd.nextInt(pageChooseTicket.getAllTickectType()) + 1;
//            pageChooseTicket.pressPlusOrMinusBtn(1, "+", pressTime);

            pageChooseTicket.selectRandomTickets();


            Double expectedFee = pageChooseTicket.calculateTicketFee();
            Double actualFee = pageChooseTicket.getTotalAmountOnFooter();

            testReport(pageChooseTicket.verifyTotal(expectedFee, actualFee), "Ticket fee is correct", "Expected ticket fee " + expectedFee + ". Actual: " + actualFee, true);

            System.out.println("111");

            pageChooseTicket.goNext();

            PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
            pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");

            PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.CASH_ON_DELIVERY;
            pagePaymentInfo.selectPaymentMethod(paymentMethod);
            pagePaymentInfo.enterAddressForCashOnDeliveryPaymentMethod("Miss Ao Dai buiding, 21 Nguyen Trung Ngan", "pls deliver the ticket at mid night");
            pagePaymentInfo.goNext();
            System.out.println("222");
            PageCompleteOrder pageCompleteOrder = new PageCompleteOrder(androidDriver);
            testReport(pageCompleteOrder.verifyPaymentMethod(paymentMethod), "Payment method is \"Cash on delevery\" as expected ", "payment method is not \"Cash on delevery\" as expected", true);

            pageCompleteOrder.goNext();
            //PageConfirmation pageConfirmation = pageCompleteOrder.confirmOKtoBuyTicket();
            //testReport(pageConfirmation.verifyTicketIsBuySuccessfully(), "payment method2", "payment method12", true);
        } catch (Exception ex) {
            handleExceptionAndMarkFailResut(ex);
        }
    }

    //@Test
    public void buyTicket() {
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

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }


        pageHome.getName();
        pageHome.closeTips(androidDriver);
        //pageHome.selectALiveEvent(0);
        PageEventDetails pageEventDetails = new PageEventDetails(androidDriver);

        PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();
        pageChooseTicket.getTicketInfo(1);

        Random rd = new Random();
        int numberOfTicketType = pageChooseTicket.getAllTickectType();
        int selectedTicketIndex = rd.nextInt(numberOfTicketType);
        int pressTime = rd.nextInt(numberOfTicketType + 1);
        //pageChooseTicket.pressPlusOrMinusBtn(1, "+", pressTime);

        //Select ticket type that not sold out yet
//        if(pageChooseTicket.isTicketSoldOut(selectedTicketIndex))
//            if(selectedTicketIndex < numberOfTicketType)
//                selectedTicketIndex += 1;
//            else
//                selectedTicketIndex -= 1;
        //pageChooseTicket.pressPlusOrMinusBtn(selectedTicketIndex, "+", pressTime);
        pageChooseTicket.selectRandomTickets();

        pageChooseTicket.getTotalAmountOnFooter();
        pageChooseTicket.isTicketSoldOut(0);

        pageChooseTicket.goNext();

        PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
        pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");
        pagePaymentInfo.selectPaymentMethod(PagePaymentInfo.PaymentMethod.AT_APITINY_OFFICE);
        pagePaymentInfo.getTotal();
        pagePaymentInfo.goNext();

        PageCompleteOrder pageCompleteOrder = new PageCompleteOrder(androidDriver);
        pageCompleteOrder.getTotal();
        pageCompleteOrder.goNext();
        //pageCompleteOrder.confirmOKtoBuyTicket();


        //androidDriver.findElement(By.xpath("//*[@text='User is not found']")).click();
    }

    @Test
    public void buyTicket_Payment_CashOnDelivery_BK() {
        try {

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.login(userName, password);

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }


            ///pageHome.getName();
            PageEventDetails pageEventDetails = pageHome.selectALiveEvent("advance 2018");
            //PageEventDetails pageEventDetails = pageHome.selectALiveEvent(0);
            pageHome.closeTips(androidDriver);
            //PageEventDetails pageEventDetails = pageHome.selectALiveEvent("advance 2018");

            PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();

            //            Random rd = new Random();
//            int pressTime = rd.nextInt(pageChooseTicket.getAllTickectType()) + 1;
//            pageChooseTicket.pressPlusOrMinusBtn(1, "+", pressTime);

            pageChooseTicket.selectRandomTickets();


            Double expectedFee = pageChooseTicket.calculateTicketFee();
            Double actualFee = pageChooseTicket.getTotalAmountOnFooter();

            testReport(pageChooseTicket.verifyTotal(expectedFee, actualFee), "Ticket fee is correct", "Expected ticket fee " + expectedFee + ". Actual: " + actualFee, true);

            System.out.println("111");

            pageChooseTicket.goNext();

            PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
            pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");

            PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.CASH_ON_DELIVERY;
            pagePaymentInfo.selectPaymentMethod(paymentMethod);
            pagePaymentInfo.enterAddressForCashOnDeliveryPaymentMethod("Miss Ao Dai buiding, 21 Nguyen Trung Ngan", "pls deliver the ticket at mid night");
            pagePaymentInfo.goNext();
            System.out.println("222");
            PageCompleteOrder pageCompleteOrder = new PageCompleteOrder(androidDriver);
            testReport(pageCompleteOrder.verifyPaymentMethod(paymentMethod), "Payment method is \"Cash on delevery\" as expected ", "payment method is not \"Cash on delevery\" as expected", true);

            pageCompleteOrder.goNext();
            //PageConfirmation pageConfirmation = pageCompleteOrder.confirmOKtoBuyTicket();
            //testReport(pageConfirmation.verifyTicketIsBuySuccessfully(), "payment method2", "payment method12", true);
        } catch (Exception ex) {
            handleExceptionAndMarkFailResut(ex);
        }
    }
}
