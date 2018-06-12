package api.model;

public class Time {
    private String timeStart;

    private String timeEnd;

    public String getTimeStart ()
    {
        return timeStart;
    }

    public void setTimeStart (String timeStart)
    {
        this.timeStart = timeStart;
    }

    public String getTimeEnd ()
    {
        return timeEnd;
    }

    public void setTimeEnd (String timeEnd)
    {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString()
    {
        return "Time [timeStart = "+timeStart+", timeEnd = "+timeEnd+"]";
    }
}
