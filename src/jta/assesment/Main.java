/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jta.assesment;

import java.util.Scanner;

/**
 *
 * @author sfcua
 */
public class Main {
    
    public static void main(String[] args) 
    {
        User user = new User(null);
        Bank bank = new Bank(null);
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to the Poker game!");
        System.out.print("Please enter your username: ");
        String input = scan.nextLine();
        System.out.println("Searching for user in database.....");
        //starting prompt that asks the user for their name
        if(user.searchUserName(input)!=null)
        {
            user.userName=input;        
            System.out.println("Welcome back "+user.getUserName()+", Bank balance: "+bank.getBalance(user));
            //if the user was already in the database then it loads their data
        }
        else
        {
            System.out.println("User not found in our database, creating new user....");
            user.setUserName(input);
            bank.setBalance(user,0);
            System.out.println("Welcome "+user.getUserName()+", Bank balance: "+bank.getBalance(user));
            //if the user isnt in the database then a new user is made with 0 balance.
        }
       
        boolean ongoing = true;
        while(ongoing)
        {
            System.out.println(user.getUserName()+" What would you like to do?");
            System.out.println("A. Play");
            System.out.println("B. Deposit");
            System.out.println("C. Withraw");
            System.out.println("D. End program");
            //the starting menu the players interact with
            input = scan.next();
            if(input.toLowerCase().compareTo("a")==0)
            {
                Game game = new Game();
                game.Start(user, bank);
                //if play is chosen then the game is started
            }
            else if(input.toLowerCase().compareTo("b")==0)
            {
                System.out.println("Enter amount to depost: ");
                input=scan.next();
                int amount = Integer.parseInt(input);
                bank.deposit(user, amount);
                System.out.println("New Balance: "+bank.getBalance(user)+"\n");
                //if deopsit is selected it calls the deposit method in th ebank class and prints new balance
            }
            else if(input.toLowerCase().compareTo("c")==0)
            {
                System.out.println("Enter amount to withraw:" );
                input = scan.next();
                int amount = Integer.parseInt(input);
                bank.withraw(user, amount);     
                System.out.println("New Balance: "+bank.getBalance(user)+"\n");    
                //if withraw is chosen it calls the withraw method in bank class
            }
            else if(input.toLowerCase().compareTo("d")==0)
            {
                break;
            }
            else
            {
                System.out.println("Unrecognized input, try again.");
                //doesnt accept an input that isnt in the menu
            }
        }
    }    
}
