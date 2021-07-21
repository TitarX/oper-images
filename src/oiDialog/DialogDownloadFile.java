/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DialogDownloadFile.java
 *
 * Created on 16.05.2010, 23:34:27
 */
package oiDialog;

import oiDialog.*;
import java.awt.*;
import javax.swing.*;
import operImages.FiltersForm;

/**
 *
 * @author TitarX
 */
public class DialogDownloadFile extends JDialog
{

    FiltersForm ff;

    /** Creates new form DialogDownloadFile */
    public DialogDownloadFile(Frame parent,String head,boolean modal,FiltersForm ff)
    {
        super(parent,head,modal);
        initComponents();
        lable1.setText("Введите URL изображения");
        lable2.setText("Например:   http://www.website.ru/images/image.jpg");
        this.ff=ff;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonClose = new javax.swing.JButton();
        lable2 = new javax.swing.JLabel();
        buttonDownload = new javax.swing.JButton();
        textFieldURL = new javax.swing.JTextField();
        lable1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        buttonClose.setText("Отмена");
        buttonClose.setMaximumSize(new java.awt.Dimension(169, 23));
        buttonClose.setMinimumSize(new java.awt.Dimension(169, 23));
        buttonClose.setPreferredSize(new java.awt.Dimension(169, 23));
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        lable2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lable2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        buttonDownload.setText("Загрузить");
        buttonDownload.setMaximumSize(new java.awt.Dimension(169, 23));
        buttonDownload.setMinimumSize(new java.awt.Dimension(169, 23));
        buttonDownload.setPreferredSize(new java.awt.Dimension(169, 23));
        buttonDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDownloadActionPerformed(evt);
            }
        });

        textFieldURL.setText("http://");

        lable1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lable1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lable1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(textFieldURL, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lable2, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lable1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lable2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(textFieldURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDownload, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonCloseActionPerformed
    {//GEN-HEADEREND:event_buttonCloseActionPerformed
        if(ff.threadDownload!=null&&ff.threadDownload.isAlive())
        {
            ff.setEnabledMenu();
            ff.threadDownload.stop();
            ff.threadDownload=null;
        }
        if(ff.img!=null)
        {
            ff.isImageLoaded=true;
            ff.showView();
        }else
        {
            ff.clearingPanel();
        }
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonDownloadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonDownloadActionPerformed
    {//GEN-HEADEREND:event_buttonDownloadActionPerformed
        ff.fileURL=textFieldURL.getText();
        dispose();
    }//GEN-LAST:event_buttonDownloadActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonDownload;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lable1;
    private javax.swing.JLabel lable2;
    private javax.swing.JTextField textFieldURL;
    // End of variables declaration//GEN-END:variables
}