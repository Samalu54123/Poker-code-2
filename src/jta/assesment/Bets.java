/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;

/**
 *
 * @author sfcua
 */
public class Bets{

    int pot;

    public Bets() 
    {
    }

    public int getPot() {
        //returns the pot
        return pot;
    }
             
    public int bet(int betAmount)
    {
        //adds the bet into the pot then returns it
        pot+=betAmount;
        return pot;
    }
}
