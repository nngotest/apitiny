package pages.device;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageHome extends PageBase {
    private AppiumDriver appiumDriver;
    NavigationBar navigationBar;

    @FindBy(xpath = "//*[@text='ALLOW']")
    WebElement allow;

    @FindBy(id = "recycler_category")
    WebElement categoriesSection;

    @FindBy(id = "txt_category")
    WebElement categories;

    @FindBy(id = "recycler_live_events")
    WebElement liveEventsSection;

    @FindBy(id = "recycler_most_live_events")
    WebElement mostInterestSection;
//    @FindBy(id = "img_photo")
//    WebElement liveBtn;
//
//    @FindBy(id = "checkInNum")
//    WebElement checkinNum;
//
//    @FindBy(id = "txt_name")
//    WebElement eventName;
//
//    @FindBy(id = "txt_location")
//    WebElement address;
//
//    @FindBy(id = "txt_time")
//    WebElement time;
//
//    @FindBy(id = "img_interested")
//    WebElement interestBtn;
//
//    @FindBy(id = "txt_like_count")
//    WebElement interestNum;
//
//    @FindBy(id = "imgLiveEvent")
//    WebElement liveBtn;

    public PageHome(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 10);
        this.categories = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txt_category")));
        this.navigationBar = new NavigationBar(appiumDriver);
    }

    public void allowAccess() {
        try {

            while (allow.isDisplayed()) {
                allow = appiumDriver.findElement(By.xpath("//*[@text='ALLOW']"));
                System.out.println("click allow");
                allow.click();
            }
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    public void closeYoutubeVideo() {

        WebElement sliderImage = wait.until(ExpectedConditions.elementToBeClickable(By.id("daimajia_slider_image")));
        //System.out.println("close Youtube");
        sliderImage.click();
    }

    public PageLogin clickABtn() {
        WebElement buttonA = wait.until(ExpectedConditions.elementToBeClickable(By.id("img_my_box")));
        buttonA.click();

        return new PageLogin(appiumDriver);
    }

    public int countLiveEvents() {

        return liveEventsSection.findElements(By.className("android.widget.FrameLayout")).size();
    }

    public PageEventDetails selectALiveEvent(int eventIndex) {
        List<WebElement> events = liveEventsSection.findElements(By.className("android.widget.FrameLayout"));
        events.get(eventIndex).findElement(By.id("img_photo")).click();

        return new PageEventDetails(this.appiumDriver);
    }

    public PageEventDetails selectALiveEvent(String eventName) {
        List<WebElement> liveEvents = liveEventsSection.findElements(By.className("android.widget.FrameLayout"));

        String name = "";
        for (int i = 0; i < liveEvents.size(); i++) {
            name = liveEvents.get(i).findElement(By.id("txt_name")).getText();
            if (name.toLowerCase().equals(eventName.toLowerCase())) {
                liveEvents.get(i).findElement(By.id("img_photo")).click();
                break;
            }
        }

        return new PageEventDetails(this.appiumDriver);
    }

    public void getName() {

        List<WebElement> events = liveEventsSection.findElements(By.className("android.widget.FrameLayout"));

        System.out.println(events.get(0).findElement(By.id("txt_name")).getText());
        System.out.println(events.get(0).findElement(By.id("txt_location")).getText());
        System.out.println(events.get(0).findElement(By.id("txt_time")).getText());
        events.get(0).findElement(By.id("img_photo")).click();
    }
}
