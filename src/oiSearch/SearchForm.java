/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchForm.java
 *
 * Created on 30.05.2010, 0:23:00
 */
package oiSearch;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import operImages.FiltersForm;

/**
 *
 * @author TitarX
 */
public class SearchForm extends JFrame
{

    private JFileChooser jfchSearch;
    private SearchFile sf;
    private ArrayList al;
    private String fileName;
    public FiltersForm ff;

    public SearchForm(String str,FiltersForm ff)
    {
        super(str);
        initComponents();
        Localization();
        buttonOpenFile.setEnabled(false);
        buttonSearch.setEnabled(false);
        this.ff=ff;
    }

    public void Localization()
    {
        jfchSearch=new JFileChooser();
        UIManager.put("FileChooser.openDialogTitleText","Указать путь поиска");
        UIManager.put("FileChooser.saveDialogTitleText","Сохранить изображение как (JPG JPEG GIF PNG)");
        UIManager.put("FileChooser.lookInLabelText","Папка:");
        UIManager.put("FileChooser.saveInLabelText","Сохранить в:");
        UIManager.put("FileChooser.openButtonText","Открыть");
        UIManager.put("FileChooser.openButtonToolTipText","Открыть");
        UIManager.put("FileChooser.saveButtonText","Сохранить");
        UIManager.put("FileChooser.saveButtonToolTipText","Сохранить текущее изображение в файл");
        UIManager.put("FileChooser.cancelButtonText","Отмена");
        UIManager.put("FileChooser.filesOfTypeLabelText","");
        UIManager.put("FileChooser.fileNameLabelText","Путь:");
        UIManager.put("FileChooser.acceptAllFileFilterText","Все папки");
        UIManager.put("FileChooser.upFolderToolTipText","Вверх");
        UIManager.put("FileChooser.homeFolderToolTipText","Рабочий стол");
        UIManager.put("FileChooser.newFolderToolTipText","Создать новую папку");
        UIManager.put("FileChooser.detailsViewButtonToolTipText","Таблица");
        UIManager.put("FileChooser.listViewButtonToolTipText","Список");
        UIManager.put("FileChooser.cancelButtonToolTipText","Закрыть диалог");
        jfchSearch.updateUI();
        jfchSearch.setFileSelectionMode(jfchSearch.DIRECTORIES_ONLY);
        jfchSearch.setAcceptAllFileFilterUsed(false);

        UIManager.put("OptionPane.yesButtonText","Да");
        UIManager.put("OptionPane.noButtonText","Нет");
        UIManager.put("OptionPane.cancelButtonText","Отмена");
        UIManager.put("OptionPane.okButtonText","Закрыть");
    }

    public void setPathSearch()
    {
        if(jfchSearch.showOpenDialog(this)==jfchSearch.APPROVE_OPTION)
        {
            textFieldSearchIn.setText(jfchSearch.getSelectedFile().getPath());

            if((!textFieldFileName.getText().equals(""))&&(!textFieldSearchIn.getText().equals("")))
            {
                buttonSearch.setEnabled(true);
            }else
            {
                buttonSearch.setEnabled(false);
            }
        }
    }

    public void newSearch()
    {
        buttonOpenFile.setEnabled(false);

//        al=new ArrayList(1);
//        al.add("Идёт поиск ...");
//        listResult.setListData(al.toArray());

        setTitle("Идёт поиск ...");

        sf=new SearchFile();
        try
        {
            al=(ArrayList)sf.find(textFieldSearchIn.getText(),textFieldFileName.getText());
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Произошла ошибка",JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(al.isEmpty())
        {
            al.add("Поиск не дал результатов");
        }

        listResult.setListData(al.toArray());

        setTitle("Поиск файлов");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSearchIn = new javax.swing.JLabel();
        labelFileName = new javax.swing.JLabel();
        textFieldFileName = new javax.swing.JTextField();
        textFieldSearchIn = new javax.swing.JTextField();
        buttonSpecify = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listResult = new javax.swing.JList();
        buttonClose = new javax.swing.JButton();
        buttonOpenFile = new javax.swing.JButton();
        buttonSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelSearchIn.setText("Искать в:");

        labelFileName.setText("Имя файла:");

        textFieldFileName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldFileNameKeyReleased(evt);
            }
        });

        textFieldSearchIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldSearchInKeyReleased(evt);
            }
        });

        buttonSpecify.setText("Указать путь");
        buttonSpecify.setMaximumSize(new java.awt.Dimension(103, 20));
        buttonSpecify.setMinimumSize(new java.awt.Dimension(103, 20));
        buttonSpecify.setPreferredSize(new java.awt.Dimension(103, 20));
        buttonSpecify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSpecifyActionPerformed(evt);
            }
        });

        listResult.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                listResultMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(listResult);

        buttonClose.setText("Закрыть окно поиска");
        buttonClose.setMaximumSize(new java.awt.Dimension(201, 23));
        buttonClose.setMinimumSize(new java.awt.Dimension(201, 23));
        buttonClose.setPreferredSize(new java.awt.Dimension(201, 23));
        buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCloseActionPerformed(evt);
            }
        });

        buttonOpenFile.setText("Открыть выбранный файл");
        buttonOpenFile.setMaximumSize(new java.awt.Dimension(201, 23));
        buttonOpenFile.setMinimumSize(new java.awt.Dimension(201, 23));
        buttonOpenFile.setPreferredSize(new java.awt.Dimension(201, 23));
        buttonOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpenFileActionPerformed(evt);
            }
        });

        buttonSearch.setText("Начать поиск");
        buttonSearch.setMaximumSize(new java.awt.Dimension(201, 23));
        buttonSearch.setMinimumSize(new java.awt.Dimension(201, 23));
        buttonSearch.setPreferredSize(new java.awt.Dimension(201, 23));
        buttonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSearchIn)
                            .addComponent(labelFileName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFieldFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                            .addComponent(textFieldSearchIn, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSpecify, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFileName)
                    .addComponent(textFieldFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSearchIn)
                    .addComponent(textFieldSearchIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSpecify, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSpecifyActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSpecifyActionPerformed
    {//GEN-HEADEREND:event_buttonSpecifyActionPerformed
        setPathSearch();
    }//GEN-LAST:event_buttonSpecifyActionPerformed

    private void buttonCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonCloseActionPerformed
    {//GEN-HEADEREND:event_buttonCloseActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCloseActionPerformed

    private void buttonOpenFileActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonOpenFileActionPerformed
    {//GEN-HEADEREND:event_buttonOpenFileActionPerformed
        fileName=listResult.getSelectedValue().toString();
        if(checkFormat(fileName))
        {
            ff.fileNameOpen=fileName;
            ff.getImage();
            dispose();
        }else
        {
            JOptionPane.showMessageDialog(null,"Формат файла не поддерживается","Произошла ошибка",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonOpenFileActionPerformed

    private void buttonSearchActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buttonSearchActionPerformed
    {//GEN-HEADEREND:event_buttonSearchActionPerformed
        newSearch();
    }//GEN-LAST:event_buttonSearchActionPerformed

    private void textFieldFileNameKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textFieldFileNameKeyReleased
    {//GEN-HEADEREND:event_textFieldFileNameKeyReleased
        if((!textFieldFileName.getText().equals(""))&&(!textFieldSearchIn.getText().equals("")))
        {
            buttonSearch.setEnabled(true);
        }else
        {
            buttonSearch.setEnabled(false);
        }
    }//GEN-LAST:event_textFieldFileNameKeyReleased

    private void textFieldSearchInKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_textFieldSearchInKeyReleased
    {//GEN-HEADEREND:event_textFieldSearchInKeyReleased
        if((!textFieldFileName.getText().equals(""))&&(!textFieldSearchIn.getText().equals("")))
        {
            buttonSearch.setEnabled(true);
        }else
        {
            buttonSearch.setEnabled(false);
        }
    }//GEN-LAST:event_textFieldSearchInKeyReleased

    private void listResultMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_listResultMouseReleased
    {//GEN-HEADEREND:event_listResultMouseReleased
        if((!listResult.isSelectionEmpty())&&(!listResult.getSelectedValue().equals("Поиск не дал результатов")))
        {
            buttonOpenFile.setEnabled(true);
        }else
        {
            buttonOpenFile.setEnabled(false);
        }
    }//GEN-LAST:event_listResultMouseReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonClose;
    private javax.swing.JButton buttonOpenFile;
    private javax.swing.JButton buttonSearch;
    private javax.swing.JButton buttonSpecify;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelFileName;
    private javax.swing.JLabel labelSearchIn;
    private javax.swing.JList listResult;
    private javax.swing.JTextField textFieldFileName;
    private javax.swing.JTextField textFieldSearchIn;
    // End of variables declaration//GEN-END:variables

    public boolean checkFormat(String fullName)
    {
        int indexSuffix=fullName.lastIndexOf(".");

        String str=fullName.substring(indexSuffix+1);

        if(str.equalsIgnoreCase("jpg")||str.equalsIgnoreCase("jpeg")||str.equalsIgnoreCase("gif")||str.equalsIgnoreCase("png"))
        {
            return true;
        }else
        {
            return false;
        }
    }
}