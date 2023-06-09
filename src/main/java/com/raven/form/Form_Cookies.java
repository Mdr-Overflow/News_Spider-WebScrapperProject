package com.raven.form;

import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import com.raven.component.*;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.ModelStudent;
import com.raven.swing.noticeboard.*;
import com.raven.swing.table.*;
import com.raven.swing.table.EventAction;

import javax.swing.*;
import java.awt.*;

public class Form_Cookies extends JPanel {

    public Form_Cookies() {
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
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private void initComponents() {
        jLabel1 = new JLabel();
        label3 = new JLabel();
        button2 = new JButton();
        radioButton1 = new JRadioButton();
        scrollPane1 = new JScrollPane();
        tree1 = new JTree();
        button1 = new JButton();

        //======== this ========

        //---- jLabel1 ----
        jLabel1.setFont(new Font("sansserif", Font.BOLD, 20));
        jLabel1.setForeground(new Color(0x0448d2));
        jLabel1.setText("Cookie Page");
        jLabel1.setIcon(new ImageIcon("C:\\Users\\ADMIN\\Documents\\GitHub\\News_Spider-WebScrapperProject\\Resources\\GoogleCookie.png"));

        //---- label3 ----
        label3.setText("Cookie Files");
        label3.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label3.setForeground(new Color(0x0448d2));
        label3.setHorizontalAlignment(SwingConstants.CENTER);

        //---- button2 ----
        button2.setText("Choose File");

        //---- radioButton1 ----
        radioButton1.setText("Use Cookies ?");
        radioButton1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        radioButton1.setForeground(new Color(0x0448d2));

        //======== scrollPane1 ========
        {

            //---- tree1 ----
            tree1.setModel(new DefaultTreeModel(
                new DefaultMutableTreeNode("Cookies\t") {
                    {
                        add(new DefaultMutableTreeNode(" Test Cookie"));
                        add(new DefaultMutableTreeNode(" Test Cookie 2"));
                    }
                }));
            scrollPane1.setViewportView(tree1);
        }

        //---- button1 ----
        button1.setText("Delete Selected");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(77, 77, 77)
                            .addComponent(radioButton1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(91, 91, 91)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                            .addGap(53, 53, 53)
                            .addComponent(button2)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                    .addGap(65, 65, 65))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 438, Short.MAX_VALUE)
                    .addComponent(button1)
                    .addGap(112, 112, 112))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(46, 46, 46)
                            .addComponent(radioButton1)
                            .addGap(40, 40, 40)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label3, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addComponent(button2))
                            .addGap(62, 62, 62)))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(button1)
                    .addContainerGap(39, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Educational license - Madaras Andrei
    private JLabel jLabel1;
    private JLabel label3;
    private JButton button2;
    private JRadioButton radioButton1;
    private JScrollPane scrollPane1;
    private JTree tree1;
    private JButton button1;
    // End of variables declaration//GEN-END:variables
}
