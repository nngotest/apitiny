package api.model;

public class User
{
    private String iosToken;

    private String shortLink;

    private String[] transaction;

    private String[] promotions;

    private String isSocialLogging;

    private String isPhoneNumberVerified;

    private String id;

    private TransactionInfo transactionInfo;

    private String[] followers;

    private String[] following;

    private String phoneNumber;

    private String _id;

    private String createdAt;

    private String description;

    private String name;

    private String role;

    private String isShowPhoneNumber;

    private String providerID;

    private Place place;

    private String[] joins;

    private String androidToken;

    private String photoURL;

    private String website;

    private String __v;

    private CompanyPlace companyPlace;

    private String provider;

    private String companyName;

    private String notiBadge;

    private String isShowEmail;

    private String email;

    private Notifications[] notifications;

    private String[] likes;

    private String active;

    private String[] categories;

    private TicketInfo ticketInfo;

    public String getIosToken ()
    {
        return iosToken;
    }

    public void setIosToken (String iosToken)
    {
        this.iosToken = iosToken;
    }

    public String getShortLink ()
    {
        return shortLink;
    }

    public void setShortLink (String shortLink)
    {
        this.shortLink = shortLink;
    }

    public String[] getTransaction ()
    {
        return transaction;
    }

    public void setTransaction (String[] transaction)
    {
        this.transaction = transaction;
    }

    public String[] getPromotions ()
    {
        return promotions;
    }

    public void setPromotions (String[] promotions)
    {
        this.promotions = promotions;
    }

    public String getIsSocialLogging ()
    {
        return isSocialLogging;
    }

    public void setIsSocialLogging (String isSocialLogging)
    {
        this.isSocialLogging = isSocialLogging;
    }

    public String getIsPhoneNumberVerified ()
    {
        return isPhoneNumberVerified;
    }

    public void setIsPhoneNumberVerified (String isPhoneNumberVerified)
    {
        this.isPhoneNumberVerified = isPhoneNumberVerified;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public TransactionInfo getTransactionInfo ()
    {
        return transactionInfo;
    }

    public void setTransactionInfo (TransactionInfo transactionInfo)
    {
        this.transactionInfo = transactionInfo;
    }

    public String[] getFollowers ()
    {
        return followers;
    }

    public void setFollowers (String[] followers)
    {
        this.followers = followers;
    }

    public String[] getFollowing ()
    {
        return following;
    }

    public void setFollowing (String[] following)
    {
        this.following = following;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
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

    public String getRole ()
    {
        return role;
    }

    public void setRole (String role)
    {
        this.role = role;
    }

    public String getIsShowPhoneNumber ()
    {
        return isShowPhoneNumber;
    }

    public void setIsShowPhoneNumber (String isShowPhoneNumber)
    {
        this.isShowPhoneNumber = isShowPhoneNumber;
    }

    public String getProviderID ()
    {
        return providerID;
    }

    public void setProviderID (String providerID)
    {
        this.providerID = providerID;
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

    public String getAndroidToken ()
    {
        return androidToken;
    }

    public void setAndroidToken (String androidToken)
    {
        this.androidToken = androidToken;
    }

    public String getPhotoURL ()
    {
        return photoURL;
    }

    public void setPhotoURL (String photoURL)
    {
        this.photoURL = photoURL;
    }

    public String getWebsite ()
    {
        return website;
    }

    public void setWebsite (String website)
    {
        this.website = website;
    }

    public String get__v ()
    {
        return __v;
    }

    public void set__v (String __v)
    {
        this.__v = __v;
    }

    public CompanyPlace getCompanyPlace ()
    {
        return companyPlace;
    }

    public void setCompanyPlace (CompanyPlace companyPlace)
    {
        this.companyPlace = companyPlace;
    }

    public String getProvider ()
    {
        return provider;
    }

    public void setProvider (String provider)
    {
        this.provider = provider;
    }

    public String getCompanyName ()
    {
        return companyName;
    }

    public void setCompanyName (String companyName)
    {
        this.companyName = companyName;
    }

    public String getNotiBadge ()
    {
        return notiBadge;
    }

    public void setNotiBadge (String notiBadge)
    {
        this.notiBadge = notiBadge;
    }

    public String getIsShowEmail ()
    {
        return isShowEmail;
    }

    public void setIsShowEmail (String isShowEmail)
    {
        this.isShowEmail = isShowEmail;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public Notifications[] getNotifications ()
    {
        return notifications;
    }

    public void setNotifications (Notifications[] notifications)
    {
        this.notifications = notifications;
    }

    public String[] getLikes ()
    {
        return likes;
    }

    public void setLikes (String[] likes)
    {
        this.likes = likes;
    }

    public String getActive ()
    {
        return active;
    }

    public void setActive (String active)
    {
        this.active = active;
    }

    public String[] getCategories ()
    {
        return categories;
    }

    public void setCategories (String[] categories)
    {
        this.categories = categories;
    }

    public TicketInfo getTicketInfo ()
    {
        return ticketInfo;
    }

    public void setTicketInfo (TicketInfo ticketInfo)
    {
        this.ticketInfo = ticketInfo;
    }

    @Override
    public String toString()
    {
        return "User [iosToken = "+iosToken+", shortLink = "+shortLink+", transaction = "+transaction+", promotions = "+promotions+", isSocialLogging = "+isSocialLogging+", isPhoneNumberVerified = "+isPhoneNumberVerified+", id = "+id+", transactionInfo = "+transactionInfo+", followers = "+followers+", following = "+following+", phoneNumber = "+phoneNumber+", _id = "+_id+", createdAt = "+createdAt+", description = "+description+", name = "+name+", role = "+role+", isShowPhoneNumber = "+isShowPhoneNumber+", providerID = "+providerID+", place = "+place+", joins = "+joins+", androidToken = "+androidToken+", photoURL = "+photoURL+", website = "+website+", __v = "+__v+", companyPlace = "+companyPlace+", provider = "+provider+", companyName = "+companyName+", notiBadge = "+notiBadge+", isShowEmail = "+isShowEmail+", email = "+email+", notifications = "+notifications+", likes = "+likes+", active = "+active+", categories = "+categories+", ticketInfo = "+ticketInfo+"]";
    }
}
