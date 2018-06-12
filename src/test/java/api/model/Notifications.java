package api.model;

public class Notifications
{
    private String eventID;

    private UserCreated userCreated;

    private String _id;

    private String isNotSeen;

    private String commentID;

    private String eventName;

    private String type;

    private String userCreateNotiID;

    private String replyID;

    private String postTime;

    public String getEventID ()
    {
        return eventID;
    }

    public void setEventID (String eventID)
    {
        this.eventID = eventID;
    }

    public UserCreated getUserCreated ()
    {
        return userCreated;
    }

    public void setUserCreated (UserCreated userCreated)
    {
        this.userCreated = userCreated;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getIsNotSeen ()
    {
        return isNotSeen;
    }

    public void setIsNotSeen (String isNotSeen)
    {
        this.isNotSeen = isNotSeen;
    }

    public String getCommentID ()
    {
        return commentID;
    }

    public void setCommentID (String commentID)
    {
        this.commentID = commentID;
    }

    public String getEventName ()
    {
        return eventName;
    }

    public void setEventName (String eventName)
    {
        this.eventName = eventName;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getUserCreateNotiID ()
    {
        return userCreateNotiID;
    }

    public void setUserCreateNotiID (String userCreateNotiID)
    {
        this.userCreateNotiID = userCreateNotiID;
    }

    public String getReplyID ()
    {
        return replyID;
    }

    public void setReplyID (String replyID)
    {
        this.replyID = replyID;
    }

    public String getPostTime ()
    {
        return postTime;
    }

    public void setPostTime (String postTime)
    {
        this.postTime = postTime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [eventID = "+eventID+", userCreated = "+userCreated+", _id = "+_id+", isNotSeen = "+isNotSeen+", commentID = "+commentID+", eventName = "+eventName+", type = "+type+", userCreateNotiID = "+userCreateNotiID+", replyID = "+replyID+", postTime = "+postTime+"]";
    }
}
