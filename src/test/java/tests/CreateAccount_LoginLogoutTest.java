package tests;

import api.Helper;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import pages.device.*;

import static org.testng.Assert.assertEquals;
import static utils.Common.generateUniqueString;
import static utils.TestReport.*;

public class CreateAccount_LoginLogoutTest extends TestBase {
    @Test
    public void loginAndLogoutWithGoogle() {
        try {

            PageHome pageHome = new PageHome(androidDriver);

            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithGoogle(gmailUser, gmailPassword);
            testResult *= pageHome.verifyUserLoginSuccessfully("Google");

            PageMore pageMore = pageHome.navigateToTab(PageHome.Tab.MORE);
            PageMyProfile pageMyProfile = pageMore.tapMyProfile();
            testResult *= pageMyProfile.verifyEmail(gmailUser);

            pageMore = pageMyProfile.goBack();

            pageMore.signOut();
            testResult *= pageHome.verifyUserLogoutSuccessfully();

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }

    //@Test
    public void loginAndLogoutWithFacebook() {
        try {

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithFacebook(facebookUser, facebookPassword);
            testResult *= pageHome.verifyUserLoginSuccessfully("Facebook");

            PageMore pageMore = pageHome.navigateToTab(PageHome.Tab.MORE);
            PageMyProfile pageMyProfile = pageMore.tapMyProfile();
            testResult *= pageMyProfile.verifyEmail(facebookUser);

            pageMore = pageMyProfile.goBack();

            pageHome = pageMore.signOut();
            testResult *= pageHome.verifyUserLogoutSuccessfully();
        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }

    //@Test
    public void createAccountAndChangePassword() {
        try {
            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            PageSignUp pageSignUp = pageLogin.clickSignUp();

            String uniqueString = generateUniqueString();
            int index = profileTestUser.indexOf("@");
            String generatedEmail = String.format("account_%s@autotest.com", uniqueString);
            System.out.println("Create account with email: " + generatedEmail);


            pageSignUp.CreateAccount(generatedEmail, profileTestPassword, "user" + uniqueString);
            testReport(androidDriver, LogStatus.INFO, String.format("Account created: %s/%s", generatedEmail, profileTestPassword), false);

            Helper apiHelper = new Helper();
            String response = apiHelper.activeAccount(generatedEmail);
            testReport(androidDriver, LogStatus.INFO, String.format("Account activated via API: %s", response), false);

            pageLogin = new PageLogin(androidDriver);
            pageHome = pageLogin.loginWithEmail(generatedEmail, profileTestPassword);
            testResult *= pageHome.verifyUserLoginSuccessfully(generatedEmail);

            PageMore pageMore = pageHome.navigateToTab(PageHome.Tab.MORE);
            pageMore.scrollingDown(androidDriver);

            PageChangePassword pageChangePassword = pageMore.tapChangePassword();
            String newPassword = "P@ssword1";
            testReport(androidDriver, LogStatus.INFO, String.format("Password will be changed to: %s then log out and log in with new password", newPassword), true);
            pageMore = pageChangePassword.changePassword(profileTestPassword, newPassword);
            pageHome = pageMore.signOut();
            pageLogin = pageHome.clickABtn();
            pageHome = pageLogin.loginWithEmail(generatedEmail, newPassword);
            testResult *= pageHome.verifyUserLoginSuccessfully(generatedEmail);

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }

    }
}
