/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;

import java.util.Scanner;

/**
 *
 * @author sfcua
 */
public class Game {
    public Game()
    {
        
    }
    public void Start(User user, Bank bank)
    {
        Scanner scan = new Scanner(System.in);
        String input;
        int deck = 0;
        System.out.println("Game starting.....");
        System.out.println("How many decks would you like to play with?");
        //when the game starts the user is promped for how many decks they want to play with
        try 
        {
            deck = scan.nextInt();
            Table game = new Table(deck, user, bank);
            //creates a new table object which represents the table the game is played on
            CheckWinner checkWinner = new CheckWinner();
            //creates the check win object that checks for the winner at the end
            System.out.println("Generated " + deck + " deck(s)");
            System.out.println("Shuffling cards");
            game.shuffle(game);
            //shuffles the deck before the cards are delt
            System.out.println("Dealing cards...");
            Card playerCard1 = game.dealCard();
            Card playerCard2 = game.dealCard();
            Card oppCard1 = game.dealCard();
            Card oppCard2 = game.dealCard();
            TableCards tableCards = new TableCards(game.dealCard(), game.dealCard(), game.dealCard(), game.dealCard(), game.dealCard());
            //the cards are dealt in order of player then opponents then the table simulating the order people generally deal cards
            int counter = 0;
            boolean ongoing = true;
            while (ongoing)
            {
                //the counter represents the round of the game going up each time the player bets or checks
                if (counter != 4) 
                {
                    System.out.println(playerCard1.card + ", " + playerCard2.card);
                    System.out.println("What would you like to do? ");
                    System.out.println("A. Check");
                    System.out.println("B. Bet");
                    System.out.println("C. Fold");
                    //game menu that asks for the players next move
                    input = scan.next();
                    if (input.toLowerCase().compareTo("a") == 0)
                    {
                        tableCards.nextRound(oppCard1, oppCard2);
                        counter++;
                        //if the player checks nothing happens but move on to the next round
                    } 
                    else if (input.toLowerCase().compareTo("b") == 0)
                    {
                        System.out.println("Enter bet amount: ");
                        input = scan.next();                        
                        //if bet is chosen the player gets prompted for how much they want to bet the opponent automatically matches
                        System.out.println("Opponent Matches...");
                        int bet = Integer.parseInt(input);                        
                        System.out.println(game.getPot() * 2);
                        game.bet(user, bank, bet*2);
                        bank.withraw(user, bet);
                        //after calling the bet class and succesfully betting the amount of money is withrawn
                        tableCards.nextRound(oppCard1, oppCard2);
                        counter++;
                        //the round moves on afteer the bet
                    } 
                    else if (input.toLowerCase().compareTo("c") == 0)
                    {
                        System.out.println("Opponent wins");
                        //if the player cooses to fold then the opponent automatically wins and ends the game loop
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid input");
                        //if the input is not any from the menu
                    }
                } 
                else 
                {
                    //when the game ends the players cards are shown and the game checks for a winner
                    System.out.println(playerCard1.card + ", " + playerCard2.card);   
                    //if the winner is the player check winner returns true and the bets get deposited to the player
                    if(checkWinner.chekWin(playerCard1, playerCard2, oppCard1, oppCard2, tableCards)==true)
                    {
                        bank.deposit(user, game.getPot());
                    }
                    break;
                }
            }
        }
        catch (Exception e) 
        {
            
        }        
    }
}
