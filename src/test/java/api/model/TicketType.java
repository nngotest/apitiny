package api.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketType {
    private String photoLogo;

    private String waiting;

    private String __v;

    private String capacityTotal;

    private String used;

    private String photoBackground;

    private String id;

    private String eventOwnerLogo;

    private String eventID;

    private String price;

    private String minPerTransaction;

    private String timeEnd;

    private String timeStart;

    private String _id;

    private String maxPerTransaction;

    private Event event;

    private String description;

    private String name;

    private String sold;

    public String getPhotoLogo ()
    {
        return photoLogo;
    }

    public void setPhotoLogo (String photoLogo)
    {
        this.photoLogo = photoLogo;
    }

    public String getWaiting ()
    {
        return waiting;
    }

    public void setWaiting (String waiting)
    {
        this.waiting = waiting;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getCapacityTotal ()
    {
        return capacityTotal;
    }

    public void setCapacityTotal (String capacityTotal)
    {
        this.capacityTotal = capacityTotal;
    }

    public String getUsed ()
    {
        return used;
    }

    public void setUsed (String used)
    {
        this.used = used;
    }

    public String getPhotoBackground ()
    {
        return photoBackground;
    }

    public void setPhotoBackground (String photoBackground)
    {
        this.photoBackground = photoBackground;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEventOwnerLogo ()
    {
        return eventOwnerLogo;
    }

    public void setEventOwnerLogo (String eventOwnerLogo)
    {
        this.eventOwnerLogo = eventOwnerLogo;
    }

    public String getEventID ()
    {
        return eventID;
    }

    public void setEventID (String eventID)
    {
        this.eventID = eventID;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getMinPerTransaction ()
    {
        return minPerTransaction;
    }

    public void setMinPerTransaction (String minPerTransaction)
    {
        this.minPerTransaction = minPerTransaction;
    }

    public String getTimeEnd ()
    {
        return timeEnd;
    }

    public void setTimeEnd (String timeEnd)
    {
        this.timeEnd = timeEnd;
    }

    public String getTimeStart ()
    {
        return timeStart;
    }

    public void setTimeStart (String timeStart)
    {
        this.timeStart = timeStart;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getMaxPerTransaction ()
    {
        return maxPerTransaction;
    }

    public void setMaxPerTransaction (String maxPerTransaction)
    {
        this.maxPerTransaction = maxPerTransaction;
    }

    public Event getEvent ()
    {
        return event;
    }

    public void setEvent (Event event)
    {
        this.event = event;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getSold ()
    {
        return sold;
    }

    public void setSold (String sold)
    {
        this.sold = sold;
    }

    @Override
    public String toString()
    {
        //return "ClassPojo [photoLogo = "+photoLogo+", waiting = "+waiting+", __v = "+__v+", capacityTotal = "+capacityTotal+", used = "+used+", photoBackground = "+photoBackground+", id = "+id+", eventOwnerLogo = "+eventOwnerLogo+", eventID = "+eventID+", price = "+price+", minPerTransaction = "+minPerTransaction+", timeEnd = "+timeEnd+", timeStart = "+timeStart+", _id = "+_id+", maxPerTransaction = "+maxPerTransaction+", event = "+event+", description = "+description+", name = "+name+", sold = "+sold+"]";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        //System.out.println(timeStart);
        Date startDate = new Date(Long.parseLong(timeStart)*1000);
        String startDateFormatted = sdf.format(startDate);

        Date endDate = new Date(Long.parseLong(timeEnd)*1000);
        String endDateFormatted = sdf.format(endDate);
        return String.format("Ticket: %s - %s - Start: %s - End: %s",name, id, startDateFormatted, endDateFormatted);
    }
}
