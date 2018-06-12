package tests;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;
import pages.device.PageHome;
import pages.device.PageLogin;
import pages.device.PageMore;
import pages.device.PageMyProfile;

import static org.testng.Assert.assertEquals;
import static utils.Common.generateUniqueString;
import static utils.TestReport.handleExceptionAndMarkFailResult;
import static utils.TestReport.testReport;

public class ProfileTest extends TestBase {

    @Test
    public void changeInformation() {
        try {
            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            PageLogin pageLogin = pageHome.clickABtn();
            pageLogin.closeTips(androidDriver);

            pageHome = pageLogin.loginWithEmail(profileTestUser, profileTestPassword);

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }

            PageMore pageMore = pageHome.navigateToTab(PageHome.Tab.MORE);
            PageMyProfile pageMyProfile = pageMore.tapMyProfile();

            String uniqueString = generateUniqueString();
            String newName = "auto" + uniqueString;
            String newPhone = uniqueString.replace("_", "");
            testReport(androidDriver, LogStatus.INFO, String.format("Change profile: name to %s, phone to %s", newName, newPhone), false);
            pageMyProfile.editInformation(newName, newPhone);
            testResult *= pageMyProfile.verifiedNameAndPhone(newName, newPhone);

        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }
}
