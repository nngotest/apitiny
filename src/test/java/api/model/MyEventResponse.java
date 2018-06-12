package api.model;

import java.util.List;

public class MyEventResponse {
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
        return "MyEventResponse [message = "+message+", status = "+status+", events = "+events+"]";
    }
}
