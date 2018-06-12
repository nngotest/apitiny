package tests;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import pages.device.PageConversations;
import pages.device.PageHome;
import pages.device.PageLogin;
import pages.device.PageMore;

import java.util.Random;

import static org.testng.Assert.assertEquals;
import static utils.Common.generateUniqueString;
import static utils.TestReport.handleExceptionAndMarkFailResult;
import static utils.TestReport.testReport;

public class ConversationTest extends TestBase {
    @Test
    public void searchConversationThenSendTextAndImage() {
        try {
            PageHome pageHome = new PageHome(androidDriver);

            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithEmail(emailUserName, emailPassword);

            PageConversations pageConversations = pageHome.navigateToTab(PageHome.Tab.CONVERSTIONS);
            //testResult *= pageConversations.verifyConversationsInfo();

            Random rnd = new Random();
            int index = rnd.nextInt(chatUser1FullName.length() - 1);
            String keyword = chatUser1FullName.substring(index, chatUser1FullName.length());
            pageConversations.searchConversations(keyword);
            testResult *= pageConversations.verifySearchedConversation(keyword);

            pageConversations.clearSeachTextBox();
            pageConversations.selectConverstation(chatUser1FullName);

            String chatMessage = "This message was sent at " + generateUniqueString();
            pageConversations.sendTextMessage(chatMessage);
            testResult *= pageConversations.verifyTextMessageSentSuccessfully(chatMessage);

            pageConversations.sendImage();
            testResult *= pageConversations.verifyImageSentSuccessfully();

            pageConversations.goBack();
            PageMore pageMore = pageHome.navigateToTab(PageHome.Tab.MORE);
            pageMore.scrollingDown(androidDriver);
            pageMore.signOut();

            testReport(androidDriver, LogStatus.INFO, "Signout then login with another user", false);
            pageLogin = pageHome.clickABtn();
            pageHome = pageLogin.loginWithGoogle(gmailUser, gmailPassword);
            pageConversations = pageHome.navigateToTab(PageHome.Tab.CONVERSTIONS);
            //testResult *= pageConversations.verifyConversationsInfo();
            pageConversations.selectConverstation(chatUser2FullName);
            testResult *= pageConversations.verifyTextMessageReceivedSuccessfully(chatMessage);
            testResult *= pageConversations.verifyImageReceivedSuccessfully();

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }
}
