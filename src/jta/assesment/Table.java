/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;


/**
 *
 * @author sfcua
 */
public class Table extends Deck{
    
    Bets betPile = new Bets();
    int pot = 0;
    
    public Table(int amount, User player, Bank playerbank) 
    {
        super(amount);
    }
    
    //returns the current betting pot
    public int getPot() 
    {
        pot = betPile.pot;
        return pot;
    }
    
    //deals the card ontop of the list
    public Card dealCard()
    {
        Card currentCard = head;
        head=head.next;
        currentCard.next=null;
        size--;
        return currentCard;
    }
    
    //takes the users bet and bank to ensure the user has sufficient funds
    public int bet(User user, Bank bank, int bet)
    {
        //ensures that the user has sufficient funds before adding the bet
        if(bank.getBalance(user)>=bet)
        {
            betPile.bet(bet);
            return pot;
        }
        return betPile.getPot();
    }
    
    //tostring
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Card currentCard = head;
        int comma = 0;
        while (currentCard.next != null) 
        {
            sb.append(currentCard.card);
            sb.append(", ");
            currentCard = currentCard.next;
        }
        return sb.toString();
    }
}
