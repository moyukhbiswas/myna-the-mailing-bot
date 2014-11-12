/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.templatemailer.handler;
import com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;
import com.templatemailer.dataobjects.*;
import org.apache.poi.*;
import org.apache.poi.hssf.*;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Moykh
 */
public class ExcelHandler {
    
    public static MailerData readFile(String fileName)throws Exception{
        
       FileInputStream file = new FileInputStream(new File(fileName));
       System.out.println("Read File");
        HSSFWorkbook workbook = new HSSFWorkbook(file);
           System.out.println("Parsed");
	HSSFSheet worksheet = workbook.getSheetAt(0);
        int numberOfEmails=0,numberOfAttributes=0;
        
       for(int i=0;;i++){
           
           HSSFRow row1 = worksheet.getRow(i);
           if(row1==null) break;
           if(row1.getCell(0)==null) break;
           String s=row1.getCell(0).getStringCellValue();
           System.out.println("Reading s:"+s);
        if(s!=null&&s!=""){
            numberOfEmails++;           
        }else{
            break;
        }
        
        }
       
       HSSFRow row1=worksheet.getRow(0);
       for(int i=0;;i++){
            HSSFCell cell=row1.getCell(i);
            if(cell==null) break;
            if(cell.getStringCellValue()==null|cell.getStringCellValue()==""){
                break;
                
            }
            
            numberOfAttributes++;
       }
       MailerData mailerData=new MailerData(numberOfAttributes,numberOfEmails);
       for(int i=0;i<numberOfAttributes;i++){
            HSSFCell cell=row1.getCell(i);
            if(cell==null) break;
            if(cell.getStringCellValue()==null|cell.getStringCellValue()==""){
                break;
                
            }
            mailerData.attributes[i]=cell.getStringCellValue().trim();
            
       }
       
       
       for(int i=0;i<numberOfEmails;i++){
           HSSFRow row=worksheet.getRow(i);
           if(i!=0)mailerData.listOfAddresses[i-1]=row.getCell(0).getStringCellValue();
           for(int j=0;j<numberOfAttributes;j++){
               HSSFCell cell=row.getCell(j);
               mailerData.attributeMatrix[i][j]=cell.getStringCellValue().trim();
                       
               
           }
       }
       
       
       
       file.close();
       return mailerData;
    
    
    }
    public static void main(String args[])throws Exception{
        System.out.println("Init");
        MailerData gottenData=readFile("C:\\FinalOne\\Test.xls");
        for(int i=0;i<gottenData.numberOfAttributes;i++){
           for(int j=0;j<gottenData.numberOfAddresses;j++){
               System.out.print(gottenData.attributeMatrix[j][0]+" ");
           }
           System.out.println();
        }
        for(int i=0;i<gottenData.numberOfAttributes;i++){
            System.out.println(gottenData.attributes[i]+" ");
        }
        
    }
    
}
