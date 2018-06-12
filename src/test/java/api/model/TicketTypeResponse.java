package api.model;

public class TicketTypeResponse {
    private TicketType ticketType;

    public TicketType getTicketType ()
    {
        return ticketType;
    }

    public void setTicketType (TicketType ticketType)
    {
        this.ticketType = ticketType;
    }

    @Override
    public String toString()
    {
        return "TicketTypeResponse [ticketType = "+ticketType+"]";
    }
}
