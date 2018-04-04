package tests;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.AppiumServer;


import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.testng.Assert.fail;

public class TestBase {

    AndroidDriver androidDriver;
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    String userName = "apitiny@clearstep.33mail.com";
    String password = "P@ssword123";


    public static ExtentTest extentTest;
    public static AppiumServer appiumServer;
    public static final int APPIUM_PORT = 4723;
    public static final String APPIUM_URL = "127.0.0.1";
    public static List<Integer> selectedTicketsIndex;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        System.out.println("BeforeMethod");
        selectedTicketsIndex = new ArrayList<Integer>();

        appiumServer = new AppiumServer();
        if (!appiumServer.checkIfServerIsRunnning(APPIUM_PORT)) {
            System.out.println("Start appium server");
            appiumServer.start();
        } else {
            System.out.println("Appium Server already running on Port: " + APPIUM_PORT);
        }

        desiredCapabilities.setCapability("deviceName", "Android");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        desiredCapabilities.setCapability("autoAcceptAlerts", "true");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.nguyenhiepsoftware.apitiny");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activity.SplashScreenActivity");
        //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, ".activity.SplashScreenActivity");

        androidDriver = new AndroidDriver(new URL(appiumServer.getServiceUrl()), desiredCapabilities);
    }

    @AfterMethod
    public void tearDown() {
        androidDriver.quit();
    }

    public void handleExceptionAndMarkFailResut(Exception ex){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        System.out.println(sStackTrace);

        testReport(LogStatus.FAIL, ex.getMessage() + "\nStackTrace: " + sStackTrace, true);
        System.out.println("Exception: " + ex.getMessage());
        fail();
    }

    public String generateUniqueString() {
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public void testReport(boolean isPassed, String passMessage, String failMessage, boolean isCaptureScreenshot) {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = extentTest.addScreenCapture(captureScreenshot());
            passMessage += screeshotPath;
            failMessage += screeshotPath;
        }

        testReport(isPassed, passMessage, failMessage);
    }

    public void testReport(LogStatus status, String msgDetail, boolean isCaptureScreenshot) {
        String screeshotPath = "";
        if (isCaptureScreenshot) {
            screeshotPath = extentTest.addScreenCapture(captureScreenshot());
            msgDetail += screeshotPath;
        }

        extentTest.log(status, msgDetail);
    }

    public void testReport(boolean isPassed, String passMessage, String failMessage) {
        if (isPassed) {
            extentTest.log(LogStatus.PASS, passMessage);
        } else {
            extentTest.log(LogStatus.FAIL, failMessage);
        }
    }

    public String captureScreenshot() {
        String filePath = "";
        try {
            String workingDir = System.getProperty("user.dir");
            filePath = workingDir + "/report/" + generateUniqueString() + ".png";
            File scrFile = ((TakesScreenshot) androidDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(filePath));
        } catch (Exception e) {

        } finally {
            return filePath;
        }
    }
}



