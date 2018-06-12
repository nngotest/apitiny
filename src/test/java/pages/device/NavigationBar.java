package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.util.List;


public class NavigationBar {

    private AppiumDriver appiumDriver;
    private WebDriverWait wait;

    @FindBy(xpath = "//*[@class='android.widget.HorizontalScrollView']")////*[@class='android.support.v7.app.ActionBar$Tab']")
    WebElement navigationBar;

    @FindBy(xpath = "//*[@id='txt_noti_badge']")
    WebElement notificationBadge;

    //*[@class='android.widget.HorizontalScrollView'] //*[@class='android.widget.ImageView']
    public NavigationBar(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        this.wait = new WebDriverWait(appiumDriver, 10);
        //this.navigationBar = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='android.support.v7.app.ActionBar$Tab']")));
    }

    public <T> T navigateToTab(PageHome.Tab tab) {
        this.navigationBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.HorizontalScrollView']")));
        List<WebElement> el = this.navigationBar.findElements(By.xpath("//*[@class='android.widget.ImageView']"));

        try {
            String className = "";
            switch (tab) {
                case CONVERSTIONS:
                    el.get(1).click();
                    className = "PageConversations";
                    break;

                case NOTIFICATIONS:
                    el.get(2).click();
                    className = "PageNotifications";
                    break;

                case MORE:
                    el.get(3).click();
                    className = "PageMore";
                    break;

                default:
                    el.get(0).click();
                    className = "PageHome";
                    break;
            }
            System.out.println("className: " + className + tab);
            //String className = pageClassName.getName();
            Class<?> cls = Class.forName("pages.device." + className);

            Constructor constructor = cls.getDeclaredConstructor(AppiumDriver.class);
            System.out.println("Class initialize finished");
            return (T) constructor.newInstance(appiumDriver);

        } catch (Exception ex) {
            System.out.println("navigateToTab() Err: " + ex.getMessage());
            return null;
        }
    }

    public void openTab() {
        this.navigationBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.HorizontalScrollView']")));
        List<WebElement> el = this.navigationBar.findElements(By.xpath("//*[@class='android.widget.ImageView']"));

        for (int i = 0; i < el.size(); i++) {
            System.out.println("Click tab " + i);
            el.get(i).click();
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {

            }
        }
    }

    public boolean isNavigationBarDisplayed() {
        try {
            navigationBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='android.widget.HorizontalScrollView']")));
            return this.navigationBar.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }
}
