/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sfcua
 */
public class User {
    
    String userName;
    
    public User(String userName)
    {
        this.userName = userName;          
    }

    public String searchUserName(String userName) 
    {
        BufferedReader inStream = null;
        try 
        {
            inStream = new BufferedReader(new FileReader("User_database.txt"));  
            //opens the user database
            String read = null;
            while((read = inStream.readLine())!=null)
            {       
                StringTokenizer st = new StringTokenizer(read,",");
                String username = st.nextToken();
                if(userName.toLowerCase().compareTo(username)==0)
                {                    
                    inStream.close();
                    return userName;
                    //when the user has been found returns the username
                }
            }         
            inStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getUserName() {
        return userName;
        //returns the username
    }
    
    public void setUserName(String userName) {
        this.userName=userName;
        PrintWriter pw = null;
        try 
        {
            pw = new PrintWriter(new FileOutputStream("User_database.txt",true));
            //opens the database
        } 
        catch (FileNotFoundException ex)
        {
            
        }
        finally
        { 
          pw.print(userName.toLowerCase());
          //wries the new user in a new line and closes the printwriter
          pw.close();
        }
    }
}
