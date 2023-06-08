/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author sfcua
 */
public class Deck 
{
    public Card head;
    public int size;
    public int cardNum = 0;

    Deck(int amount) 
    {
        this.generateDeck(amount);
        //whenever a new deck object is made generate deck is called
    }
    
    //the add method that adds the cards to a deck
    public void add(String card) 
    {
        Card newCard = new Card(card);
        if (head == null) 
        {
            head = newCard;
        } else 
        {
            Card currentCard = head;
            while (currentCard.next != null) 
            {
                currentCard = currentCard.next;
            }
            currentCard.next = newCard;
        }
        size++;
    }

    //method that creates a new deck by reading in the cards from the text file
    public void generateDeck(int amount) 
    {  
        //repeats depending on the amount of decks the player wants
        for (int i = 0; i < amount; i++) {
            try 
            {
                BufferedReader inStream = new BufferedReader(new FileReader("Cards.txt"));
                //reads in the text file containing all the cards
                String read = null;
                //adds all the lines in the text file
                while ((read = inStream.readLine()) != null)
                {
                    add(read);
                }
                inStream.close();
            } catch (FileNotFoundException e) 
            {

            } catch (IOException e) 
            {

            }
        }
    }
    
    //shuffles the deck
    public void shuffle(Deck deck)
    {
        Random rand = new Random();
        for(int i = deck.size-1; i>0 ; i--)
        {
            //creates a random integer and swaps the card in the begining with the random card
            int r = rand.nextInt(i+1);
            Card card1 = getCardAtIndex(i);
            Card card2 = getCardAtIndex(r);          
            swap(card1,card2);
        }        
    }
    
    //returns the card at the specified index
    public Card getCardAtIndex(int index)
    {
        Card currentCard = head;
        //iterates through the linked list till the desired index has been reached
        for(int i = 0 ; i < index ; i++)
        {
            currentCard = currentCard.next;
        }
        return currentCard;
    }
    
    //swaps the 2 cards around
    public void swap(Card card1, Card card2)
    {
        String i = card1.card;
        card1.card=card2.card;
        card2.card=i;
    }

    //tostring
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Card currentCard = head;
        int counter = 0;
        int comma = 0;
        while (currentCard.next != null) 
        {
            sb.append(currentCard.card);
            sb.append(", ");
            currentCard = currentCard.next;
            counter++;
            if (counter == 13) 
            {
                sb.append("\n");
                counter = 0;
            }
        }
        sb.deleteCharAt(sb.length()-3);
        return sb.toString();
    }
}
