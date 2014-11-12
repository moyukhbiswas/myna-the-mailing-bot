/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package templatemailer;
import java.io.*;
import java.util.*;
import com.templatemailer.dataobjects.*;
import com.templatemailer.handler.*;
import com.templatemailer.panels.*;
import javax.mail.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Moykh
 */
public class TemplateMailer {

    /**
     * @param args the command line arguments
     */
    
    static LoginPanel loginPanel;
    static MailListPanel filePanel=new MailListPanel();
    static MailerPanel mailerPanel;
    static MailerData mailerData;
    static Session emailSession;
    static JFrame mainFrame;
    static EmailHandler emailHandler=new EmailHandler();
    static JFrame fileChooser;
    static boolean hasEmailList;
    
    
    public static void main(String[] args) {
    // TODO code application logic here
        
        mainFrame=new JFrame("Mynabot- The Template Mailer");
        mainFrame.setBounds(100,100,1000,650);
       loginPanel=new LoginPanel();
       mainFrame.setContentPane(loginPanel);
       mailerPanel=new MailerPanel();
       mailerPanel.idList.removeAll();
       mailerPanel.idList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "None" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
       mailerPanel.revalidate();
       
       
        loginPanel.LoginButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                        String username=loginPanel.username.getText();
                        String password=loginPanel.password.getText();
                        try{
                            emailSession=emailHandler.authenticate(username, password);
                        }catch(Exception exp){
                            JLabel errorLabel=new JLabel("Error. Cannot login with this username/password.");
                            loginPanel.add(errorLabel);
                            loginPanel.revalidate();
                            loginPanel.repaint();
                            return;
                        }
                        if(emailSession==null){
                            JLabel errorLabel=new JLabel("Error. Cannot login with this username/password.");
                            loginPanel.add(errorLabel);
                            loginPanel.revalidate();
                            loginPanel.repaint();
                            return;
                        }
                        mainFrame.setContentPane(mailerPanel);
                        mailerPanel.revalidate();
                        mailerPanel.repaint();
                        mainFrame.revalidate();
                        mainFrame.repaint();
                }
        });
        
       /* mailerPanel.importExcel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fileChooser=new JFrame("Choose the Excel File");
                fileChooser.setContentPane(filePanel);
                fileChooser.setBounds(100,100,800,600);
                fileChooser.setVisible(true);
            }
            
            
        });*/
        mailerPanel.importExcel.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    int returnVal = filePanel.jFileChooser1.showOpenDialog(filePanel);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        String x=filePanel.jFileChooser1.getSelectedFile().getAbsolutePath();
                       System.out.println("You chose to open this file: " +x);
                       try{
                       mailerData=ExcelHandler.readFile(x);
                       }catch(Exception exp){
                           return;
                       }
                       mailerPanel.idList.setModel(new javax.swing.AbstractListModel() {
                        String[] strings = mailerData.listOfAddresses;
                        public int getSize() { return strings.length; }
                        public Object getElementAt(int i) { return strings[i]; }
                    });
                       mailerPanel.idList.revalidate();
                       mailerPanel.idList.repaint();
                       
                    }
                        
                        
                        
                    }
                    
                    
                }
        
        
        );
        
        mailerPanel.viewDetails.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    String emailID=(String)mailerPanel.idList.getSelectedValue();
                    DetailsPanel detailsPanel=new DetailsPanel();
                    Object[][] attributeMatrix;
                    int indexOfData=0;
                    for(int i=0;i<mailerData.numberOfAddresses;i++){
                        if(mailerData.attributeMatrix[i][0].equals(emailID))
                        {
                            indexOfData=i;
                            break;
                        }
                    }
                    attributeMatrix=new Object[mailerData.numberOfAttributes][2];
                    
                            for(int i=0;i<mailerData.numberOfAttributes;i++){
                                attributeMatrix[i][0]=mailerData.attributes[i];
                                attributeMatrix[i][1]=mailerData.attributeMatrix[indexOfData][i];
                              
                            }
                    detailsPanel.jTable1.setModel(new javax.swing.table.DefaultTableModel(
                            attributeMatrix,
                            new String [] {
                                "Attribute Name", "Value"
                            }
        ));
                    JFrame detailsFrame=new JFrame("Details of "+emailID);
                    detailsFrame.setBounds(100,100,500,500);
                    detailsFrame.setContentPane(detailsPanel);
                    detailsFrame.setVisible(true);
                    
            }
            
            
        });
        
        
        
       mailerPanel.previewButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               JFrame newFrame=new JFrame("Preview Design");
               PreviewPanel previewPanel=new PreviewPanel(mailerData,mailerPanel.Subject.getText(), mailerPanel.Content.getText());
               newFrame.setBounds(100,100,700,600);
               newFrame.setContentPane(previewPanel);
               newFrame.setVisible(true);
           }
           
           
       });
        
        
        
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        
    }
        
    
    
    }
    