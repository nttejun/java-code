package ticketoffice;

import ticket.Ticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {
    private Long amount;
    private List<Ticket> ticketList = new ArrayList<>();

    public TicketOffice(Long amount, Ticket ticket){
        this.amount = amount;
        this.ticketList.addAll(Arrays.asList(ticket));
    }

    public Ticket getTicket(){
        return ticketList.remove(0);
    }

    public void minusAmount(Long amount){
        this.amount -= amount;
    }

    public void plusAmount(Long amount){
        this.amount += amount;
    }
}
