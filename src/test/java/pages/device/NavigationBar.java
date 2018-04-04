package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NavigationBar {

    private AppiumDriver appiumDriver;

    @FindBy(xpath = "//*[@class='android.support.v7.app.ActionBar$Tab']")
    List<WebElement> navigationBar;

    public NavigationBar(AppiumDriver appiumDriver){
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        //this.navigationBar = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='android.support.v7.app.ActionBar$Tab']")));
    }
    public void selectProfileIcon(){
        System.out.println(this.navigationBar.size());
        for (int i = 0; i < this.navigationBar.size(); i++) {
            navigationBar.get(i).click();
            try {
                Thread.sleep(1000);
            }catch (Exception e){}
        }
    }
}
