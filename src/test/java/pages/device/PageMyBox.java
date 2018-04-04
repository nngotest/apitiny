package pages.device;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageMyBox extends PageBase {

    @FindBy(id = "interestedTab")
    WebElement interestedTab;

    @FindBy(id = "myTicketTab")
    WebElement myTicketTab;
}
