package pages.device;

import api.Helper;
import api.model.Event;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static utils.TestReport.testReport;

public class PageHome extends PageBase {
    private AppiumDriver appiumDriver;
    private WebDriverWait wait;
    NavigationBar navBar;

    public enum Tab {
        HOME,
        CONVERSTIONS,
        NOTIFICATIONS,
        MORE
    }

    @FindBy(xpath = "//*[@text='ALLOW']")
    WebElement allow;

    @FindBy(id = "recycler_category")
    WebElement categoriesSection;

    @FindBy(id = "txt_category")
    WebElement categories;

    @FindBy(id = "recycler_live_events")
    WebElement liveEventsSection;

    @FindBy(id = "recycler_most_like_events")
    WebElement mostInterestSection;

    @FindBy(id = "recycler_free_events")
    WebElement freeEventSection;

    @FindBy(id = "img_search")
    WebElement searchBtn;

    @FindBy(id = "edtSearch")
    WebElement searchTxtBox;

    @FindBy(id = "dontHaveEvent")
    WebElement eventNotFound;

    @FindBy(id = "txt_hot_events")
    WebElement hotEvent;

    public enum EventSection {
        LIVE,
        MOST_INTERESTED,
        FREE
    }

    public PageHome(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        this.wait = new WebDriverWait(appiumDriver, 30);
        this.categories = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txt_category")));
        this.navBar = new NavigationBar(appiumDriver);
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

        WebElement tooltip = null;
        do {
            hotEvent = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txt_hot_events")));
            hotEvent.click();
            try {
                tooltip = appiumDriver.findElement(By.id("toolTip_container"));
            } catch (Exception ex) {
                System.out.println("ex: " + ex.getMessage());
            }
        } while (tooltip == null);
    }

    public PageLogin clickABtn() {
        WebElement buttonA = wait.until(ExpectedConditions.elementToBeClickable(By.id("img_my_box")));
        buttonA.click();

        return new PageLogin(appiumDriver);
    }

    //region live events
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

    public void getEvenInfo() {

        List<WebElement> events = liveEventsSection.findElements(By.className("android.widget.FrameLayout"));

        System.out.println(events.get(0).findElement(By.id("txt_name")).getText());
        System.out.println(events.get(0).findElement(By.id("txt_location")).getText());
        System.out.println(events.get(0).findElement(By.id("txt_time")).getText());
        events.get(0).findElement(By.id("img_photo")).click();
    }

    public List<WebElement> getSearchedEvents() {

        List<WebElement> eventOnUI = new ArrayList<WebElement>();
        try {
            eventOnUI = liveEventsSection.findElements(By.className("android.widget.FrameLayout"));
        } catch (Exception ex) {
        }

        super.scrollingDown(appiumDriver);
        try {
            eventOnUI.addAll(mostInterestSection.findElements(By.className("android.widget.FrameLayout")));
        } catch (Exception ex) {
        }

        super.scrollingDown(appiumDriver);
        try {
            eventOnUI.addAll(freeEventSection.findElements(By.className("android.widget.FrameLayout")));
        } catch (Exception ex) {
        }

        return eventOnUI;
    }
    //endregion

    public void getMostInterestedEventsInfo() {

        List<WebElement> events = mostInterestSection.findElements(By.className("android.widget.FrameLayout"));
        for (int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i).findElement(By.id("txt_name")).getText());
            System.out.println(events.get(i).findElement(By.id("txt_location")).getText());
            System.out.println(events.get(i).findElement(By.id("txt_time")).getText());
        }
    }

    public PageEventDetails selectAnEventByName(EventSection eventSection, String eventName) {

        List<WebElement> events;
        Boolean isFound = false;
        WebElement selectedEvent = null;


        switch (eventSection) {
            case MOST_INTERESTED:
                events = mostInterestSection.findElements(By.className("android.widget.FrameLayout"));
                break;
            case FREE:
                events = freeEventSection.findElements(By.className("android.widget.FrameLayout"));
                break;
            default:
                events = liveEventsSection.findElements(By.className("android.widget.FrameLayout"));
        }

        System.out.println(String.format("events.size: %d, isFound: %s", events.size(), isFound));
        do {
            try {
                String name = "";
                for (int i = 0; i < events.size(); i++) {
                    name = events.get(i).findElement(By.id("txt_name")).getText();
                    System.out.println("event: " + name);
                    if (name.toLowerCase().equals(eventName.toLowerCase())) {
                        isFound = true;
                        selectedEvent = events.get(i);
                        System.out.println("found event: " + eventName);
                        selectedEvent.findElement(By.id("img_photo")).click();
                        break;
                    }
                }

                if (isFound == false) {
                    System.out.println("scrolling...");
                    srollingLeft(appiumDriver);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("scrolling...");
                srollingLeft(appiumDriver);
            }
        } while (isFound == false);

        return new PageEventDetails(this.appiumDriver);

    }

    public PageEventDetails selectANotFreeEvent() {

        List<WebElement> events;
        Boolean isFound = false;
        String eventName = "";
        Helper apiHelper = new Helper();

        events = mostInterestSection.findElements(By.className("android.widget.FrameLayout"));
        events.addAll(liveEventsSection.findElements(By.className("android.widget.FrameLayout")));

        List<String> notFreeEventNames = new ArrayList<String>();
        List<Event> notFreeEvents = new ArrayList<Event>();

        try {
            notFreeEvents = apiHelper.getNotFreeEvents();
            notFreeEventNames = apiHelper.getEventsName(notFreeEvents);
        } catch (Exception ex) {
        }

        System.out.println("# events: " + events.size());
        Random rd = new Random();
        int index = 0;
        do {
            index = rd.nextInt(events.size());
            System.out.println("index " + index);

            eventName = events.get(index).findElement(By.id("txt_name")).getText();
            System.out.println("eventName " + eventName);

            if (notFreeEventNames.contains(eventName)) {
                System.out.println("Selected event: " + eventName);
                for (Event event:notFreeEvents) {
                    if(event.getName().equals(eventName)){
                        TestBase.selectedEvent = notFreeEvents.get(index);
                        break;
                    }
                }

                events.get(index).findElement(By.id("img_photo")).click();
                isFound = true;
            }
            events.remove(index);
            if ((events.size() == 0) && (isFound == false)) {
                srollingLeft(appiumDriver);
            }
        } while (isFound == false);

        return new PageEventDetails(this.appiumDriver);
    }

    public PageEventDetails selectAnyEvent() {

        List<WebElement> events = new ArrayList<WebElement>();
        Boolean isFound = false;
        String eventName = "";
        Helper apiHelper = new Helper();

        try {
            events = mostInterestSection.findElements(By.className("android.widget.FrameLayout"));
        } catch (Exception ex) {
        }

        try {
            events.addAll(liveEventsSection.findElements(By.className("android.widget.FrameLayout")));
        } catch (Exception ex) {
        }
        try {
            events.addAll(freeEventSection.findElements(By.className("android.widget.FrameLayout")));
        } catch (Exception ex) {
        }

        Random rd = new Random();
        int index = 0;
        index = rd.nextInt(events.size());
        events.get(index).findElement(By.id("img_photo")).click();

        return new PageEventDetails(this.appiumDriver);
    }

    public boolean isNavigationBarDisplayed() {
        return navBar.isNavigationBarDisplayed();
    }

    public <T> T navigateToTab(Tab tab) {
        return navBar.navigateToTab(tab);
    }

    public void openTab() {
        navBar.openTab();
    }

    public int verifyUserLoginSuccessfully(String account) {
        boolean result = navBar.isNavigationBarDisplayed();
        testReport(this.appiumDriver, result, String.format("User can login with %s", account), String.format("User can NOT login with %s", account), true);

        if(result == true)
            return 1;
        return 0;
    }

    public int verifyUserLogoutSuccessfully() {
        boolean result = navBar.isNavigationBarDisplayed() == false;

        testReport(this.appiumDriver, result, "User logout successfully", "User can NOT logout", true);
        if(result == true)
            return 1;
        return 0;
    }

    public void searchEvent(String keyword) {
        searchBtn.click();
        searchTxtBox.sendKeys(keyword);

        try {
            appiumDriver.hideKeyboard();
        } catch (Exception e) {
            System.out.println("Virtual keyboard is not shown.");
        }
    }

    public int verifySearchedEvent(String keyword) {
        keyword = keyword.toLowerCase();
        List<WebElement> events = getSearchedEvents();
        String testlog = "Search keyword: \"" + keyword + "\", event returned incorrect:\n";
        String name = null;
        String address = null;

        boolean result = true;
        for (int i = 0; i < events.size(); i++) {
            name = events.get(i).findElement(By.id("txt_name")).getText().toLowerCase();
            address = events.get(i).findElement(By.id("txt_location")).getText().toLowerCase();

            if ((name.contains(keyword) == false) && (address.contains(keyword)) == false) {
                result = false;
                testlog += name + "\n";
            }

        }
        testReport(this.appiumDriver, result, "Search keyword: \"" + keyword + "\", result returned correctly.", testlog, true);
        if(result == true)
            return 1;
        return 0;
    }

    public int verifySearchedEventNotFound(String keyword) {
        keyword = keyword.toLowerCase();
        List<WebElement> events = getSearchedEvents();
        String testlog = "Search keyword: \"" + keyword + "\", event returned OR the \"Event not found\" message does not displayed";
        boolean result = (events.size() > 0 == false) && (eventNotFound.getText() != "Event not found");
        testReport(this.appiumDriver, result, "Search keyword: \"" + keyword + "\", returned \"Event not found\" as expected.", testlog, true);

        if(result == true)
            return 1;
        return 0;
    }
}
