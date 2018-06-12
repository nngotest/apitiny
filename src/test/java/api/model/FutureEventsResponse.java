package api.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FutureEventsResponse {
    private String message;

    private String status;

    private List<Event> events;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public List<Event> getEvents ()
    {
        return events;
    }

    public void setEvents (List<Event> events)
    {
        this.events = events;
    }

    @Override
    public String toString()
    {
        return "FutureEvent [message = "+message+", status = "+status+", events = "+events+"]";
    }

    public Map<String, String> getEventsNameAndId(){
        Map<String, String> eNameAndId = new HashMap<String, String>();

        for (int i = 0; i< events.size(); i++){
            eNameAndId.put(events.get(i).getName(), events.get(i).getId());
        }

        return eNameAndId;
    }
}
