package api.model;

public class TicketInfo
{
    private String yearOfBirth;

    private String phoneNumber;

    private String email;

    private String name;

    public String getYearOfBirth ()
    {
        return yearOfBirth;
    }

    public void setYearOfBirth (String yearOfBirth)
    {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
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
        return "ClassPojo [yearOfBirth = "+yearOfBirth+", phoneNumber = "+phoneNumber+", email = "+email+", name = "+name+"]";
    }
}
