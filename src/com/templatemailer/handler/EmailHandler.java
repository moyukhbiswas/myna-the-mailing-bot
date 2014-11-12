/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.templatemailer.handler;

import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;
import java.io.*;
public class EmailHandler {

	private  String password;
	private  String from;

	private  String to;
 
	private  String body;
	
	private  String sal;
	private  String content;
	private  String mainContent;

	//The link for the document review portal
	private static String link="https://www.gmail.com";
	
	
	
	static Properties properties = new Properties();
	
	static
	{
		properties.put("mail.smtp.host","smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", true);
	    properties.put("mail.smtp.port", "465");
	}
        
        public  Session authenticate(String username, String pass)throws Exception{
            from=username;
            password=pass;
            Session session = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication
					getPasswordAuthentication() {
						return new PasswordAuthentication(from, password);
					}
				});
            
            return session;
        }
        
        public  boolean sendMail(Session session,String sendTo, String subject, String emailContent,boolean isHTML)throws Exception{
            try{
                Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setSubject(subject);
                if(isHTML){
                    message.setContent(emailContent, "text/html; charset=utf-8");
                    
                }else{
                    message.setText(emailContent);
                }
                Transport.send(message);
                return true;
            }catch(Exception e){
                return false;
            }
         }
        
        
	
		
}
		
	
		

