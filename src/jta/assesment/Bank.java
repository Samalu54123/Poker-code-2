/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sfcua
 */
public class Bank extends User{

    
    public Bank(String userName) {
        super(userName);
    }
    
    public void setBalance(User user,int balance)
    {
        PrintWriter pw = null;
        BufferedReader inStream = null;
        try {            
            inStream = new BufferedReader(new FileReader("User_database.txt"));
            //opens the userdatabase to edit
            String read = null;    
            while((read=inStream.readLine())!=null)
            {
                if(user.getUserName().toLowerCase().compareTo(read)==0)
                //searches through the database till the user has been found
                {
                    pw = new PrintWriter(new FileOutputStream("User_database.txt",true));
                    pw.println(", "+balance); 
                    //sets the balance 
                }
            }
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
        finally
        {
            try 
            {
                inStream.close(); 
                pw.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public int getBalance(User user)
    {
        try {    
            BufferedReader inStream = new BufferedReader(new FileReader("User_database.txt"));
            String read = null;            
            while((read=inStream.readLine())!=null)
            {
                StringTokenizer st = new StringTokenizer(read,", ");
                //separates the user and the balance
                String username = st.nextToken();
                String balance = st.nextToken();
                //sets them to their respective value
                if(user.getUserName().toLowerCase().compareTo(username)==0)
                //if the username has been found
                {                    
                    int bal = Integer.parseInt(balance);        
                    //turns the balance string to an integer and returns it
                    inStream.close();
                    return bal;
                }                
            }
            inStream.close();
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        }
        return 0;
    }
    
    public void deposit(User user,int amount)
    {
        PrintWriter outStream = null;
        BufferedReader inStream = null;      
        try 
        {   
            inStream = new BufferedReader(new FileReader("User_database.txt"));
            //opens the userdatabase
            outStream = new PrintWriter(new FileWriter("User_Database_temp.txt"));      
            //creates a temporary userdatabase that will be the updated version
            String read = null;            
            //while the text file still has text
            while((read=inStream.readLine())!=null)
            {
                StringTokenizer st = new StringTokenizer(read,", ");
                //splits the user and the balance
                String username = st.nextToken();                
                String balanceStr = st.nextToken();
                //sets them to their respective one
                int bal = Integer.parseInt(balanceStr);
                bal=bal+amount;
                //adds the amount intended to the previous balance thats been turned to an integer
                if(user.getUserName().toLowerCase().compareTo(username)==0)
                //if the user is found
                {
                    //the updated balance gets printed
                    outStream.println(username+", "+bal);
                }
                else
                //if the line isnt the user it copies it over
                {
                    outStream.println(read);
                }
            }
            outStream.close();
            inStream.close();           
            File og = new File("User_database.txt");            
            File temp = new File("User_Database_temp.txt"); 
            //creates 2 files named after the original database and the new updated temp file
            Files.move(temp.toPath(), og.toPath(), StandardCopyOption.REPLACE_EXISTING);
            //the new file replaces the orininal file
        }         
        catch (FileNotFoundException ex) 
        {
            
        } catch (IOException ex) {
            Logger.getLogger(Bank.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void withraw(User user, int amount)
    {
        PrintWriter outStream = null;
        BufferedReader inStream = null;        
        try 
        {    
            inStream = new BufferedReader(new FileReader("User_database.txt"));
            outStream = new PrintWriter(new FileWriter("User_Database_temp.txt")); 
            //creates the temporary file with the updated balance
            String read = null;            
            while((read=inStream.readLine())!=null)
            {
                StringTokenizer st = new StringTokenizer(read,", ");
                //splits the user and balance
                String username = st.nextToken();
                String balanceStr = st.nextToken();
                //assigns them to their respective values
                if(user.getUserName().toLowerCase().compareTo(username)==0)
                //if the username has been found
                {
                    int bal = Integer.parseInt(balanceStr);
                    if(bal>=amount)
                    //ensures that the balance is greater than the amount
                    {
                        bal=bal-amount;
                        outStream.println(username+", "+bal);
                        //updates the balance
                    }
                    else
                    {
                        outStream.println(username+", "+bal);
                        System.out.println("Insufficient funds");
                        //it keeps the balance the same if the user doesnt have enough funds
                    }                    
                }else
                {
                    //if the user isnt the desier one it copies it over                    
                    outStream.println(read);
                }
            }
            outStream.close();
            inStream.close();
        }         
        catch (FileNotFoundException ex) 
        {
            
        }
        catch (IOException ex) 
        {
            
        }   
        File og = new File("User_database.txt");
        File temp = new File("User_Database_temp.txt");         
        //creates a file named after the original and the new temporary updated file
        try 
        {
            Files.move(temp.toPath(), og.toPath(), StandardCopyOption.REPLACE_EXISTING);
            //the new file replaces the original file 
        } 
        catch (IOException ex) 
        {
            
        }
    }
}
