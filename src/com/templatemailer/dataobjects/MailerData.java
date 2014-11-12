/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.templatemailer.dataobjects;
import java.util.*;
/**
 *
 * @author Moykh
 */
public class MailerData {
    
    public String attributes[];
    public String attributeMatrix[][];
    public String listOfAddresses[];
    public int numberOfAttributes;
    public int numberOfAddresses;
    
    public MailerData(int numberOfAttributes, int numberOfAddresses){
        this.numberOfAddresses=numberOfAddresses;
        this.numberOfAttributes=numberOfAttributes;
        attributes=new String[numberOfAttributes];
        listOfAddresses=new String[numberOfAddresses-1];
        attributeMatrix=new String[numberOfAddresses][numberOfAttributes];
        
        
    }
    
}
