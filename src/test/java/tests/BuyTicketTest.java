package tests;

import api.Helper;
import api.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import pages.device.*;

import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import static utils.Common.generateUniqueString;
import static utils.TestReport.*;

public class BuyTicketTest extends TestBase {

    @Test
    public void buyTicket_Payment_CashOnDelivery() {
        try {

            // login
            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithEmail(emailUserName, emailPassword);

            // get user info
            Helper apiHelper = new Helper();
            User user = apiHelper.getUseInfo(emailUserName, emailPassword);

            sleep(2000);

            // select an event and buy ticket
            pageHome.scrollingDown(androidDriver);
            PageEventDetails pageEventDetails = pageHome.selectANotFreeEvent();
            pageEventDetails.closeTips(androidDriver);

            PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();

            Random rd = new Random();
            int selectTicketIndex = rd.nextInt(pageChooseTicket.getAllTickectType());
            int pressTime = rd.nextInt(pageChooseTicket.getAllTickectType()) + 1;
            pageChooseTicket.pressPlusOrMinusBtn(selectTicketIndex, "+", pressTime);

            // verify if ticket fee is calculated correctly
            Double expectedFee = pageChooseTicket.calculateTicketFee();
            Double actualFee = pageChooseTicket.getTotalAmountOnFooter();
            testResult *= pageChooseTicket.verifyTotal(expectedFee, actualFee);

            // enter payment info
            pageChooseTicket.goNext();

            PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
            pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");
            PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.CASH_ON_DELIVERY;
            pagePaymentInfo.selectPaymentMethod(paymentMethod);
            pagePaymentInfo.enterAddressForCashOnDeliveryPaymentMethod("Miss Ao Dai buiding, 21 Nguyen Trung Ngan", "pls deliver the ticket at mid night");

            // get discount amount for selected event if any
            Double userPromotionAmount = getUserPromotionAmountForThisEvent(TestBase.selectedEvent, user, expectedFee);

            // verify payment info: promotion, total fee, payment method
            PageCompleteOrder pageCompleteOrder = pagePaymentInfo.goNext();
            testResult *= pageCompleteOrder.verifyDiscountAmount(userPromotionAmount);
            testResult *= pageCompleteOrder.verifyTotalAmount(expectedFee - userPromotionAmount + SHIPPING_FEE);
            testResult *= pageCompleteOrder.verifyPaymentMethod(paymentMethod);

            // complete order and check if user can buy ticket
            pageCompleteOrder.goNext();
            PageConfirmation pageConfirmation = pageCompleteOrder.confirmOKtoBuyTicket();
            testResult *= pageConfirmation.verifyTicketIsBuySuccessfully();

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }

    @Test
    public void buyTicket_Payment_GoToOffice() {
        try {

            // login
            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithGoogle(gmailUser, gmailPassword);

            // get user info
            Helper apiHelper = new Helper();
            User user = apiHelper.getUseInfo(gmailUser, gmailPassword);

            sleep(2000);

            // select an event and buy ticket
            pageHome.scrollingDown(androidDriver);
            PageEventDetails pageEventDetails = pageHome.selectANotFreeEvent();
            pageHome.closeTips(androidDriver);

            PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();
            pageChooseTicket.selectRandomTickets();

            // verify if ticket fee is calculated correctly
            Double expectedFee = pageChooseTicket.calculateTicketFee();
            Double actualFee = pageChooseTicket.getTotalAmountOnFooter();
            testResult *= pageChooseTicket.verifyTotal(expectedFee, actualFee);

            pageChooseTicket.goNext();

            // enter payment info
            PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
            pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");

            PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.AT_APITINY_OFFICE;
            pagePaymentInfo.selectPaymentMethod(paymentMethod);

            // get discount amount for selected event if any
            Double userPromotionAmount = getUserPromotionAmountForThisEvent(TestBase.selectedEvent, user, expectedFee);

            // verify payment info: promotion, total fee, payment method
            PageCompleteOrder pageCompleteOrder = pagePaymentInfo.goNext();
            testResult *= pageCompleteOrder.verifyDiscountAmount(userPromotionAmount);
            testResult *= pageCompleteOrder.verifyTotalAmount(expectedFee - userPromotionAmount);
            testResult *= pageCompleteOrder.verifyPaymentMethod(paymentMethod);

            // complete order and check if user can buy ticket
            pageCompleteOrder.goNext();
            PageConfirmation pageConfirmation = pageCompleteOrder.confirmOKtoBuyTicket();
            testResult *= pageConfirmation.verifyTicketIsBuySuccessfully();

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }

    @Test
    public void buyTicket_Payment_BankTransfer() {
        try {

            // login
            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithEmail(emailUserName, emailPassword);

            // get user info
            Helper apiHelper = new Helper();
            User user = apiHelper.getUseInfo(emailUserName, emailPassword);

            sleep(2000);

            // select an event and buy ticket
            pageHome.scrollingDown(androidDriver);
            PageEventDetails pageEventDetails = pageHome.selectANotFreeEvent();
            pageHome.closeTips(androidDriver);

            PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();
            pageChooseTicket.selectRandomTickets();

            // verify if ticket fee is calculated correctly
            Double expectedFee = pageChooseTicket.calculateTicketFee();
            Double actualFee = pageChooseTicket.getTotalAmountOnFooter();
            testResult *= pageChooseTicket.verifyTotal(expectedFee, actualFee);

            pageChooseTicket.goNext();

            // enter payment info
            PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
            pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");

            PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.BANK_TRANSFER;
            pagePaymentInfo.selectPaymentMethod(paymentMethod);
            pagePaymentInfo.scrollingDown(androidDriver);

            // get discount amount for selected event if any and verify payment info: promotion, total fee, payment method
            Double userPromotionAmount = getUserPromotionAmountForThisEvent(TestBase.selectedEvent, user, expectedFee);
            Double expectedAmount = expectedFee - userPromotionAmount;
            testResult *= pagePaymentInfo.verifyDiscountAmount(userPromotionAmount);
            testResult *= pagePaymentInfo.verifyTotalAmount(expectedAmount);
            String transactionCode = pagePaymentInfo.getTransactionCode();
            testResult *= pagePaymentInfo.verifyBankTransferInstruction(expectedAmount, transactionCode);


            // complete order and check if ticket info is correct
            pagePaymentInfo.clickComplete();
            PageMyTicket pageMyTicket = pagePaymentInfo.clickOK();

            testResult *= pageMyTicket.verifyBookingInfo(0, TestBase.selectedEvent.getName(), expectedAmount, transactionCode);

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }

    @Test
    public void buyTicket_Payment_OnePay() {
        try {

            // login
            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithEmail(emailUserName, emailPassword);

            // get user info
            Helper apiHelper = new Helper();
            User user = apiHelper.getUseInfo(emailUserName, emailPassword);

            sleep(2000);

            // select an event and buy ticket
            pageHome.scrollingDown(androidDriver);
            PageEventDetails pageEventDetails = pageHome.selectANotFreeEvent();
            pageHome.closeTips(androidDriver);

            PageChooseTicket pageChooseTicket = pageEventDetails.clickBuyTicket();
            pageChooseTicket.selectRandomTickets();

            // verify if ticket fee is calculated correctly
            Double expectedFee = pageChooseTicket.calculateTicketFee();
            Double actualFee = pageChooseTicket.getTotalAmountOnFooter();
            testResult *= pageChooseTicket.verifyTotal(expectedFee, actualFee);

            pageChooseTicket.goNext();

            // enter payment info
            PagePaymentInfo pagePaymentInfo = new PagePaymentInfo(androidDriver);
            pagePaymentInfo.enterTicketReceiverInfo(generateUniqueString(), "", "0123456798");

            PagePaymentInfo.PaymentMethod paymentMethod = PagePaymentInfo.PaymentMethod.ONE_PAY;
            pagePaymentInfo.selectPaymentMethod(paymentMethod);
            pagePaymentInfo.scrollingDown(androidDriver);

            // get discount amount for selected event if any and verify payment info: promotion, total fee, payment method
            Double userPromotionAmount = getUserPromotionAmountForThisEvent(TestBase.selectedEvent, user, expectedFee);
            Double expectedAmount = expectedFee - userPromotionAmount;
            testResult *= pagePaymentInfo.verifyDiscountAmount(userPromotionAmount);
            testResult *= pagePaymentInfo.verifyTotalAmount(expectedAmount);

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }

}
