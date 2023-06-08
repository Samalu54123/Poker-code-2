/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;

import java.util.Arrays;

/**
 *
 * @author sfcua
 */
public class CheckWinner {
    public CheckWinner() 
    {
         
    }
    
    public boolean hasRoyalFlush(Card card1, Card card2, TableCards tablecards)
    {       
        int count=0;
        int[] cardsInOrder = new int[7];
        //creates an array to hold the 7 cards being compared
        cardsInOrder[0] = getCardValue(card1);
        cardsInOrder[1] = getCardValue(card2);
        //sets the player cards into the array
        Card currentCard=tablecards.head; 
        Arrays.sort(cardsInOrder);
        //sorts the cards
        for (int i = 2; i < cardsInOrder.length - 1; i++) {
            cardsInOrder[i] = getCardValue(currentCard);
            currentCard = currentCard.next;
            //sets the remaining table cards in
        }
        if(hasFlush(card1, card2, tablecards))
        //calls the flush function to see if theres a flush
        {       
            for (int i = 1; i < cardsInOrder.length; i++) 
            {
                if(cardsInOrder[i]>=10)
                //makes sure that the straight is a royal
                {
                    if (cardsInOrder[i] == cardsInOrder[i - 1] + 1)
                    {
                        count++;
                        //if the next card is consecutive then a counter increases by one if 5 is reached straight has been found
                    } else if (cardsInOrder[i] == cardsInOrder[i - 1] + 1) 
                    {
                        //resets the counter if the streak breaks
                        count = 0;
                    }
                }
            }
            if(count == 5)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasStraightFlush(Card card1, Card card2, TableCards tablecards)
    {
        if(hasStraight(card1, card2, tablecards))
        //checks if its a straight
        {
            if(hasFlush(card1, card2, tablecards))
            //checks if its also a flush
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasFourOfAKind(Card card1, Card card2, TableCards tablecards)
    {
        Card currentCard = tablecards.head;
        int counter=0;
        //creates a counter to keep track of how many matches there are
        while(currentCard.next!=null)
        {
            if(card1.card.charAt(0)==currentCard.card.charAt(0)||
               card2.card.charAt(0)==currentCard.card.charAt(0))
             //if the first card or the second card matches with any of tha table cards
            {
                counter++;
                //add to counter
            }
            currentCard=currentCard.next;
        }
        if(counter==4)
        //if the counter is 4 then it returns true
        {
            return true;
        }
        return false;
    }
    

    public boolean hasFullHouse(Card card1, Card card2, TableCards tablecards)
    {
        if(hasPair(card1, card2, tablecards))
        //checks for a pair
        {
            if(hasThreeOfAKind(card1 , card2, tablecards))
            //then checks for a three of a kind
            {
                return true;
            }
        }        
        return false;
    }

    public boolean hasFlush(Card card1, Card card2, TableCards tablecards)
    {
        int count=0;
        char[] cardsInOrder = new char[7];
        //creates a new array to hold the 7 cards being compared
        cardsInOrder[0] = card1.card.charAt(1);
        cardsInOrder[1] = card2.card.charAt(1);
        //sets the suit value of the card into the array
        Card currentCard=tablecards.head;        
        for(int i=2;i<cardsInOrder.length-1;i++)
        {
            cardsInOrder[i]=currentCard.card.charAt(1);
            currentCard=currentCard.next;
            //sets the rest of the cards
        }
        Arrays.sort(cardsInOrder);
        //sorts the array
        for(int i = 1; i<cardsInOrder.length; i++)
        {
            if(cardsInOrder[i]==cardsInOrder[i-1])
            {
                count++;
                //count goes up if the suits match up
            }
        }
        if(count==5)
        //if count is 5 meaning 5 of the same suit thers a flush
        {
            return true;
        }        
        return false;
    }    
    
    public boolean hasStraight(Card card1, Card card2, TableCards tablecards)
    {
        int count=0;
        int[] cardsInOrder = new int[7];
        //create an integer array that holds the 7 cards that will be compared
        cardsInOrder[0] = getCardValue(card1);
        cardsInOrder[1] = getCardValue(card2);
        //set the player cards into the array
        Card currentCard=tablecards.head;        
        for(int i=2;i<cardsInOrder.length-1;i++)
        {
            cardsInOrder[i]=getCardValue(currentCard);
            currentCard=currentCard.next;
            //set the rest of the cards in the array
        }
        Arrays.sort(cardsInOrder);        
        //sort the array
        for(int i = 1; i<cardsInOrder.length; i++)
        {
            if(cardsInOrder[i]==cardsInOrder[i-1]+1)
            //if the card in front of the first card is the consecutive value going up then counter goes up
            {
                count++;
            }
            else if(cardsInOrder[i]==cardsInOrder[i-1]+1)
            //if the streak is broken the counter resets
            {
                count = 0;
            }
        }
        if(count==5)
        //if the counter is at 5 then a straight has been found
        {
            return true;
        }        
        return false;
    }
    
    public boolean hasThreeOfAKind(Card card1, Card card2, TableCards tablecards) 
    {
        int count1 = 0;
        Card currentCard = tablecards.head;

        while(currentCard != null) 
        {
            if(card1.card.charAt(0) == currentCard.card.charAt(0)) 
            //checks if card 1 matches with any of the table cards
            {
                count1++;
            }
            else if(card1.card.charAt(0)==card2.card.charAt(0))
            //checks if card 1 matches with card 2
            {
                count1++;
            }
            else if(card2.card.charAt(0) == currentCard.card.charAt(0)) 
            //checks if card 2 matches with any of the table cards                
            {
                count1++;
            } 
            if(count1>=3)
            {
                return true;
            }
            currentCard = currentCard.next;
        }
        return false;
    }

    public boolean hasTwoPair(Card card1, Card card2, TableCards tablecards)
    {     
        if(!hasPair(card1,card2,tablecards))
        {
            return false;
        }
        //checks for a pair
        Card currentCard = tablecards.head;
        int counter = 0;
        while(currentCard!=null)
        {
            if(counter<2)
            {
                if(card1.card.charAt(0)==currentCard.card.charAt(0)||
                   card2.card.charAt(0)==currentCard.card.charAt(0))
                //if eiter of the 2 cards match any of the table
                {
                    counter++;
                }
            }
            else if(counter==2)
            {
                return true;
            }           
            currentCard = currentCard.next;
        }
        return false;
    }
            
    public boolean hasPair(Card card1, Card card2, TableCards tablecards) 
    {
        Card currentCard = tablecards.head;
        while (currentCard != null) 
        {
            if (card1.card.charAt(0) == currentCard.card.charAt(0)|| 
                card2.card.charAt(0) == currentCard.card.charAt(0)) 
            //if any of the 2 player cards matches with the table if so a pair is found
            {
                return true;
            }
            currentCard = currentCard.next;
        }
        return false;
    }
    
    public boolean hasHighCard(Card card1, Card card2, TableCards tablecards)
    {        
        int[] cardsInOrder = new int[7];
        //creates a new card array to hold the 7 cards being compared
        cardsInOrder[0] = getCardValue(card1);
        cardsInOrder[1] = getCardValue(card2);
        //sets in the players cards
        Card currentCard=tablecards.head;        
        for(int i=2;i<cardsInOrder.length-1;i++)
        //places the rest of the cards into the array
        {
            cardsInOrder[i]=getCardValue(currentCard);
            currentCard=currentCard.next;
        }
        Arrays.sort(cardsInOrder);
        //sorts it
        //the code then checks if either of the players cards are the highest in the array
        if(getCardValue(card1)==cardsInOrder[6])
        {
            return true;
        }else if((getCardValue(card2)==cardsInOrder[6]))
        {
            return true;
        }
        return false;        
    }
    
    //the methid assigns a numerical value to the cards by taking thwe string value and returning their number value
    //the royal cards are represented as 11, 12, 13, 14
    public int getCardValue(Card card)
    {
        String value = String.valueOf(card.card.charAt(0));
        if(value.compareTo("J")==0)
        {
            return 11;
        }
        else if(value.compareTo("Q")==0)
        {
            return 12;
        }
        else if(value.compareTo("K")==0)
        {
            return 13;
        }
        else if(value.compareTo("A")==0)
        {
            return 14;
        }
        else if(value.compareTo("A")==0)
        {
            return 14;
        }
        else if(value.compareTo("10")==0)
        {
            return 10;
        }
        else if(value.compareTo("9")==0)
        {
             return 9;   
        }
        else if(value.compareTo("8")==0)
        {
            return 8;
        }
        else if(value.compareTo("7")==0)
        {
            return 7;
        }
        else if(value.compareTo("6")==0)
        {
            return 6;
        }
        else if(value.compareTo("5")==0)
        {
            return 5;
        }
        else if(value.compareTo("4")==0)
        {
            return 4;
        }
        else if(value.compareTo("3")==0)
        {
            return 3;
        }
        else if(value.compareTo("2")==0)
        {
            return 2;
        }
        return 0;
    }

    
    //checks through all of the methods in order of strongest hand to using the player cards then the opponents cards
    //returning true if player won returning false if the opponent won
    public boolean chekWin(Card pCard1, Card pCard2, Card oCard1, Card oCard2, TableCards tablecards)
    {
        if(hasRoyalFlush(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with Royal Flush");
            return true;
        }
        else if(hasRoyalFlush(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins with Royal Flush");
            return false;
        }
        else if(hasStraightFlush(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with Straight Flush");
            return true;
        }
        else if(hasStraightFlush(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins with Straight Flush");
            return false;
        }
        else if(hasFourOfAKind(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with Four of a kind");
            return true;
        }
        else if(hasFourOfAKind(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins with Four of a kind");
            return false;
        }
        else if(hasFullHouse(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with a Full House");
            return true;
        }
        else if(hasFullHouse(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins wiht a Full House");
            return false;
        }
        else if(hasFlush(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with a Flush");
            return true;
        }
        else if(hasFlush(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponents wins with a Flush");
            return false;
        }
        else if(hasStraight(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with a Straight");
            return true;
        }
        else if(hasStraight(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins with a Straight");
            return false;
        }
        else if(hasThreeOfAKind(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with a Three of a kind");
            return true;
        }
        else if(hasThreeOfAKind(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins with a Three of a kind");
            return false;
        }
        else if(hasTwoPair(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with a Two Pair");
            return true;
        }
        else if(hasTwoPair(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins with a Two Pair");
            return false;
        }
        else if(hasPair(pCard1, pCard2, tablecards))
        {
            System.out.println("player wins with a Pair");
            return true;
        }
        else if(hasPair(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins with a Pair");
            return false;
        }
        else if(hasHighCard(pCard1, pCard2, tablecards))
        {
            System.out.println("Player wins with a High Card");
            return true;
        }
        else if(hasHighCard(oCard1, oCard2, tablecards))
        {
            System.out.println("Opponent wins with a High Card");
            return false;
        }
        return false;
    }
}
