package api.model;

public class Weather
{
    private String summary;

    private String icon;

    private String temperature;

    public String getSummary ()
    {
        return summary;
    }

    public void setSummary (String summary)
    {
        this.summary = summary;
    }

    public String getIcon ()
    {
        return icon;
    }

    public void setIcon (String icon)
    {
        this.icon = icon;
    }

    public String getTemperature ()
    {
        return temperature;
    }

    public void setTemperature (String temperature)
    {
        this.temperature = temperature;
    }

    @Override
    public String toString()
    {
        return "Weather [summary = "+summary+", icon = "+icon+", temperature = "+temperature+"]";
    }
}
