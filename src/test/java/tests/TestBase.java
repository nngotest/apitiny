package tests;

import api.Helper;
import api.model.Event;
import api.model.Promotion;
import api.model.User;
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
import org.testng.annotations.BeforeTest;
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
    String emailUserName;
    String emailPassword;
    String gmailUser;
    String gmailPassword;
    String facebookUser;
    String facebookPassword;
    String gmailUserEventOwner;
    String gmailPasswordEvent;
    String chatUser1FullName;
    String chatUser2FullName;
    String profileTestUser;
    String profileTestPassword;

    public static int testResult;
    public static ResourceBundle rb;
    public static int APPIUM_PORT = 4723;
    public static String APPIUM_URL = "127.0.0.1";
    public static Event selectedEvent = null;
    public static List<Integer> selectedTicketsIndex;
    public static Double SHIPPING_FEE = 20000.00;

    @BeforeTest
    public void getConfig(){

        // get config
        rb = ResourceBundle.getBundle("config");
        APPIUM_PORT = Integer.parseInt(rb.getString("APPIUM_PORT"));
        APPIUM_URL = rb.getString("APPIUM_URL");
        SHIPPING_FEE = Double.parseDouble(rb.getString("SHIPPING_FEE"));

        emailUserName = rb.getString("emailUserName");
        emailPassword = rb.getString("emailPassword");
        gmailUser = rb.getString("gmailUser");
        gmailPassword = rb.getString("gmailPassword");
        facebookUser = rb.getString("facebookUser");
        facebookPassword = rb.getString("facebookPassword");
        gmailUserEventOwner = rb.getString("gmailUserEventOwner");
        gmailPasswordEvent = rb.getString("gmailPasswordEventOwner");
        chatUser1FullName = rb.getString("chatUser1FullName");
        chatUser2FullName = rb.getString("chatUser2FullName");
        profileTestUser = rb.getString("profileTestUser");
        profileTestPassword = rb.getString("profileTestPassword");

        // build appium capability
        desiredCapabilities.setCapability("deviceName", "Android");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        desiredCapabilities.setCapability("autoAcceptAlerts", "true");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.nguyenhiepsoftware.apitiny");
        //desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activity.SplashScreenActivity");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.splash.SplashScreenScreenActivity");
    }

    @BeforeMethod
    public void setUp() throws MalformedURLException {

        System.out.println("BeforeMethod");
        selectedTicketsIndex = new ArrayList<Integer>();
        selectedEvent = null;
        testResult = 1;
        androidDriver = new AndroidDriver(new URL(APPIUM_URL + ":" + APPIUM_PORT + "/wd/hub"), desiredCapabilities);
    }

    @AfterMethod
    public void tearDown() {
        androidDriver.quit();
    }

    private Promotion getApplicablePromotionForSelectedEvent(Event selectedEvent, User user) {
        try {
            Helper apiHelper = new Helper();
            Promotion appliedPromotion = null;
            List<Promotion> promotions = apiHelper.getUserPromotions(user.getId());

            // no promotion
            if (promotions.size() == 0)
                return null;
            else {
                for (Promotion promotion : promotions) {
                    // promotion used for specific event
                    if(promotion.getUsingForEvent().equals(selectedEvent.getId())){
                        appliedPromotion = promotion;
                        break;
                    }
                    // promotion used for all events
                    else if(promotion.getUsingForEvent().equals("")){
                        appliedPromotion = promotion;
                        break;
                    }
                }
            }
            return appliedPromotion;
        } catch (Exception ex) {
            return null;
        }
    }

    public Double getUserPromotionAmountForThisEvent(Event selectedEvent, User user, Double ticketFee){
        Promotion promotion = getApplicablePromotionForSelectedEvent(selectedEvent, user);

        if (promotion != null) {
            String value = "";
            if (promotion.getIsPercent().equals("true")) {
                value = promotion.getPercentValue();
                value = value.replace("%", "");
                return (Double.parseDouble(value) * ticketFee) / 100;
            }
            else {
                value = promotion.getMoneyValue();
                return Double.parseDouble(value);
            }
        } else return 0.00;
    }

    public void sleep(int milisecond) {
        try {
            Thread.sleep(milisecond);
        } catch (Exception e) {
        }
    }
}