package api;

import api.model.*;
import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Helper {
    OkHttpClient client = new OkHttpClient();
    public final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    //public final String URL_BASE = "http://test.apitiny.com/api";
    public final String URL_BASE = "http://52.221.250.23/api";
    public final String URL_FUTURE_EVENT = URL_BASE + "/event/future/";
    public final String URL_EVENT = URL_BASE + "/event/";
    public final String URL_SIGN_IN = URL_BASE + "/auth/signIn/";
    public final String URL_TICKET_TYPE = URL_BASE + "/ticketType/";
    public final String URL_USER_PROMOTION = URL_BASE + "/promotion/user/{userid}";
    public final String URL_CODE_PROMOTION = URL_BASE + "/promotion/{code}";

    public final String URL_ACTIVE_ACCOUNT = "http://52.221.250.23/admin/api" + "/active-user";

    String sendGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    String sendPostRequest(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    public List<Event> getFutureEvents() throws IOException {
        String response = sendGetRequest(URL_FUTURE_EVENT);
        Gson gson = new Gson();
        FutureEventsResponse futureEventsResponse = gson.fromJson(response, FutureEventsResponse.class);

        List<Event> approvedEvents = new ArrayList<Event>();

        for (Event event : futureEventsResponse.getEvents()) {
            if (event.getIsApproved() == "true") {
                approvedEvents.add(event);
            }
        }
        return approvedEvents;
    }

    public List<Event> getFreeEvents() throws IOException {
        List<Event> events = getFutureEvents();
        List<Event> freeEvent = new ArrayList<Event>();

        for (Event event : events) {
            if (event.getIsFree() == "true") {
                freeEvent.add(event);
            }
        }
        return freeEvent;
    }


    public List<Event> getNotFreeEvents() throws IOException {
        List<Event> events = getFutureEvents();
        List<Event> notFreeEvent = new ArrayList<Event>();

        for (Event event : events) {
            if (event.getIsFree() == "false") {
                notFreeEvent.add(event);
            }
        }
        return notFreeEvent;
    }

    public Event getEvent(String eventId) throws IOException {
        String response = sendGetRequest(URL_EVENT + eventId);
        Gson gson = new Gson();
        EventResponse eventResponse = gson.fromJson(response, EventResponse.class);
        return eventResponse.getEvent();
    }

    public List<String> getEventsName(List<Event> events) {
        List<String> eventsName = new ArrayList<String>();
        for (Event event : events) {
            eventsName.add(event.getName());
        }
        return eventsName;
    }

    // return true for not free event
    public boolean checkIfNotFreeEvent(String eventName) {
        try {
            List<String> notFreeEventsName = getEventsName(getNotFreeEvents());
            return notFreeEventsName.contains(eventName);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean checkIfNotFreeEvent(Event event) {
        if (event.getIsFree().equals("false")) {
            return true;
        }
        return false;
    }

    public TicketType getTicketType(String ticketId) throws IOException {
        String response = sendGetRequest(URL_TICKET_TYPE + ticketId);
        Gson gson = new Gson();
        TicketTypeResponse ticketTypeResponse = gson.fromJson(response, TicketTypeResponse.class);
        return ticketTypeResponse.getTicketType();
    }

    private SignInResponse getSignInUserInfo(String signInJson) throws IOException {
        String response = sendPostRequest(URL_SIGN_IN, signInJson);
        Gson gson = new Gson();
        return gson.fromJson(response, SignInResponse.class);
    }

    public String getAccessToken(String email, String password) throws IOException {
        String signInJson = buildSignInJSON(email, password);
        SignInResponse signInResponse = getSignInUserInfo(signInJson);
        return signInResponse.getToken();
    }

    public User getUseInfo(String email, String password) throws IOException {
        String signInJson = buildSignInJSON(email, password);
        SignInResponse signInResponse = getSignInUserInfo(signInJson);
        return signInResponse.getUser();
    }

    public String buildSignInJSON(String email, String password) {
        return "{ \"email\" : \"" + email + "\","
                + "\"password\" : \"" + password + "\""
                + "}";
    }

    public List<Event> eventOwner_GetMyEvents(String userId) throws IOException {
        String url = URL_BASE + "/user/" + userId + "/event/created";
        List<Event> events = new ArrayList<Event>();

        String response = sendGetRequest(url);
        Gson gson = new Gson();
        MyEventResponse myEventResponse = gson.fromJson(response, MyEventResponse.class);
        return myEventResponse.getEvents();
    }

    public String activeAccount(String email) throws IOException {
        String json = "{ \"email\" : \"" + email + "\"}";
        System.out.println(json);
        return sendPostRequest(URL_ACTIVE_ACCOUNT, json);
    }

    public List<Promotion> getUserPromotions(String userId) throws IOException {
        String response = sendGetRequest(URL_USER_PROMOTION.replace("{userid}", userId));
        Gson gson = new Gson();
        UserPromotionResponse userPromotionResponse = gson.fromJson(response, UserPromotionResponse.class);

        List<Promotion> promotions = new ArrayList<Promotion>();
        promotions = userPromotionResponse.getPromotions();

        for (Promotion promotion : promotions) {
            if(!promotion.getIsActive().equals("true")){
                promotions.remove(promotion);
            }
        }
        return promotions;
    }

    public Promotion getPromotionFromCode(String promotionCode) throws IOException {
        String response = sendGetRequest(URL_CODE_PROMOTION.replace("{code}", promotionCode));
        Gson gson = new Gson();
        CodePromotionResponse CodePromotionResponse = gson.fromJson(response, CodePromotionResponse.class);

        return CodePromotionResponse.getPromotion();
    }
}
