package pages.device;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Constructor;
import java.time.Duration;

public abstract class PageBase {
    @FindBy(id = "toolTip_container")
    WebElement tooltip;

    @FindBy(id = "backBtn")
    WebElement backBtn;

    @FindBy(id = "nextBtn")
    WebElement nextBtn;

    WebDriverWait wait;

    public enum PaymentMethod {
        CASH_ON_DELIVERY,
        AT_APITINY_OFFICE
    }

    public void closeTips(AppiumDriver appiumDriver) {
        try {
            wait = new WebDriverWait(appiumDriver, 3);
            tooltip = wait.until(ExpectedConditions.elementToBeClickable(By.id("toolTip_container")));
            while (tooltip.isDisplayed()) {
                tooltip.click();
                tooltip = wait.until(ExpectedConditions.elementToBeClickable(By.id("toolTip_container")));
            }
        } catch (Exception e) {
        }
    }

    public void goBack() {
        backBtn.click();
    }

    public <T> T goToNextPage(Class<T> NextPageClassName, AppiumDriver appiumDriver) {
        try {
            nextBtn.click();
            String className = NextPageClassName.getName();
            Class<?> cls = Class.forName(className);

            Constructor constructor = cls.getDeclaredConstructor(AppiumDriver.class);//NextPageClassName.getClass());
            //return cls.getConstructor(NextPageClassName.getClass()).newInstance(driver);

            return (T) constructor.newInstance(appiumDriver);
            //return NextPageClassName.getDeclaredConstructor(Class.class).newInstance(driver);//newIntanceof ;
        } catch (Exception e) {
            System.out.println("goToNextPage() Err: " + e.getMessage());
            return null;
        }
    }

    public <T> T goToPreviousPage(Class<T> NextPageClassName, AppiumDriver appiumDriver) {
        try {
            backBtn.click();
            String className = NextPageClassName.getName();
            Class<?> cls = Class.forName(className);

            Constructor constructor = cls.getDeclaredConstructor(AppiumDriver.class);//NextPageClassName.getClass());
            //return cls.getConstructor(NextPageClassName.getClass()).newInstance(driver);

            return (T) constructor.newInstance(appiumDriver);
            //return NextPageClassName.getDeclaredConstructor(Class.class).newInstance(driver);//newIntanceof ;
        } catch (Exception e) {
            System.out.println("goToPreviousPage() Err: " + e.getMessage());
            return null;
        }
    }

    public void scrollToElement(AppiumDriver appiumDriver, WebElement startElement, WebElement endElement) {
        System.out.println("scroll to element...");
        int x = startElement.getLocation().getX();
        System.out.println("startElement.getLocation().getX(): " + startElement.getLocation().getX());
        int y = startElement.getLocation().getY();
        System.out.println("startElement.getLocation().getY(): " + startElement.getLocation().getY());

        int x1 = endElement.getLocation().getX();
        System.out.println("endElement.getLocation().getX(): " + startElement.getLocation().getX());

        int y1 = endElement.getLocation().getY();
        System.out.println("endElement.getLocation().getY(): " + startElement.getLocation().getY());

        //Swipe from Right to Left.
        TouchAction touchAction = new TouchAction(appiumDriver);
        //touchAction.press(startX, startY).moveTo(endX, endY).release().perform();
        //touchAction.press(415, 277).moveTo(-87, 2).release().perform();

        touchAction.press(startElement).moveTo(endElement).release().perform();

    }

}
