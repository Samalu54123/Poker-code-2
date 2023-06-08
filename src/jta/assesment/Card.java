/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jta.assesment;
/**
 *
 * @author sfcua
 */
public class Card{
    
    public Card next;
    public String card;

    public Card(String card)
    {
        //holds the string value of the card
        this.card=card;
        next=null;
    }
}
