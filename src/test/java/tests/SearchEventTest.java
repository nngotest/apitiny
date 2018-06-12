package tests;

import api.Helper;
import api.model.Event;
import org.testng.annotations.Test;
import pages.device.*;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static utils.TestReport.handleExceptionAndMarkFailResult;

public class SearchEventTest extends TestBase{

    @Test
    public void searchEventName() {
        try {

            // Get event via API
            Helper apiHelper = new Helper();
            List<Event> events = apiHelper.getFutureEvents();
            Random rd = new Random();
            int selectEvenIndex = rd.nextInt(events.size());
            String eventName = events.get(selectEvenIndex).getName();

            int keywordLength = rd.nextInt((eventName.length() - 2) + 1) + 2; // range [2..eventName.length()]
            String keywork = eventName.substring(0, keywordLength).toLowerCase();

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            System.out.println("Search event: \"" + keywork + "\"");
            pageHome.searchEvent(keywork);
            testResult *= pageHome.verifySearchedEvent(keywork);
        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }

    @Test
    public void searchEventAddress() {
        try {

            // Get event via API
            Helper apiHelper = new Helper();
            List<Event> events = apiHelper.getFutureEvents();
            Random rd = new Random();
            int selectEvenIndex = rd.nextInt(events.size());
            String eventName = events.get(selectEvenIndex).getPlace().getAddress();

            int keywordLength = rd.nextInt((eventName.length() - 2) + 1) + 2; // range [2..eventName.length()]
            String keywork = eventName.substring(0, keywordLength).toLowerCase();

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            System.out.println("Search event: \"" + keywork + "\"");
            pageHome.searchEvent(keywork);
            testResult *= pageHome.verifySearchedEvent(keywork);
        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }

    @Test
    public void searchEventNotFound() {
        try {

            String keywork = "No event returned";

            PageHome pageHome = new PageHome(androidDriver);
            pageHome.closeYoutubeVideo();
            pageHome.closeTips(androidDriver);

            System.out.println("Search event: \"" + keywork + "\"");
            pageHome.searchEvent(keywork);
            testResult *= pageHome.verifySearchedEventNotFound(keywork);
        } catch (Exception ex) {
            testResult *= 0;
            handleExceptionAndMarkFailResult(androidDriver, ex);
        } finally {
            assertEquals(testResult, 1);
        }
    }
}
