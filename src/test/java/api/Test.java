package api;

import api.model.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Test {
    public static void main(String[] args) throws IOException {
        Helper helper = new Helper();

        ResourceBundle rb = ResourceBundle.getBundle("config");
        String browser = rb.getString("emailUserName");
        System.out.println(browser);

        String response = helper.sendGetRequest(helper.URL_FUTURE_EVENT);

        Gson gson = new Gson();
        FutureEventsResponse apitinyEvents = gson.fromJson(response, FutureEventsResponse.class);

        System.out.println(apitinyEvents.getEvents().get(0).getName());
        System.out.println(apitinyEvents.getEvents().get(0).getCreated());
        System.out.println(apitinyEvents.getEvents().get(0).getDate());
        System.out.println("========");


        List<Event> futureEvents = helper.getFutureEvents();
        Map<String, String> eNameAndId = new HashMap<String, String>();

        for (int i = 0; i < futureEvents.size(); i++) {
            eNameAndId.put(futureEvents.get(i).getId(), futureEvents.get(i).getName());
        }

        for (Map.Entry<String, String> event : eNameAndId.entrySet()) {
            System.out.println(event.getKey() + "; " + event.getValue());
        }
        System.out.println(apitinyEvents.getEvents().get(0).toString());

        System.out.println("====Ticket type====");
        TicketType ticketType1 = helper.getTicketType("5b1e0215056c5d35c698569a");
        System.out.println(ticketType1.toString());

        TicketType ticketType2 = helper.getTicketType("5b1e0215056c5d35c6985699");
        System.out.println(ticketType2.toString());

        Timestamp timestamp1 = new Timestamp(Long.parseLong(ticketType1.getTimeEnd())*1000);
        Timestamp timestamp2 = new Timestamp(Long.parseLong(ticketType2.getTimeEnd())*1000);
        System.out.println(timestamp2.before(timestamp1));

        System.out.println("====Event====");
        Event event = helper.getEvent("5b1c8b1ec8af2672a5004726");
        System.out.println(event.toString());

        System.out.println("====Free Event====");
        List<Event> freeEven = helper.getFreeEvents();
        System.out.println(freeEven.size());

        System.out.println("====NOT Free Event====");
        List<Event> notFreeEvens = helper.getNotFreeEvents();
        System.out.println(notFreeEvens.size());
        for (Event evt : notFreeEvens) {
            System.out.println("event name: " +evt.getName());
        }

        System.out.println("====Sign in====");
        String json = helper.buildSignInJSON("nngotest2@gmail.com", "b7MjI");
        System.out.println(json);

        response = helper.sendPostRequest(helper.URL_SIGN_IN, json);
        SignInResponse signInResponse = gson.fromJson(response, SignInResponse.class);
        System.out.println(signInResponse.getUser().get_id());
        System.out.println(signInResponse.getUser().getEmail());
        System.out.println(signInResponse.getToken());

        System.out.println("====Promotion base on user====");
        json = helper.buildSignInJSON("apitiny@clearstep.33mail.com", "P@ssword123");
        response = helper.sendPostRequest(helper.URL_SIGN_IN, json);
        signInResponse = gson.fromJson(response, SignInResponse.class);
        String userId = signInResponse.getUser().get_id();

        List<Promotion> promotions = helper.getUserPromotions(userId);
        System.out.println(promotions.size());
        System.out.println(promotions.get(0).getCode());

        System.out.println("====Promotion base on code====");
        Promotion promotion = helper.getPromotionFromCode("AUTO-PERCENT");
        System.out.println("percent: " + promotion.getPercentValue());

        promotion = helper.getPromotionFromCode("AUTO-AMOUNT");
        System.out.println("amount: " + promotion.getMoneyValue());

        System.out.println("====Active account====");
        //System.out.println(helper.activeAccount("account_01052018_221744@autotest.com"));
        helper.activeAccount("account_01052018_221744@autotest.com");

    }
}
