/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.templatemailer.panels;
import com.templatemailer.dataobjects.*;
/**
 *
 * @author Moykh
 */
public class PreviewPanel extends javax.swing.JPanel {

    /**
     * Creates new form PreviewPanel
     */
    
    MailerData mailerData;
    String subject,content;
    int iterator;
    StringBuffer contentBuffered;
    public PreviewPanel() {
        initComponents();
    }
    
    public PreviewPanel(MailerData data, String Subject, String Content){
        mailerData=data;
        subject=Subject;
        content=Content;
        iterator=1;
        initComponents();
        To.setText(mailerData.listOfAddresses[0]);
        this.Subject.setText(Subject);
        
        contentBuffered=new StringBuffer(Content);
        for(int i=0;i<mailerData.numberOfAttributes;i++){
            int index=contentBuffered.indexOf("$"+mailerData.attributes[i].toUpperCase()+"$");
            if(index!=-1) contentBuffered.replace(index, (index+mailerData.attributes[i].length()+2), mailerData.attributeMatrix[1][i]);
        }
        iterator=1;
        this.Content.setText(contentBuffered.toString());
        
        revalidate();
        repaint();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        To = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        Subject = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        Content = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();

        jScrollPane1.setViewportView(To);

        jScrollPane2.setViewportView(Subject);

        jScrollPane3.setViewportView(Content);

        jLabel1.setText("To:");

        jLabel2.setText("Subject:");

        jLabel3.setText("Content:");

        previousButton.setText("Previous");
        previousButton.setEnabled(false);
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(previousButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(previousButton, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(nextButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        iterator++;
        if(iterator==mailerData.numberOfAddresses-1){
            nextButton.setEnabled(false);
        }else nextButton.setEnabled(true);
        
        if(iterator!=1) previousButton.setEnabled(true);
        
        To.setText(mailerData.listOfAddresses[iterator-1]);
         contentBuffered=new StringBuffer(content);
        for(int i=0;i<mailerData.numberOfAttributes;i++){
            int index=contentBuffered.indexOf("$"+mailerData.attributes[i].toUpperCase()+"$");
            if(index!=-1) contentBuffered.replace(index, (index+mailerData.attributes[i].length()+2), mailerData.attributeMatrix[iterator][i]);
        }
        
        this.Content.setText(contentBuffered.toString());
        
        revalidate();
        repaint();
        
        
    }//GEN-LAST:event_nextButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        iterator--;
        if(iterator==1) previousButton.setEnabled(false);
        else previousButton.setEnabled(true);
        
        if(iterator!=mailerData.numberOfAddresses-1) nextButton.setEnabled(true);
        
        To.setText(mailerData.listOfAddresses[iterator-1]);
         contentBuffered=new StringBuffer(content);
        for(int i=0;i<mailerData.numberOfAttributes;i++){
            int index=contentBuffered.indexOf("$"+mailerData.attributes[i].toUpperCase()+"$");
            if(index!=-1) contentBuffered.replace(index, (index+mailerData.attributes[i].length()+2), mailerData.attributeMatrix[iterator][i]);
        }
        
        this.Content.setText(contentBuffered.toString());
        
        revalidate();
        repaint();
    }//GEN-LAST:event_previousButtonActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane Content;
    private javax.swing.JTextPane Subject;
    private javax.swing.JTextPane To;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    // End of variables declaration//GEN-END:variables
}
