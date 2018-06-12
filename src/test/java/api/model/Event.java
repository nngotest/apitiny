package api.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private String detail;

    private String userCreate;

    private String shortLink;

    private String[] ticketTypeID;

    private String[] liveEvent;

    private String date;

    private String id;

    private Time time;

    private String _id;

    private String created;

    private String name;

    private Weather weather;

    private String commission;

    private Place place;

    private String[] joins;

    private String needTicketInfo;

    private String minTicketPrice;

    private String status;

    private String __v;

    private String isHot;

    private String sale;

    private String fee;

    private UserCreated userCreated;

    private String isFree;

    private String[] likes;

    private String[] photoLink;

    private String[] categories;

    private String isApproved;

    private String[] coorganizers;

    public String getDetail ()
    {
        return detail;
    }

    public void setDetail (String detail)
    {
        this.detail = detail;
    }

    public String getUserCreate ()
    {
        return userCreate;
    }

    public void setUserCreate (String userCreate)
    {
        this.userCreate = userCreate;
    }

    public String getShortLink ()
    {
        return shortLink;
    }

    public void setShortLink (String shortLink)
    {
        this.shortLink = shortLink;
    }

    public String[] getTicketTypeID ()
    {
        return ticketTypeID;
    }

    public void setTicketTypeID (String[] ticketTypeID)
    {
        this.ticketTypeID = ticketTypeID;
    }

    public String[] getLiveEvent ()
    {
        return liveEvent;
    }

    public void setLiveEvent (String[] liveEvent)
    {
        this.liveEvent = liveEvent;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Time getTime ()
    {
        return time;
    }

    public void setTime (Time time)
    {
        this.time = time;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Weather getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather weather)
    {
        this.weather = weather;
    }

    public String getCommission ()
    {
        return commission;
    }

    public void setCommission (String commission)
    {
        this.commission = commission;
    }

    public Place getPlace ()
    {
        return place;
    }

    public void setPlace (Place place)
    {
        this.place = place;
    }

    public String[] getJoins ()
    {
        return joins;
    }

    public void setJoins (String[] joins)
    {
        this.joins = joins;
    }

    public String getNeedTicketInfo ()
    {
        return needTicketInfo;
    }

    public void setNeedTicketInfo (String needTicketInfo)
    {
        this.needTicketInfo = needTicketInfo;
    }

    public String getMinTicketPrice ()
    {
        return minTicketPrice;
    }

    public void setMinTicketPrice (String minTicketPrice)
    {
        this.minTicketPrice = minTicketPrice;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public String getIsHot ()
    {
        return isHot;
    }

    public void setIsHot (String isHot)
    {
        this.isHot = isHot;
    }

    public String getSale ()
    {
        return sale;
    }

    public void setSale (String sale)
    {
        this.sale = sale;
    }

    public String getFee ()
    {
        return fee;
    }

    public void setFee (String fee)
    {
        this.fee = fee;
    }

    public UserCreated getUserCreated ()
    {
        return userCreated;
    }

    public void setUserCreated (UserCreated userCreated)
    {
        this.userCreated = userCreated;
    }

    public String getIsFree ()
    {
        return isFree;
    }

    public void setIsFree (String isFree)
    {
        this.isFree = isFree;
    }

    public String[] getLikes ()
    {
        return likes;
    }

    public void setLikes (String[] likes)
    {
        this.likes = likes;
    }

    public String[] getPhotoLink ()
    {
        return photoLink;
    }

    public void setPhotoLink (String[] photoLink)
    {
        this.photoLink = photoLink;
    }

    public String[] getCategories ()
    {
        return categories;
    }

    public void setCategories (String[] categories)
    {
        this.categories = categories;
    }

    public String getIsApproved ()
    {
        return isApproved;
    }

    public void setIsApproved (String isApproved)
    {
        this.isApproved = isApproved;
    }

    public String[] getCoorganizers ()
    {
        return coorganizers;
    }

    public void setCoorganizers (String[] coorganizers)
    {
        this.coorganizers = coorganizers;
    }

    @Override
    public String toString()
    {
        //return "Event [detail = "+detail+", userCreate = "+userCreate+", shortLink = "+shortLink+", ticketTypeID = "+ticketTypeID+", liveEvent = "+liveEvent+", date = "+date+", id = "+id+", time = "+time+", _id = "+_id+", created = "+created+", name = "+name+", weather = "+weather+", commission = "+commission+", place = "+place+", joins = "+joins+", needTicketInfo = "+needTicketInfo+", minTicketPrice = "+minTicketPrice+", status = "+status+", __v = "+__v+", isHot = "+isHot+", sale = "+sale+", fee = "+fee+", userCreated = "+userCreated+", isFree = "+isFree+", likes = "+likes+", photoLink = "+photoLink+", categories = "+categories+", isApproved = "+isApproved+", coorganizers = "+coorganizers+"]";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date startDate = new Date(Long.parseLong(time.getTimeStart())*1000);
        String startDateFormatted = sdf.format(startDate);

        Date endDate = new Date(Long.parseLong(time.getTimeEnd())*1000);
        String endDateFormatted = sdf.format(endDate);
        return String.format("Event: %s - %s - Start: %s - End: %s",name, id, startDateFormatted, endDateFormatted);
    }
}
