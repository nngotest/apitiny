package pages.device;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;

import java.util.List;
import java.util.Random;

public class PageChooseTicket extends PageBase {
    private AppiumDriver appiumDriver;

    @FindBy(xpath = "//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout")
    List<WebElement> tickets;

    @FindBy(id = "total")
    WebElement total;

    @FindBy(id = "rltNext")
    WebElement nextBtn;

    public PageChooseTicket(AppiumDriver appiumDriver) {

        this.appiumDriver = appiumDriver;
        PageFactory.initElements(appiumDriver, this);
        wait = new WebDriverWait(appiumDriver, 10);
        //super.waitForVisibilityOfElement(appiumDriver, this.SignInButon);
        this.tickets = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout")));
    }

    public void getTicketInfo(int ticketIndex) {

        System.out.println("tickets.size: " + tickets.size());

        System.out.println(tickets.get(ticketIndex).findElement(By.id("tv_type")).getText());
        System.out.println(tickets.get(ticketIndex).findElement(By.id("tv_price_type")).getText());
        System.out.println(tickets.get(ticketIndex).findElement(By.id("tv_type_info")).getText());
        System.out.println(tickets.get(ticketIndex).findElements(By.className("android.widget.Button")).size());

        List<WebElement> btns = tickets.get(ticketIndex).findElements(By.className("android.widget.Button"));
        for (int i = 0; i < btns.size(); i++) {
            System.out.println(btns.get(i).getText());
        }

        System.out.println(tickets.get(ticketIndex).findElement(By.id("num_type")).getText());

    }

    public Double getTicketPrice(int ticketIndex) {
        tickets = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//android.support.v7.widget.RecyclerView/android.widget.RelativeLayout")));
        String price = tickets.get(ticketIndex).findElement(By.id("tv_price_type")).getText().replace(",", "");
        price = price.substring(0, price.indexOf(" VND"));
        //System.out.println("getTicketPrice :" + price);

        return Double.parseDouble(price.trim());
    }

    public String getTicketType(int ticketIndex) {
        return tickets.get(ticketIndex).findElement(By.id("tv_type")).getText();
    }

    public String getTicketDescription(int ticketIndex) {
        return tickets.get(ticketIndex).findElement(By.id("tv_type_info")).getText();
    }

    public int getAllTickectType() {
        return tickets.size();
    }

    public void pressPlusOrMinusBtn(int ticketIndex, String button, int pressTime) {
        List<WebElement> buttons = this.tickets.get(ticketIndex).findElements(By.className("android.widget.Button"));
        String btnText;
        WebElement selectedBtn = null;

        for (int i = 0; i < buttons.size(); i++) {
            btnText = buttons.get(i).getText();
            if (button == "+") {
                selectedBtn = buttons.get(2);
            } else if (button == "-") {
                selectedBtn = buttons.get(0);
            }
        }

        if (selectedBtn != null) {
            for (int i = 0; i < pressTime; i++) {
                selectedBtn.click();
            }
        }

        if (TestBase.selectedTicketsIndex.contains(ticketIndex) == false)
            TestBase.selectedTicketsIndex.add(ticketIndex);
    }

    public int getNumberOfBoughtTicket(int ticketIndex) {
        List<WebElement> buttons = this.tickets.get(ticketIndex).findElements(By.className("android.widget.Button"));
        return Integer.parseInt(buttons.get(1).getText().trim());
    }

    public boolean isTicketSoldOut(int ticketIndex) {
        try {
            WebElement soldOut = this.tickets.get(ticketIndex).findElement(By.id("tv_type_sold_out"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Double getTotalAmountOnFooter() {
        String totalAmountOnFooter = total.getText().replace(",", "");
        System.out.println("totalAmountOnFooter: " + totalAmountOnFooter);
        return Double.parseDouble(totalAmountOnFooter.substring(0, totalAmountOnFooter.indexOf(" VND")));
    }

    public void goNext() {
        this.nextBtn.click();
    }

    public Double calculateTicketFee() {
        double ticketFee = 0.0;
        double price = 0.0;
        int numberOfTicket = 0;

        for (int i = 0; i < TestBase.selectedTicketsIndex.size(); i++) {
            //scrollToElement(appiumDriver, tickets.get(TestBase.selectedTicketsIndex.get(2)), tickets.get(TestBase.selectedTicketsIndex.get(0)));

            price = getTicketPrice(TestBase.selectedTicketsIndex.get(i));
            numberOfTicket = getNumberOfBoughtTicket(TestBase.selectedTicketsIndex.get(i));

            System.out.println("Buy ticket: " + getTicketType(TestBase.selectedTicketsIndex.get(i)) + " " + price + " * " + numberOfTicket);
            ticketFee += price * numberOfTicket;
        }
        return ticketFee;
    }

    public void selectRandomTickets() {

        int numberOfTicketType, rndTicketTypes, rndTicketIndex, rndNumberTicketToBuy;

        Random rd = new Random();
        numberOfTicketType = getAllTickectType();
        rndTicketTypes = rd.nextInt(numberOfTicketType) + 1;

        for (int i = 0; i < rndTicketTypes; i++) {

            rndNumberTicketToBuy = rd.nextInt(10) + 1;
            rndTicketIndex = rd.nextInt(numberOfTicketType);
            while(isTicketSoldOut(rndTicketIndex)) {
                rndTicketIndex = rd.nextInt(numberOfTicketType);
                System.out.println("Ticket index [" + rndTicketIndex + "] was sold out, select another ticket.");
            }

            pressPlusOrMinusBtn(rndTicketIndex, "+", rndNumberTicketToBuy);
        }
    }

    public boolean verifyTotal() {
        if (calculateTicketFee().equals(getTotalAmountOnFooter()))
            return true;
        return false;
    }

    public boolean verifyTotal(Double expectedAmount, Double actualAmount) {
        if (expectedAmount.equals(actualAmount))
            return true;
        return false;
    }
}
