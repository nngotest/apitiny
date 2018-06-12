package api.model;

public class UserCreated
{
    private String id;

    private String photoURL;

    private String shortLink;

    private String name;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPhotoURL ()
    {
        return photoURL;
    }

    public void setPhotoURL (String photoURL)
    {
        this.photoURL = photoURL;
    }

    public String getShortLink ()
    {
        return shortLink;
    }

    public void setShortLink (String shortLink)
    {
        this.shortLink = shortLink;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "UserCreated [id = "+id+", photoURL = "+photoURL+", shortLink = "+shortLink+", name = "+name+"]";
    }
}
