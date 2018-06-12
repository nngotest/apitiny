package tests;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import pages.device.*;
import utils.TestReport;

import static org.testng.Assert.assertEquals;
import static utils.Common.generateUniqueString;
import static utils.TestReport.handleExceptionAndMarkFailResult;
import static utils.TestReport.testReport;

public class EventTest extends TestBase {

    @Test
    public void interestedInEventAndTagUserToEvent() {
        try {
            String message = "[EventTest - TagUser] on " + generateUniqueString();
            String userName = chatUser1FullName;

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithEmail(emailUserName, emailPassword);

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            pageHome.scrollingDown(androidDriver);
            PageEventDetails pageEventDetails = pageHome.selectAnyEvent();

            pageEventDetails.closeTips(androidDriver);
            int expectedInterestedUsers = 0;
            int currentInterestedUsers = pageEventDetails.getNumberOfInterestedUsers();

            if (pageEventDetails.isThisUserInterestedInCurrentEvent())
                expectedInterestedUsers = currentInterestedUsers - 1;
            else
                expectedInterestedUsers = currentInterestedUsers + 1;

            pageEventDetails.clickInterested();
            testResult *= pageEventDetails.verifyNumberOfIntersetedUsers(expectedInterestedUsers);
            String eventName = pageEventDetails.getEventName();

            PageComment pageComment = pageEventDetails.clickComment();
            pageComment.sendMessageAndTagAUser(userName, message);
            testResult *= pageComment.verifyCommentAddedAndUserIsTagged(userName, message);

            pageEventDetails = pageComment.clickBack();
            pageHome = pageEventDetails.clickBack();

            PageMore pageMore = pageHome.navigateToTab(PageHome.Tab.MORE);
            PageMyProfile pageMyProfile = pageMore.tapMyProfile();
            String profileName = pageMyProfile.getProfileName();

            pageMore = pageMyProfile.goBack();
            pageHome = pageMore.signOut();

            // Sign in another user to check the notification
            testReport(androidDriver, LogStatus.INFO, "Signin another user to check the notification", false);

            pageLogin = pageHome.clickABtn();
            pageHome = pageLogin.loginWithGoogle(gmailUser, gmailPassword);
            PageNotifications pageNotifications = pageHome.navigateToTab(PageHome.Tab.NOTIFICATIONS);
            testResult *= pageNotifications.verifyNotificationInfo(0, profileName, eventName);

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }
}
