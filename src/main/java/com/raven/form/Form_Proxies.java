package com.raven.form;

import javax.swing.border.*;
import javax.swing.table.*;
import com.raven.component.*;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.ModelStudent;
import com.raven.swing.noticeboard.*;
import com.raven.swing.table.*;
import com.raven.swing.table.EventAction;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class Form_Proxies extends JPanel {

    public Form_Proxies() {
        initComponents();

        setOpaque(false);
        initData();
    }

    private void initData() {

        initTableData();
    }

    private void initTableData() {
        EventAction eventAction = new EventAction() {
            @Override
            public void delete(ModelStudent student) {
                if (showMessage("Delete Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }

            @Override
            public void update(ModelStudent student) {
                if (showMessage("Update Student : " + student.getName())) {
                    System.out.println("User click OK");
                } else {
                    System.out.println("User click Cancel");
                }
            }
        };

    }


    private boolean showMessage(String message) {
        Message obj = new Message(Frame.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jLabel1 = new JLabel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        textField1 = new JTextField();
        label1 = new JLabel();

        //======== this ========

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setForeground(new Color(0x0448d2));
        jLabel1.setText("Proxies Page");
        jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleProxie.png"));

        //---- radioButton1 ----
        radioButton1.setText("Use Proxies ?");
        radioButton1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        radioButton1.setForeground(new Color(0x0448d2));

        //---- radioButton2 ----
        radioButton2.setText("Use Default");
        radioButton2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        radioButton2.setForeground(new Color(0x0448d2));

        //---- radioButton3 ----
        radioButton3.setText("Use Custom");
        radioButton3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        radioButton3.setForeground(new Color(0x0448d2));

        //---- label1 ----
        label1.setText("URL To Proxie List");
        label1.setIcon(null);
        label1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        label1.setForeground(new Color(0x0448d2));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(153, 153, 153)
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(radioButton3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(28, 28, 28)
                                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20))
                                .addComponent(radioButton2, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)))
                            .addGap(57, 57, 57)))
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jLabel1)
                    .addGap(43, 43, 43)
                    .addComponent(radioButton1)
                    .addGap(18, 18, 18)
                    .addComponent(radioButton2)
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(radioButton3)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1))
                    .addContainerGap(210, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JTextField textField1;
    private JLabel label1;
    // End of variables declaration//GEN-END:variables
}
