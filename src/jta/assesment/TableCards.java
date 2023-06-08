/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;

/**
 *
 * @author sfcua
 */
public class TableCards{
    public Card head; 
    int counter = 0;
    
    //takes in 5 cards that will be used as the table cards
    public TableCards(Card card1, Card card2, Card card3, Card card4, Card card5)
    {
        this.add(card1);
        this.add(card2);
        this.add(card3);
        this.add(card4);
        this.add(card5);
    }
    
    //manages the revealing of cards as the rounds go on
    public void nextRound(Card oppcard1, Card oppcard2)
    {
        counter++;
        //ads counter for the next round
        Card currentCard = head;
        //round one is when the first 3 cards are revealed
        if(counter==1)
        {
            for(int i = 0; i<3 ; i++)
            {
                System.out.print(currentCard.card+", ");
                currentCard=currentCard.next;
            }
            System.out.print("\n");
        }
        //round 2 the fourth card is revealed
        else if(counter==2)
        {
            for(int i = 0; i<4 ; i++)
            {
                System.out.print(currentCard.card+", ");
                currentCard=currentCard.next;
            }
            System.out.print("\n");
        }
        //round 3 the final card is revealed
        else if(counter==3)
        {
            for(int i = 0; i<5 ; i++)
            {
                System.out.print(currentCard.card+", ");
                currentCard=currentCard.next;
            }
            System.out.print("\n");
        }
        //round 4 the opponents careds are shown as well as all 5 cards
        else if(counter==4)
        {
            System.out.println("Opponents cards: ");
            System.out.println(oppcard1.card+", "+oppcard2.card);
            for(int i = 0; i<5 ; i++)
            {
                System.out.print(currentCard.card+", ");
                currentCard=currentCard.next;
            }
            System.out.print("\n");
        }
    }
    
    //adds the cards into the tablecard linked list
    public void add(Card card)
    {
        if(head==null)
        {
            head=card;
        }else
        {
            Card currentCard = head;
            while(currentCard.next!=null)
            {
                currentCard = currentCard.next;
            }
            currentCard.next=card; 
        }      
    }
    
}
