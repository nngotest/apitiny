package api.model;

public class EventResponse {
    private String message;

    private String status;

    private Event event;

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

    public Event getEvent ()
    {
        return event;
    }

    public void setEvent (Event event)
    {
        this.event = event;
    }

    @Override
    public String toString()
    {
        return "EventDetails [message = "+message+", status = "+status+", event = "+event+"]";
    }
}
